import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProfessorService } from '../../services/professor.service';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-professores',
  templateUrl: './professores.component.html',
  styleUrls: ['./professores.component.scss']
})
export class ProfessoresComponent implements OnInit {
  form!: FormGroup;
  professores: any[] = [];

  displayedColumns: string[] = ['nome', 'email', 'area', 'coordenador', 'acoes'];

  constructor(private fb: FormBuilder, private service: ProfessorService) {}

  ngOnInit() {
    this.form = this.fb.group({
      nome: [''],
      email: [''],
      area: ['']
    });
    this.carregarProfessores();
  }

  carregarProfessores() {
    this.service.listar().subscribe(res => (this.professores = res));
  }

  salvar() {
    this.service.salvar(this.form.value).subscribe(() => this.carregarProfessores());
    this.form.reset();
  }

  remover(identifier: number) {
    this.service.remover(identifier).subscribe(() => this.carregarProfessores());
  }

  toggleCoordenador(event: MatSlideToggleChange, professor: any) { // ✅ tipagem correta
    professor.coordenador = event.checked;
    this.service.salvar(professor).subscribe(() => this.carregarProfessores());
  }
}
