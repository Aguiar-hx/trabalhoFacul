// frontend/src/app/components/aluno/aluno-form/aluno-form.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // IMPORTANTE para formulários reativos
import { ActivatedRoute, Router, RouterModule } from '@angular/router'; // Para pegar o ID da URL e para navegação
import { Aluno } from '../../../models/aluno.model'; // Nosso model
import { AlunoService } from '../../../services/aluno.service'; // Nosso serviço

@Component({
  selector: 'app-aluno-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule, // IMPORTANTE: Adicionar ReactiveFormsModule aqui
    RouterModule         // Para o botão de cancelar/voltar, se usar routerLink
  ],
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css']
})
export class AlunoFormComponent implements OnInit {
  alunoForm: FormGroup; // Vai guardar a estrutura do nosso formulário
  isEditMode = false;   // Para saber se estamos criando ou editando
  alunoId: string | null = null; // Para guardar o ID do aluno se estivermos editando

  constructor(
    private fb: FormBuilder, // Ferramenta para criar formulários reativos
    private alunoService: AlunoService,
    private router: Router, // Para navegar para outras páginas
    private route: ActivatedRoute // Para ler parâmetros da URL (como o ID do aluno)
  ) {
    // Inicializamos o formulário aqui no construtor
    this.alunoForm = this.fb.group({
      // Definimos os campos do formulário e suas validações
      nome: ['', Validators.required], // Campo 'nome' é obrigatório
      ira: [null, [Validators.required, Validators.min(0), Validators.max(10)]], // IRA obrigatório, entre 0 e 10
      cursoId: ['', Validators.required],
      periodoIngressoId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // Quando o componente inicia, verificamos se tem um 'id' na URL
    this.alunoId = this.route.snapshot.paramMap.get('id');

    if (this.alunoId) {
      // Se tem 'id', estamos no modo de edição
      this.isEditMode = true;
      this.alunoService.getAlunoById(this.alunoId).subscribe(aluno => {
        // Preenchemos o formulário com os dados do aluno que veio do backend
        this.alunoForm.patchValue(aluno);
      });
    }
    // Se não tem 'id', estamos no modo de criação, e o formulário já começa em branco.
  }

  onSubmit(): void {
    if (this.alunoForm.invalid) {
      // Se o formulário não for válido (ex: campos obrigatórios não preenchidos), não faz nada.
      // (Idealmente, aqui você mostraria mensagens de erro para o usuário)
      this.alunoForm.markAllAsTouched(); // Marca todos os campos como "tocados" para mostrar erros de validação
      return;
    }

    const alunoData: Aluno = this.alunoForm.value; // Pega os dados do formulário

    if (this.isEditMode && this.alunoId) {
      // Se estamos editando, chamamos o método de atualizar do serviço
      this.alunoService.updateAluno(this.alunoId, alunoData).subscribe({
        next: () => {
          this.router.navigate(['/alunos']); // Após atualizar, volta para a lista de alunos
        },
        error: (err) => console.error('Erro ao atualizar aluno', err) // Tratar erro
      });
    } else {
      // Se estamos criando, chamamos o método de criar do serviço
      this.alunoService.createAluno(alunoData).subscribe({
        next: () => {
          this.router.navigate(['/alunos']); // Após criar, volta para a lista de alunos
        },
        error: (err) => console.error('Erro ao criar aluno', err) // Tratar erro
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/alunos']); // Volta para a lista de alunos
  }
}