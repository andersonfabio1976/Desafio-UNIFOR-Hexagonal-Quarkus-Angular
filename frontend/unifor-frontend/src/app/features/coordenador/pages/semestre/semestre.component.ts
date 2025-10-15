import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SemestreService } from './semestre.service';
import { SemestreDTO } from './semestre.dto';
import { LookupService, IdName } from '../../../../shared/lookup.service';

@Component({
  selector: 'app-semestre',
  templateUrl: './semestre.component.html',
  styleUrls: ['./semestre.component.scss']
})
export class SemestreComponent implements OnInit {
  form!: FormGroup;
  semestres: SemestreDTO[] = [];
  saving = false;
  editing = false;
  carregando = false;

  cursos: IdName[] = [];

  constructor(
    private fb: FormBuilder,
    private service: SemestreService,
    private lookup: LookupService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: [null],
      numero: [null, Validators.required],
      cursoId: [null]
    });

    this.listar();
    this.carregarCursos();
  }

  get f() {
    return this.form.controls as { [key: string]: any };
  }

  carregarCursos(): void {
    this.lookup.cursos().subscribe({
      next: (res: IdName[]) => (this.cursos = res ?? []),
      error: (err: any) => console.error('Erro ao carregar cursos', err)
    });
  }

  listar(): void {
    this.carregando = true;
    this.service.listarTodos().subscribe({
      next: (res) => (this.semestres = res ?? []),
      error: (err: any) => console.error('Erro ao listar semestres', err),
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

    const dto: SemestreDTO = {
      identifier: raw.identifier ?? undefined,
      numero: Number(raw.numero),
      curso: raw.cursoId ? { identifier: Number(raw.cursoId) } : undefined
    };

    this.service.salvar(dto).subscribe({
      next: () => {
        this.form.reset();
        this.listar();
        this.editing = false;
      },
      error: (err: any) => {
        console.error('Falha ao salvar semestre', err);
        alert('Erro ao salvar semestre.');
      },
      complete: () => (this.saving = false)
    });
  }

  editar(s: SemestreDTO): void {
    this.editing = true;
    this.form.patchValue({
      identifier: s.identifier ?? null,
      numero: s.numero ?? null,
      cursoId: s.curso?.identifier ?? null
    });
  }

  cancelar(): void {
    this.form.reset();
    this.editing = false;
  }

  remover(id?: number): void {
    if (!id) return;
    if (confirm('Confirma a exclusão do semestre?')) {
      this.service.remover(id).subscribe({
        next: () => this.listar(),
        error: (err: any) => {
          console.error('Erro ao remover semestre', err);
          alert('Erro ao remover semestre.');
        }
      });
    }
  }
}
