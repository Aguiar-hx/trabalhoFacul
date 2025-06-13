// Define o pacote e importa as ferramentas e classes necessárias do Angular e do seu próprio projeto.
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes e usar o ciclo de vida 'OnInit'.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor para o template HTML.
import { Router, RouterModule } from '@angular/router'; // Ferramentas do Angular para lidar com navegação (rotas).
import { Turma } from '../../../models/turma.model'; // Importa a "forma" de um objeto Turma do nosso arquivo de modelo.
import { TurmaService } from '../../../services/turma.service'; // Importa o serviço que se comunica com o backend para operações de Turma.

@Component({
  selector: 'app-turma-list', // O nome da tag HTML para este componente (ex: <app-turma-list></app-turma-list>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [CommonModule, RouterModule], // Declara que este componente utilizará as funcionalidades do CommonModule e do RouterModule em seu template.
  templateUrl: './turma-list.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./turma-list.component.css']   // Aponta para o arquivo CSS com os estilos específicos deste componente.
})
// A classe implementa 'OnInit', o que significa que ela deve ter um método chamado 'ngOnInit'.
export class TurmaListComponent implements OnInit {
  
  // Declara uma propriedade chamada 'turmas'. Será um array de objetos do tipo 'Turma', inicializado como um array vazio.
  turmas: Turma[] = [];

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos para o componente funcionar.
   * @param turmaService Uma instância do nosso serviço para buscar dados do backend.
   * @param router Uma instância do serviço de roteamento do Angular para navegar entre páginas.
   */
  constructor(private turmaService: TurmaService, private router: Router) { }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado e inicializado.
   * É o lugar perfeito para buscar os dados iniciais que a tela precisa mostrar.
   */
  ngOnInit(): void {
    this.loadTurmas(); // Chama o método para carregar as turmas do backend.
  }

  /**
   * Método responsável por buscar a lista de turmas do backend.
   */
  loadTurmas(): void {
    // Usa o serviço 'turmaService' para chamar o método 'getTurmas()'.
    // O método '.subscribe()' "escuta" a resposta da chamada à API.
    this.turmaService.getTurmas().subscribe(data => {
      // Quando os dados chegam do backend ('data'), eles são atribuídos à nossa propriedade 'turmas'.
      // O Angular detecta essa mudança e atualiza automaticamente a tabela no HTML.
      this.turmas = data;
    });
  }

  /**
   * Método chamado quando o botão "Nova Turma" é clicado.
   */
  novaTurma(): void {
    // Usa o serviço de roteamento para navegar para a página do formulário de criação.
    this.router.navigate(['/turmas/novo']);
  }

  /**
   * Método chamado quando o botão "Editar" de um item da lista é clicado.
   * @param id O ID da turma a ser editada, que é opcional.
   */
  editarTurma(id?: string): void {
    // Verifica se um ID foi realmente passado.
    if (id) {
      // Navega para a página do formulário de edição, passando o ID na URL.
      this.router.navigate(['/turmas/editar', id]);
    }
  }

  /**
   * Método chamado quando o botão "Excluir" de um item da lista é clicado.
   * @param id O ID da turma a ser excluída, que é opcional.
   */
  excluirTurma(id?: string): void {
    // Verifica se um ID foi passado e abre uma janela de confirmação para o usuário.
    if (id && confirm('Tem certeza que deseja excluir esta turma?')) {
      // Se o usuário confirmar, chama o método de exclusão no serviço.
      this.turmaService.deleteTurma(id).subscribe(() => {
        // Após a exclusão ser bem-sucedida no backend, a resposta chega aqui.
        // Chamamos 'loadTurmas()' novamente para recarregar a lista da tabela,
        // que agora virá sem o item que foi excluído.
        this.loadTurmas();
      });
    }
  }
}