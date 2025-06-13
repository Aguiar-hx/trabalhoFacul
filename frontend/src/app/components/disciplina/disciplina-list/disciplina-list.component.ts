// Define o pacote e importa as ferramentas e classes necessárias do Angular e do seu próprio projeto.
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes e usar o ciclo de vida 'OnInit'.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor para o template HTML.
import { Router, RouterModule } from '@angular/router'; // Ferramentas do Angular para lidar com navegação (rotas).
import { Disciplina } from '../../../models/disciplina.model'; // Importa a "forma" de um objeto Disciplina do nosso arquivo de modelo.
import { DisciplinaService } from '../../../services/disciplina.service'; // Importa o serviço que se comunica com o backend para operações de Disciplina.

@Component({
  selector: 'app-disciplina-list', // O nome da tag HTML para este componente (ex: <app-disciplina-list></app-disciplina-list>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [CommonModule, RouterModule], // Declara que este componente utilizará as funcionalidades do CommonModule e do RouterModule em seu template.
  templateUrl: './disciplina-list.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./disciplina-list.component.css']   // Aponta para o arquivo CSS com os estilos específicos deste componente.
})
// A classe implementa 'OnInit', o que significa que ela deve ter um método chamado 'ngOnInit'.
export class DisciplinaListComponent implements OnInit {
  
  // Declara uma propriedade chamada 'disciplinas'. Será um array de objetos do tipo 'Disciplina', inicializado como um array vazio.
  disciplinas: Disciplina[] = [];

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos para o componente funcionar.
   * @param disciplinaService Uma instância do nosso serviço para buscar dados do backend.
   * @param router Uma instância do serviço de roteamento do Angular para navegar entre páginas.
   */
  constructor(private disciplinaService: DisciplinaService, private router: Router) { }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado e inicializado.
   * É o lugar perfeito para buscar os dados iniciais que a tela precisa mostrar.
   */
  ngOnInit(): void {
    this.loadDisciplinas(); // Chama o método para carregar as disciplinas do backend.
  }

  /**
   * Método responsável por buscar a lista de disciplinas do backend.
   */
  loadDisciplinas(): void {
    // Usa o serviço 'disciplinaService' para chamar o método 'getDisciplinas()'.
    // O método '.subscribe()' "escuta" a resposta da chamada à API.
    this.disciplinaService.getDisciplinas().subscribe(data => {
      // Quando os dados chegam do backend ('data'), eles são atribuídos à nossa propriedade 'disciplinas'.
      // O Angular detecta essa mudança e atualiza automaticamente a tabela no HTML.
      this.disciplinas = data;
    });
  }

  /**
   * Método chamado quando o botão "Nova Disciplina" é clicado.
   */
  novaDisciplina(): void {
    // Usa o serviço de roteamento para navegar para a página do formulário de criação.
    this.router.navigate(['/disciplinas/novo']);
  }

  /**
   * Método chamado quando o botão "Editar" de um item da lista é clicado.
   * @param id O ID da disciplina a ser editada, que é opcional.
   */
  editarDisciplina(id?: string): void {
    // Verifica se um ID foi realmente passado.
    if (id) {
      // Navega para a página do formulário de edição, passando o ID na URL.
      this.router.navigate(['/disciplinas/editar', id]);
    }
  }

  /**
   * Método chamado quando o botão "Excluir" de um item da lista é clicado.
   * @param id O ID da disciplina a ser excluída, que é opcional.
   */
  excluirDisciplina(id?: string): void {
    // Verifica se um ID foi passado e abre uma janela de confirmação para o usuário.
    if (id && confirm('Tem certeza que deseja excluir esta disciplina?')) {
      // Se o usuário confirmar, chama o método de exclusão no serviço.
      this.disciplinaService.deleteDisciplina(id).subscribe(() => {
        // Após a exclusão ser bem-sucedida no backend, a resposta chega aqui.
        // Chamamos 'loadDisciplinas()' novamente para recarregar a lista da tabela,
        // que agora virá sem o item que foi excluído.
        this.loadDisciplinas();
      });
    }
  }
}