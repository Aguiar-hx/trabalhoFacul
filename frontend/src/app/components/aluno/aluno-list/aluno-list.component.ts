// frontend/src/app/components/aluno/aluno-list/aluno-list.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Para *ngFor, *ngIf
import { Router, RouterModule } from '@angular/router'; // Para navegação nos botões
import { Aluno } from '../../../models/aluno.model'; // Certifique-se que o caminho está correto
import { AlunoService } from '../../../services/aluno.service'; // Certifique-se que o caminho está correto

@Component({
  selector: 'app-aluno-list',
  standalone: true, // IMPORTANTE: Adicionar isto
  imports: [
    CommonModule,    // IMPORTANTE: Para usar *ngFor, *ngIf no HTML
    RouterModule     // IMPORTANTE: Para usar routerLink nos botões (ou router.navigate na lógica)
  ],
  templateUrl: './aluno-list.component.html', // Mantém o seu nome de arquivo HTML
  styleUrls: ['./aluno-list.component.css']   // CORRIGIDO para styleUrls (plural e array)
})
export class AlunoListComponent implements OnInit { // NOME DA CLASSE CORRIGIDO e implementa OnInit
  alunos: Aluno[] = []; // Propriedade para guardar a lista de alunos

  // Injeta o AlunoService para buscar dados e o Router para navegação
  constructor(private alunoService: AlunoService, private router: Router) { }

  ngOnInit(): void { // Método que é chamado quando o componente é iniciado
    this.loadAlunos();
  }

  loadAlunos(): void { // Método para carregar os alunos do serviço
    this.alunoService.getAlunos().subscribe(
      data => {
        this.alunos = data;
      },
      error => {
        console.error('Erro ao carregar alunos!', error);
        // Aqui você poderia, por exemplo, mostrar uma mensagem de erro para o usuário na tela
      }
    );
  }

  // Métodos para os botões (o HTML para eles já foi fornecido antes)
  novoAluno(): void {
    this.router.navigate(['/alunos/novo']); // Rota que criaremos para o formulário
  }

  editarAluno(id?: string): void {
    if (id) {
      this.router.navigate(['/alunos/editar', id]); // Rota para editar aluno
    }
  }

  excluirAluno(id?: string): void {
    if (id) {
      if (confirm('Tem certeza que deseja excluir este aluno?')) {
        this.alunoService.deleteAluno(id).subscribe({
          next: () => {
            this.loadAlunos(); // Recarrega a lista após a exclusão
          },
          error: (err) => {
            console.error('Erro ao excluir aluno:', err);
            // Mostrar mensagem de erro para o usuário
          }
        });
      }
    }
  }
}