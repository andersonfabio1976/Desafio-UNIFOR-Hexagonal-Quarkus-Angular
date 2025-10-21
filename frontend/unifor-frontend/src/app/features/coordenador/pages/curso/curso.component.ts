import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CursoService } from './curso.service';
import { CursoDTO } from './curso.dto';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.scss']
})
export class CursoComponent implements OnInit {
  form!: FormGroup;
  cursos: CursoDTO[] = [];
  saving = false;
  editing = false;
  carregando = false;

  constructor(private fb: FormBuilder, private service: CursoService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: [null],
      nome: ['', Validators.required],
    });
    this.listar();
  }

  get f() {
    return this.form.controls as { [key: string]: any };
  }

  listar(): void {
    this.carregando = true;
    this.service.listarTodos().subscribe({
      next: (res) => (this.cursos = res ?? []),
      error: (err) => console.error('Erro ao listar cursos', err),
      complete: () => (this.carregando = false)
    });
  }

  salvar(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.saving = true;
    const raw = this.form.value;
    const dto: CursoDTO = {
      identifier: raw.identifier ?? undefined,
      nome: String(raw.nome ?? '').trim()
    };
    this.service.salvar(dto).subscribe({
      next: () => { this.form.reset(); this.listar(); this.editing = false; },
      error: (err) => { console.error('Falha ao salvar curso', err); alert('Erro ao salvar curso.'); },
      complete: () => (this.saving = false)
    });
  }

  editar(c: CursoDTO): void {
    this.editing = true;
    this.form.patchValue({ identifier: c.identifier, nome: c.nome });
  }

  cancelar(): void {
    this.form.reset(); this.editing = false;
  }

  remover(id?: number): void {
    if (!id) return;
    if (confirm('Confirma a exclusão do curso?')) {
      this.service.remover(id).subscribe({
        next: () => this.listar(),
        error: (err) => { console.error('Erro ao remover curso', err); alert('Erro ao remover curso.'); }
      });
    }
  }
}
