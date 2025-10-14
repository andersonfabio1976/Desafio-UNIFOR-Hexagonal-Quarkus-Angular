import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlunosService } from './alunos.service';
import { finalize } from 'rxjs/operators';

export interface AlunoDTO {
  identifier?: number;
  nome: string;
  email: string;
  dataNascimento: string; // yyyy-MM-dd (compatível com LocalDate)
  createdOn?: string;
  updatedOn?: string;
}

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss'],
})
export class AlunosComponent implements OnInit {
  alunos: AlunoDTO[] = [];
  form: FormGroup;
  editing = false;
  selectedId?: number;
  saving = false;

  constructor(
    private alunosService: AlunosService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      // input type="date" devolve string (yyyy-MM-dd). Se virar Date em algum momento, normalizamos no salvar().
      dataNascimento: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.load();
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
    this.form.reset();
    this.editing = false;
    this.selectedId = undefined;
  }

  private normalizeDate(value: unknown): string {
    if (!value) return '';
    // Se já vier 'yyyy-MM-dd'
    if (typeof value === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(value)) {
      return value;
    }
    // Se vier Date
    if (value instanceof Date) {
      const z = new Date(value.getTime() - value.getTimezoneOffset() * 60000);
      return z.toISOString().slice(0, 10);
    }
    try {
      // Última tentativa: transformar em Date e normalizar
      const d = new Date(String(value));
      if (!isNaN(d.getTime())) {
        const z = new Date(d.getTime() - d.getTimezoneOffset() * 60000);
        return z.toISOString().slice(0, 10);
      }
    } catch {}
    return String(value);
  }

  salvar() {
    if (this.form.invalid || this.saving) return;

    const raw = this.form.getRawValue();
    const dto: AlunoDTO = {
      identifier: this.selectedId,
      nome: (raw.nome ?? '').toString().trim(),
      email: (raw.email ?? '').toString().trim(),
      dataNascimento: this.normalizeDate(raw.dataNascimento),
    };

    console.log('Enviando aluno:', dto);
    this.saving = true;
    this.alunosService
      .salvar(dto)
      .pipe(finalize(() => (this.saving = false)))
      .subscribe({
        next: (res) => {
          console.log('Aluno salvo com sucesso:', res);
          this.load();
        },
        error: (err) => {
          console.error('Falha ao salvar aluno', err);
          alert('Falha ao salvar aluno. Verifique os dados e tente novamente.');
        },
      });
  }

  editar(aluno: AlunoDTO) {
    // dataNascimento no input type="date" deve ser yyyy-MM-dd
    const normalizada = this.normalizeDate(aluno.dataNascimento);
    this.form.patchValue({
      nome: aluno.nome,
      email: aluno.email,
      dataNascimento: normalizada,
    });
    this.editing = true;
    this.selectedId = aluno.identifier;
  }

  cancelar() {
    this.form.reset();
    this.editing = false;
    this.selectedId = undefined;
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

  // Opcional: trackBy para melhorar performance de tabela
  trackById = (_: number, a: AlunoDTO) => a.identifier ?? a.email;
}
