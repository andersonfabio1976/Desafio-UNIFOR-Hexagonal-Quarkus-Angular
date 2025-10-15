import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlunosService } from './alunos.service';
import { AlunoDTO } from './aluno.dto';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss']
})
export class AlunosComponent implements OnInit {
  form!: FormGroup;
  alunos: AlunoDTO[] = [];
  carregando = false;
  saving = false;
  editing = false;

  constructor(private fb: FormBuilder, private service: AlunosService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: [null],
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dataNascimento: ['', Validators.required],
      usuario: this.fb.group({
        identifier: [null],
        username: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmarSenha: ['', [Validators.required, Validators.minLength(6)]]
      })
    });

    this.listar();
  }

  /** ✅ Getter compatível com Angular 17 (usar f['nome']) */
  get f() {
    return this.form.controls as { [key: string]: any };
  }

  listar(): void {
    this.carregando = true;
    this.service.listarTodos().subscribe({
      next: (res) => (this.alunos = res),
      error: (err) => console.error(err),
      complete: () => (this.carregando = false)
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving = true;
    const raw = this.form.value;

    // 🔧 Converte ddMMyyyy → yyyy-MM-dd
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
      usuario: {
        username: a.usuario?.username,
        password: '',
        confirmarSenha: ''
      }
    });
  }

  cancelar(): void {
    this.form.reset();
    this.editing = false;
  }

  remover(id: number): void {
    if (confirm('Confirma a exclusão do aluno?')) {
      this.service.remover(id).subscribe({
        next: () => this.listar(),
        error: (err) => {
          console.error('❌ Erro ao remover aluno', err);
          alert('Erro ao remover aluno.');
        }
      });
    }
  }
}
