import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlunosService } from './alunos.service';
import { AlunoDTO } from '../alunos/aluno.dto';
import { LookupService, IdName } from '../../../../shared/lookup.service'; // usar lookup para listar cursos

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss']
})
export class AlunosComponent implements OnInit {
  form!: FormGroup;
  alunos: AlunoDTO[] = [];
  cursos: IdName[] = []; // dropdown de cursos

  carregando = false;
  saving = false;
  editing = false;

  constructor(
    private fb: FormBuilder,
    private service: AlunosService,
    private lookup: LookupService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      // input type="date" devolve string (yyyy-MM-dd). Se virar Date em algum momento, normalizamos no salvar().
      dataNascimento: ['', Validators.required],
      cursoId: [null, Validators.required], // novo campo
      usuario: this.fb.group({
        identifier: [null],
        username: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmarSenha: ['', [Validators.required, Validators.minLength(6)]]
      })
    });

    this.carregarLookups();
    this.listar();
  }

  get f() {
    return this.form.controls as { [key: string]: any };
  }

  private carregarLookups(): void {
    this.lookup.cursos().subscribe({
      next: (res) => (this.cursos = res ?? []),
      error: (err) => console.error('Erro ao carregar cursos', err),
    });
  }

  load() {
    this.alunosService.listarTodos().subscribe({
      next: (list) => {
        this.alunos = list ?? [];
      },
      error: (err) => {
        console.error('Falha ao listar alunos', err);
        alert('Falha ao carregar a lista de alunos.');
      },
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving = true;
    const raw = this.form.value;

    // Converte ddMMyyyy → yyyy-MM-dd caso venha sem hífens
    let dataFormatada = raw.dataNascimento;
    if (dataFormatada && /^\d{8}$/.test(dataFormatada)) {
      const dia = dataFormatada.substring(0, 2);
      const mes = dataFormatada.substring(2, 4);
      const ano = dataFormatada.substring(4, 8);
      dataFormatada = `${ano}-${mes}-${dia}`;
    }

    const dto: AlunoDTO = {
      identifier: raw.identifier ?? undefined,
      nome: raw.nome.trim(),
      email: raw.email.trim(),
      dataNascimento: dataFormatada,
      curso: raw.cursoId ? { identifier: Number(raw.cursoId), nome: '' } as any : undefined,
      usuario: {
        identifier: raw.usuario.identifier ?? undefined,
        username: raw.usuario.username.trim(),
        firstName: raw.nome.trim(),
        lastName: '',
        email: raw.email.trim(),
        password: raw.usuario.password
      }
    };

    this.service.salvar(dto).subscribe({
      next: () => {
        this.form.reset();
        this.listar();
        this.editing = false;
      },
      error: (err) => {
        console.error('❌ Falha ao salvar aluno', err);
        alert('Erro ao salvar aluno.');
      },
      complete: () => (this.saving = false)
    });
  }

  editar(a: AlunoDTO): void {
    this.editing = true;
    this.form.patchValue({
      identifier: a.identifier,
      nome: a.nome,
      email: a.email,
      dataNascimento: a.dataNascimento,
      cursoId: a.curso?.identifier ?? null,
      usuario: {
        identifier: a.usuario?.identifier ?? null,
        username: a.usuario?.username ?? '',
        password: '',
        confirmarSenha: ''
      }
    });
  }

  cancelar() {
    this.form.reset();
    this.editing = false;
  }

  remover(id: number) {
    if (confirm('Deseja remover este aluno?')) {
      this.alunosService.remover(id).subscribe({
        next: () => this.load(),
        error: (err) => {
          console.error('Falha ao remover aluno', err);
          alert('Falha ao remover aluno.');
        },
      });
    }
  }
}
