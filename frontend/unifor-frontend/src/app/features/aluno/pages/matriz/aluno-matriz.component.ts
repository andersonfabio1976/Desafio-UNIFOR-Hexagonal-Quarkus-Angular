import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { MatrizService } from '../../../../core/services/matriz.service';
import { MatrizAlunoItem } from '../../../../core/models/matriz';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

type Row = MatrizAlunoItem;

@Component({
  selector: 'app-aluno-matriz',
  templateUrl: './aluno-matriz.component.html',
  styleUrls: ['./aluno-matriz.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AlunoMatrizComponent implements OnInit {
  loading = true;
  rows$!: Observable<Row[]>;
  error: string | null = null;

  constructor(private matrizService: MatrizService) {}

  ngOnInit(): void {
    this.rows$ = this.matrizService.getMatrizAluno().pipe(
      map(res => res.itens.sort((a, b) => a.periodo - b.periodo)),
    );
    // Apenas para controlar skeleton de forma simples
    setTimeout(() => (this.loading = false), 450);
  }

  trackByRow(_i: number, r: Row) {
    return `${r.periodo}-${r.disciplina}`;
  }

  statusClass(s?: Row['status']) {
    switch (s) {
      case 'APROVADA': return 'chip approved';
      case 'EM_ANDAMENTO': return 'chip progress';
      case 'PENDENTE': return 'chip pending';
      default: return 'chip';
    }
  }
}
