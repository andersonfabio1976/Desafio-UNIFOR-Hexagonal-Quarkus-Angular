import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
})
export class AlunosComponent implements OnInit {
  form!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: [''],
      matricula: [''],
      email: [''],
    });
  }

  salvar() {
    // aqui depois chamamos o backend
    console.log('Salvar aluno', this.form.value);
  }
}
