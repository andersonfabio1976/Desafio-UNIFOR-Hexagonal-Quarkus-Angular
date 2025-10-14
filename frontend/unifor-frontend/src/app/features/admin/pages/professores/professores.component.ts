import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ProfessoresService, ProfessorDTO } from './professores.service';

type ProfessorForm = FormGroup<{
  identifier: FormControl<number | null>;
  nome: FormControl<string>;
  email: FormControl<string>;
  area: FormControl<string>;
  coordenador: FormControl<boolean>;
}>;

@Component({
  selector: 'app-professores',
  templateUrl: './professores.component.html',
  styleUrls: ['./professores.component.scss']
})
export class ProfessoresComponent implements OnInit {
  form!: ProfessorForm;
  professores: ProfessorDTO[] = [];
  loading = false;
  saving = false;

  constructor(
    private fb: FormBuilder,
    private service: ProfessoresService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      identifier: this.fb.control<number | null>(null),
      nome: this.fb.control<string>('', { validators: [Validators.required, Validators.minLength(2)], nonNullable: true }),
      email: this.fb.control<string>('', { validators: [Validators.required, Validators.email], nonNullable: true }),
      area: this.fb.control<string>('', { validators: [Validators.required], nonNullable: true }),
      coordenador: this.fb.control<boolean>(false, { nonNullable: true })
    }) as ProfessorForm;

    this.carregar();
  }

  carregar(): void {
    this.loading = true;
    this.service.listarTodos().subscribe({
      next: data => { this.professores = data; this.loading = false; },
      error: err => { console.error('Falha ao listar professores', err); this.loading = false; }
    });
  }

  editar(p: ProfessorDTO): void {
    this.form.patchValue({
      identifier: p.identifier ?? null,
      nome: p.nome,
      email: p.email,
      area: p.area,
      coordenador: !!p.coordenador
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  excluir(p: ProfessorDTO): void {
    if (!p.identifier) return;
    if (!confirm(`Confirma excluir o professor "${p.nome}"?`)) return;
    this.service.remover(p.identifier).subscribe({
      next: () => this.carregar(),
      error: err => console.error('Falha ao excluir', err)
    });
  }

  cancelarEdicao(): void {
    // mantém identifier como null no form; convertemos para undefined no submit (para casar com o DTO)
    this.form.reset({ identifier: null, nome: '', email: '', area: '', coordenador: false } as any);
  }

  submit(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }

    // Converte null -> undefined em identifier para casar com ProfessorDTO.identifier?: number
    const raw = this.form.getRawValue();
    const dto: ProfessorDTO = {
      ...raw,
      identifier: raw.identifier ?? undefined
    };

    this.saving = true;
    this.service.salvar(dto).subscribe({
      next: () => {
        this.saving = false;
        this.cancelarEdicao();
        this.carregar();
      },
      error: err => { this.saving = false; console.error('Falha ao salvar', err); }
    });
  }

  // Getter tipado para usar f.nome / f.email / f.area no template
  get f() { return this.form.controls; }
}
