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

  /** ✅ Getter compatível com Angular 17 (usar f['nome']) */
  get f() {
    return this.form.controls as { [key: string]: any };
  }

  carregar(): void {
    this.service.listarTodos().subscribe({
      next: (res) => (this.professores = res),
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
      nome: raw.nome.trim(),
      email: raw.email.trim(),
      area: raw.area.trim(),
      coordenador: !!raw.coordenador,
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
      identifier: p.identifier,
      nome: p.nome,
      email: p.email,
      area: p.area,
      coordenador: p.coordenador,
      usuario: {
        username: p.usuario?.username,
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
