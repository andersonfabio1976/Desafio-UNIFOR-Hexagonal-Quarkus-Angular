import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatrizService, MatrizItemDTO } from './matriz.service';
import { LookupService, IdName } from '../../../../shared/lookup.service';

@Component({
  selector: 'app-matriz',
  templateUrl: './matriz.component.html',
  styleUrls: ['./matriz.component.scss']
})
export class MatrizComponent implements OnInit {
  form!: FormGroup;
  cursos: IdName[] = [];
  matriz: MatrizItemDTO[] = [];
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private service: MatrizService,
    private lookup: LookupService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({ cursoId: [null] });

    this.lookup.cursos().subscribe({
      next: (res) => (this.cursos = res ?? []),
      error: (err) => console.error('Erro ao carregar cursos', err),
    });

    this.form.get('cursoId')?.valueChanges.subscribe((cursoId: number | null) => {
      if (cursoId) this.carregarMatriz(cursoId);
      else this.matriz = [];
    });
  }

  carregarMatriz(cursoId: number) {
    this.carregando = true;
    this.service.listarPorCurso(cursoId).subscribe({
      next: (res) => (this.matriz = res ?? []),
      error: (err) => console.error('Erro ao carregar matriz', err),
      complete: () => (this.carregando = false),
    });
  }
}
