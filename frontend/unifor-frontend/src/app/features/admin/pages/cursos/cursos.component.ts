import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Curso, CursoService } from '../../services/curso.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {
  cursos: Curso[] = [];
  form!: FormGroup;
  displayedColumns = ['identifier', 'nome', 'acoes'];

  constructor(private service: CursoService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['']
    });
    this.carregar();
  }

  carregar() {
    this.service.listar().subscribe(data => (this.cursos = data));
  }

  salvar() {
    const curso = this.form.value as Curso;
    this.service.salvar(curso).subscribe(() => {
      this.form.reset();
      this.carregar();
    });
  }

  excluir(id: number) {
    this.service.excluir(id).subscribe(() => this.carregar());
  }
}
