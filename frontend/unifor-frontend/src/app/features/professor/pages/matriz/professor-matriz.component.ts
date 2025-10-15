import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { MatrizService } from '../../../../core/services/matriz.service';
import { MatrizProfessorItem } from '../../../../core/models/matriz';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

type Row = MatrizProfessorItem;

@Component({
  selector: 'app-professor-matriz',
  templateUrl: './professor-matriz.component.html',
  styleUrls: ['./professor-matriz.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProfessorMatrizComponent implements OnInit {
  loading = true;
  rows$!: Observable<Row[]>;

  constructor(private matrizService: MatrizService) {}

  ngOnInit(): void {
    this.rows$ = this.matrizService.getMatrizProfessor().pipe(
      map(res => res.itens.sort((a, b) => a.periodo - b.periodo)),
    );
    setTimeout(() => (this.loading = false), 450);
  }

  trackByRow(_i: number, r: Row) {
    return `${r.periodo}-${r.disciplina}`;
  }
}
