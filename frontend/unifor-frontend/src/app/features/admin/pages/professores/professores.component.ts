import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfessoresService } from './professores.service';
import { ProfessorDTO } from './professor.dto';

@Component({
  selector: 'app-professores',
  templateUrl: './professores.component.html',
  styleUrls: ['./professores.component.scss']
})
export class ProfessoresComponent implements OnInit {
  form!: FormGroup;
  professores: ProfessorDTO[] = [];
  saving = false;
  editing = false;

  constructor(private fb: FormBuilder, private service: ProfessoresService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: [null],
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      area: ['', Validators.required],
      coordenador: [false],
      usuario: this.fb.group({
        identifier: [null],
        username: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmarSenha: ['', [Validators.required, Validators.minLength(6)]]
      })
    });

    this.carregar();
  }

  get f() {
    return this.form.controls as { [key: string]: any };
  }

  carregar(): void {
    this.service.listarTodos().subscribe({
      next: (res) => (this.professores = res ?? []),
      error: (err) => console.error('Erro ao listar professores', err)
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving = true;
    const raw = this.form.value;

    const dto: ProfessorDTO = {
      identifier: raw.identifier ?? undefined,
      nome: String(raw.nome ?? '').trim(),
      email: String(raw.email ?? '').trim(),
      area: String(raw.area ?? '').trim(),
      coordenador: !!raw.coordenador,
      usuario: {
        identifier: raw.usuario?.identifier ?? undefined,
        username: String(raw.usuario?.username ?? '').trim(),
        firstName: String(raw.nome ?? '').trim(),
        lastName: '',
        email: String(raw.email ?? '').trim(),
        password: raw.usuario?.password
      } as any
    };

    this.service.salvar(dto).subscribe({
      next: () => {
        this.form.reset({ coordenador: false, usuario: { password: '', confirmarSenha: '' } });
        this.carregar();
        this.editing = false;
      },
      error: (err) => {
        console.error('❌ Falha ao salvar professor', err);
        alert('Erro ao salvar professor.');
      },
      complete: () => (this.saving = false)
    });
  }

  editar(p: ProfessorDTO): void {
    this.editing = true;
    this.form.patchValue({
      identifier: p.identifier ?? null,
      nome: p.nome ?? '',
      email: p.email ?? '',
      area: p.area ?? '',
      coordenador: !!p.coordenador,
      usuario: {
        identifier: p.usuario?.identifier ?? null,
        username: p.usuario?.username ?? '',
        password: '',
        confirmarSenha: ''
      }
    });
  }

  cancelar(): void {
    this.form.reset({ coordenador: false, usuario: { password: '', confirmarSenha: '' } });
    this.editing = false;
  }

  remover(id?: number): void {
    if (!id) return;
    if (confirm('Confirma a exclusão do professor?')) {
      this.service.remover(id).subscribe({
        next: () => this.carregar(),
        error: (err) => {
          console.error('❌ Erro ao remover professor', err);
          alert('Erro ao remover professor.');
        }
      });
    }
  }
}
