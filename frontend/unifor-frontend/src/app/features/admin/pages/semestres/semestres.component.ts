import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-semestres',
  templateUrl: './semestres.component.html',
  styleUrls: ['./semestres.component.scss']
})
export class SemestresComponent implements OnInit {
  semestres = [
    { id: 1, nome: '2025.1', dataInicio: '01/02/2025', dataFim: '30/06/2025' },
    { id: 2, nome: '2025.2', dataInicio: '01/08/2025', dataFim: '15/12/2025' }
  ];

  constructor() {}

  ngOnInit(): void {}
}
