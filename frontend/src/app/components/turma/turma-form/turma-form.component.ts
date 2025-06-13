// Define o pacote e importa as ferramentas e classes necessárias do Angular e do seu próprio projeto.
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes e usar o ciclo de vida 'OnInit'.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor.
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // Ferramentas essenciais para criar formulários reativos.
import { ActivatedRoute, Router, RouterModule } from '@angular/router'; // Ferramentas para ler a URL e navegar entre páginas.
import { Turma } from '../../../models/turma.model'; // Importa a "forma" de um objeto Turma do nosso arquivo de modelo.
import { TurmaService } from '../../../services/turma.service'; // Importa o serviço que se comunica com o backend para operações de Turma.

@Component({
  selector: 'app-turma-form', // O nome da tag HTML para este componente (ex: <app-turma-form></app-turma-form>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [
    CommonModule,          // Necessário para usar diretivas como *ngIf no HTML.
    ReactiveFormsModule,   // Essencial para usar diretivas como [formGroup] e formControlName no HTML.
    RouterModule           // Necessário se usarmos a diretiva 'routerLink' no HTML.
  ],
  templateUrl: './turma-form.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./turma-form.component.css']   // Aponta para o arquivo CSS com os estilos do componente.
})
// A classe implementa 'OnInit', o que significa que ela deve ter um método chamado 'ngOnInit'.
export class TurmaFormComponent implements OnInit {
  
  // Declaração das propriedades da classe.
  turmaForm: FormGroup; // Esta propriedade vai armazenar a estrutura e o estado do nosso formulário.
  isEditMode = false;   // Uma "bandeira" para sabermos se estamos criando uma nova turma (false) ou editando uma existente (true).
  turmaId: string | null = null; // Para guardar o ID da turma quando estivermos no modo de edição.

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos.
   */
  constructor(
    private fb: FormBuilder, // O FormBuilder é uma ferramenta que ajuda a criar formulários complexos de forma mais fácil.
    private turmaService: TurmaService, // Nosso serviço para falar com o backend sobre Turmas.
    private router: Router, // Serviço do Angular para nos permitir navegar para outras páginas.
    private route: ActivatedRoute // Serviço para obter informações sobre a rota atual, como o ID na URL.
  ) {
    // Aqui, no construtor, criamos a estrutura do nosso formulário usando o FormBuilder.
    this.turmaForm = this.fb.group({
      // Cada chave aqui corresponde a um campo no formulário.
      // A estrutura é: [valorInicial, validadores]
      disciplinaId: ['', Validators.required], // Campo 'disciplinaId' começa vazio e é obrigatório.
      ano: [null, [Validators.required, Validators.min(1900)]], // Campo 'ano' começa nulo, é obrigatório e o valor mínimo é 1900.
      semestre: [null, [Validators.required, Validators.min(1), Validators.max(2)]], // Campo 'semestre' é obrigatório e o valor deve ser 1 ou 2.
      professor: ['', Validators.required] // Campo 'professor' começa vazio e é obrigatório.
    });
  }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado.
   * É o lugar ideal para buscar dados iniciais ou configurar o componente com base em parâmetros de rota.
   */
  ngOnInit(): void {
    // Tentamos pegar o parâmetro 'id' da URL (ex: na rota /turmas/editar/123, ele pegaria o "123").
    this.turmaId = this.route.snapshot.paramMap.get('id');

    // Se um ID foi encontrado na URL, significa que estamos no modo de edição.
    if (this.turmaId) {
      this.isEditMode = true; // Ativamos a "bandeira" de modo de edição.
      // Usamos o serviço para buscar os dados da turma específica pelo ID.
      this.turmaService.getTurmaById(this.turmaId).subscribe(turma => {
        // Quando os dados chegam do backend, usamos 'patchValue' para preencher os campos do formulário
        // com os dados da turma que recebemos.
        this.turmaForm.patchValue(turma);
      });
    }
    // Se não houver 'id' na URL, o componente simplesmente mostra o formulário em branco para criação.
  }

  /**
   * Método executado quando o formulário é enviado (geralmente ao clicar no botão 'submit').
   */
  onSubmit(): void {
    // Se o formulário não for válido (alguma regra de validação falhou), não continua.
    if (this.turmaForm.invalid) {
      this.turmaForm.markAllAsTouched(); // Mostra as mensagens de erro de todos os campos que não foram tocados.
      return;
    }

    // Pega todos os valores atuais dos campos do formulário.
    const turmaData: Turma = this.turmaForm.value;

    // Verifica se está no modo de edição para decidir se deve atualizar uma turma existente ou criar uma nova.
    if (this.isEditMode && this.turmaId) {
      // Se for edição, chama o método de ATUALIZAR do serviço.
      this.turmaService.updateTurma(this.turmaId, turmaData).subscribe({
        next: () => this.router.navigate(['/turmas']) // Em caso de sucesso, navega de volta para a lista de turmas.
      });
    } else {
      // Se não for edição, chama o método de CRIAR do serviço.
      this.turmaService.createTurma(turmaData).subscribe({
        next: () => this.router.navigate(['/turmas']) // Em caso de sucesso, navega de volta para a lista.
      });
    }
  }

  /**
   * Método chamado pelo botão "Cancelar".
   */
  cancelar(): void {
    // Simplesmente navega de volta para a página da lista de turmas, sem salvar nada.
    this.router.navigate(['/turmas']);
  }
}