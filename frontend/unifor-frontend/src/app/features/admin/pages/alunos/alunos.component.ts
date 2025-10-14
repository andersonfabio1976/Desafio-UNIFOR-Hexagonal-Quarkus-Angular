import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlunosService } from './alunos.service';
import { AlunoDTO } from './aluno.dto';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss'],
})
export class AlunosComponent implements OnInit {
  alunos: AlunoDTO[] = [];
  form: FormGroup;
  editing: boolean = false;
  selectedId?: number;

  constructor(
    private alunosService: AlunosService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dataNascimento: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.alunosService.listarTodos().subscribe(list => this.alunos = list);
    this.form.reset();
    this.editing = false;
    this.selectedId = undefined;
  }

  salvar() {
    if (this.form.invalid) return;
    const aluno: AlunoDTO = {
      ...this.form.value,
      identifier: this.selectedId
    };
    this.alunosService.salvar(aluno).subscribe(() => this.load());
  }

  editar(aluno: AlunoDTO) {
    this.form.patchValue(aluno);
    this.editing = true;
    this.selectedId = aluno.identifier;
  }

  cancelar() {
    this.form.reset();
    this.editing = false;
    this.selectedId = undefined;
  }

  remover(id: number) {
    if (confirm('Deseja remover este aluno?')) {
      this.alunosService.remover(id).subscribe(() => this.load());
    }
  }
}
