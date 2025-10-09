import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Aluno, AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss']
})
export class AlunosComponent implements OnInit {
  alunos: Aluno[] = [];
  form!: FormGroup;
  displayedColumns = ['identifier', 'nome', 'email', 'dataNascimento', 'acoes'];

  constructor(private alunoService: AlunoService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: [''],
      email: [''],
      dataNascimento: ['']
    });
    this.carregar();
  }

  carregar() {
    this.alunoService.listar().subscribe(data => this.alunos = data);
  }

  salvar() {
    this.alunoService.salvar(this.form.value).subscribe(() => {
      this.form.reset();
      this.carregar();
    });
  }

  excluir(id: number) {
    this.alunoService.excluir(id).subscribe(() => this.carregar());
  }
}
