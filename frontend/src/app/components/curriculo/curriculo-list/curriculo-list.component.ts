// Define o pacote e importa as ferramentas e classes necessárias do Angular e do seu próprio projeto.
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes e usar o ciclo de vida 'OnInit'.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor para o template HTML.
import { Router, RouterModule } from '@angular/router'; // Ferramentas do Angular para lidar com navegação (rotas).
import { Curriculo } from '../../../models/curriculo.model'; // Importa a "forma" de um objeto Curriculo do nosso arquivo de modelo.
import { CurriculoService } from '../../../services/curriculo.service'; // Importa o serviço que se comunica com o backend para operações de Currículo.

@Component({
  selector: 'app-curriculo-list', // O nome da tag HTML para este componente (ex: <app-curriculo-list></app-curriculo-list>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [CommonModule, RouterModule], // Declara que este componente utilizará as funcionalidades do CommonModule e do RouterModule em seu template.
  templateUrl: './curriculo-list.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./curriculo-list.component.css']   // Aponta para o arquivo CSS com os estilos específicos deste componente.
})
// A classe implementa 'OnInit', o que significa que ela deve ter um método chamado 'ngOnInit'.
export class CurriculoListComponent implements OnInit {
  
  // Declara uma propriedade chamada 'curriculos'. Será um array de objetos do tipo 'Curriculo', inicializado como um array vazio.
  curriculos: Curriculo[] = [];

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos para o componente funcionar.
   * @param curriculoService Uma instância do nosso serviço para buscar dados do backend.
   * @param router Uma instância do serviço de roteamento do Angular para navegar entre páginas.
   */
  constructor(private curriculoService: CurriculoService, private router: Router) { }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado e inicializado.
   * É o lugar perfeito para buscar os dados iniciais que a tela precisa mostrar.
   */
  ngOnInit(): void {
    this.loadCurriculos(); // Chama o método para carregar os currículos do backend.
  }

  /**
   * Método responsável por buscar a lista de currículos do backend.
   */
  loadCurriculos(): void {
    // Usa o serviço 'curriculoService' para chamar o método 'getCurriculos()'.
    // O método '.subscribe()' "escuta" a resposta da chamada à API.
    this.curriculoService.getCurriculos().subscribe(data => {
      // Quando os dados chegam do backend ('data'), eles são atribuídos à nossa propriedade 'curriculos'.
      // O Angular detecta essa mudança e atualiza automaticamente a tabela no HTML.
      this.curriculos = data;
    });
  }

  /**
   * Método chamado quando o botão "Novo Currículo" é clicado.
   */
  novoCurriculo(): void {
    // Usa o serviço de roteamento para navegar para a página do formulário de criação.
    this.router.navigate(['/curriculos/novo']);
  }

  /**
   * Método chamado quando o botão "Editar" de um item da lista é clicado.
   * @param id O ID do currículo a ser editado, que é opcional.
   */
  editarCurriculo(id?: string): void {
    // Verifica se um ID foi realmente passado.
    if (id) {
      // Navega para a página do formulário de edição, passando o ID na URL.
      this.router.navigate(['/curriculos/editar', id]);
    }
  }

  /**
   * Método chamado quando o botão "Excluir" de um item da lista é clicado.
   * @param id O ID do currículo a ser excluído, que é opcional.
   */
  excluirCurriculo(id?: string): void {
    // Verifica se um ID foi passado e abre uma janela de confirmação para o usuário.
    if (id && confirm('Tem certeza que deseja excluir este currículo?')) {
      // Se o usuário confirmar, chama o método de exclusão no serviço.
      this.curriculoService.deleteCurriculo(id).subscribe(() => {
        // Após a exclusão ser bem-sucedida no backend, a resposta chega aqui.
        // Chamamos 'loadCurriculos()' novamente para recarregar a lista da tabela,
        // que agora virá sem o item que foi excluído.
        this.loadCurriculos();
      });
    }
  }
}