import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DisciplinaService } from './disciplina.service';
import { DisciplinaDTO } from './disciplina.dto';
import { LookupService, IdName } from '../../../../shared/lookup.service';

@Component({
  selector: 'app-disciplina',
  templateUrl: './disciplina.component.html',
  styleUrls: ['./disciplina.component.scss']
})
export class DisciplinaComponent implements OnInit {
  form!: FormGroup;
  disciplinas: DisciplinaDTO[] = [];
  saving = false;
  editing = false;
  carregando = false;

  professores: IdName[] = [];
  cursos: IdName[] = [];
  semestres: any[] = []; // {identifier, numero}

  constructor(
    private fb: FormBuilder,
    private service: DisciplinaService,
    private lookup: LookupService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: [null],
      nome: ['', Validators.required],
      cargaHoraria: [null, [Validators.required, Validators.min(1)]], // NOVO
      professorId: [null],
      cursoId: [null],
      semestreId: [null],
    });

    this.listar();
    this.carregarLookups();
  }

  get f() { return this.form.controls as { [key: string]: any }; }

  carregarLookups(): void {
    this.lookup.professores().subscribe({
      next: (res: IdName[]) => (this.professores = res ?? []),
      error: (err: any) => console.error('Erro ao carregar professores', err)
    });
    this.lookup.cursos().subscribe({
      next: (res: IdName[]) => (this.cursos = res ?? []),
      error: (err: any) => console.error('Erro ao carregar cursos', err)
    });
    this.lookup.semestres().subscribe({
      next: (res: any[]) => (this.semestres = res ?? []),
      error: (err: any) => console.error('Erro ao carregar semestres', err)
    });
  }

  listar(): void {
    this.carregando = true;
    this.service.listarTodos().subscribe({
      next: (res) => (this.disciplinas = res ?? []),
      error: (err: any) => console.error('Erro ao listar disciplinas', err),
      complete: () => (this.carregando = false)
    });
  }

  salvar(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.saving = true;
    const raw = this.form.value;

    const dto: DisciplinaDTO = {
      identifier: raw.identifier ?? undefined,
      nome: String(raw.nome ?? '').trim(),
      cargaHoraria: raw.cargaHoraria != null ? Number(raw.cargaHoraria) : undefined, // NOVO
      professor: raw.professorId ? { identifier: Number(raw.professorId) } : undefined,
      curso: raw.cursoId ? { identifier: Number(raw.cursoId) } : undefined,
      semestre: raw.semestreId ? { identifier: Number(raw.semestreId) } : undefined
    };

    this.service.salvar(dto).subscribe({
      next: () => { this.form.reset(); this.listar(); this.editing = false; },
      error: (err: any) => { console.error('Falha ao salvar disciplina', err); alert('Erro ao salvar disciplina.'); },
      complete: () => (this.saving = false)
    });
  }

  editar(d: DisciplinaDTO): void {
    this.editing = true;
    this.form.patchValue({
      identifier: d.identifier ?? null,
      nome: d.nome ?? '',
      cargaHoraria: d.cargaHoraria ?? null, // NOVO
      professorId: d.professor?.identifier ?? null,
      cursoId: d.curso?.identifier ?? null,
      semestreId: d.semestre?.identifier ?? null,
    });
  }

  cancelar(): void { this.form.reset(); this.editing = false; }

  remover(id?: number): void {
    if (!id) return;
    if (confirm('Confirma a exclusão da disciplina?')) {
      this.service.remover(id).subscribe({
        next: () => this.listar(),
        error: (err: any) => { console.error('Erro ao remover disciplina', err); alert('Erro ao remover disciplina.'); }
      });
    }
  }
}
