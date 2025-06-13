// Define o pacote e importa as ferramentas e classes necessárias do Angular e do seu próprio projeto.
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes e usar o ciclo de vida 'OnInit'.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor.
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms'; // Ferramentas essenciais para criar formulários reativos.
import { ActivatedRoute, Router, RouterModule } from '@angular/router'; // Ferramentas para ler a URL e navegar entre páginas.
import { Curso } from '../../../models/curso.model'; // Importa a "forma" de um objeto Curso do nosso arquivo de modelo.
import { CursoService } from '../../../services/curso.service'; // Importa o serviço que se comunica com o backend para operações de Curso.

@Component({
  selector: 'app-curso-form', // O nome da tag HTML para este componente (ex: <app-curso-form></app-curso-form>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [
    CommonModule,          // Necessário para usar diretivas como *ngIf no HTML.
    ReactiveFormsModule,   // Essencial para usar diretivas como [formGroup] e formControlName no HTML.
    RouterModule           // Necessário se usarmos a diretiva 'routerLink' no HTML.
  ],
  templateUrl: './curso-form.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./curso-form.component.css']   // Aponta para o arquivo CSS com os estilos do componente.
})
// A classe implementa 'OnInit', o que significa que ela deve ter um método chamado 'ngOnInit'.
export class CursoFormComponent implements OnInit {
  
  // Declaração das propriedades da classe.
  cursoForm: FormGroup; // Esta propriedade vai armazenar a estrutura e o estado do nosso formulário.
  isEditMode = false;   // Uma "bandeira" para sabermos se estamos criando um novo curso (false) ou editando um existente (true).
  cursoId: string | null = null; // Para guardar o ID do curso quando estivermos no modo de edição.

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos.
   */
  constructor(
    private fb: FormBuilder, // O FormBuilder é uma ferramenta que ajuda a criar formulários complexos de forma mais fácil.
    private cursoService: CursoService, // Nosso serviço para falar com o backend sobre Cursos.
    private router: Router, // Serviço do Angular para nos permitir navegar para outras páginas.
    private route: ActivatedRoute // Serviço para obter informações sobre a rota atual, como o ID na URL.
  ) {
    // Aqui, no construtor, criamos a estrutura do nosso formulário usando o FormBuilder.
    this.cursoForm = this.fb.group({
      // Cada chave aqui corresponde a um campo no formulário.
      // A estrutura é: [valorInicial, validadores]
      nome: ['', Validators.required],       // Campo 'nome' começa vazio e é obrigatório.
      nivel: ['', Validators.required],      // Campo 'nivel' começa vazio e é obrigatório.
      modalidade: ['', Validators.required], // Campo 'modalidade' começa vazio e é obrigatório.
      turno: ['', Validators.required]       // Campo 'turno' começa vazio e é obrigatório.
    });
  }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado.
   * É o lugar ideal para buscar dados iniciais ou configurar o componente com base em parâmetros de rota.
   */
  ngOnInit(): void {
    // Tentamos pegar o parâmetro 'id' da URL (ex: na rota /cursos/editar/123, ele pegaria o "123").
    this.cursoId = this.route.snapshot.paramMap.get('id');

    // Se um ID foi encontrado na URL, significa que estamos no modo de edição.
    if (this.cursoId) {
      this.isEditMode = true; // Ativamos a "bandeira" de modo de edição.
      // Usamos o serviço para buscar os dados do curso específico pelo ID.
      this.cursoService.getCursoById(this.cursoId).subscribe(curso => {
        // Quando os dados chegam do backend, usamos 'patchValue' para preencher os campos do formulário
        // com os dados do curso que recebemos.
        this.cursoForm.patchValue(curso);
      });
    }
    // Se não houver 'id' na URL, o componente simplesmente mostra o formulário em branco para criação.
  }

  /**
   * Método executado quando o formulário é enviado (geralmente ao clicar no botão 'submit').
   */
  onSubmit(): void {
    // Se o formulário não for válido (alguma regra de validação falhou), não continua.
    if (this.cursoForm.invalid) {
      this.cursoForm.markAllAsTouched(); // Mostra as mensagens de erro de todos os campos que não foram tocados.
      return;
    }

    // Pega todos os valores atuais dos campos do formulário.
    const cursoData: Curso = this.cursoForm.value;

    // Verifica se está no modo de edição para decidir se deve atualizar um curso existente ou criar um novo.
    if (this.isEditMode && this.cursoId) {
      // Se for edição, chama o método de ATUALIZAR do serviço.
      this.cursoService.updateCurso(this.cursoId, cursoData).subscribe({
        next: () => this.router.navigate(['/cursos']), // Em caso de sucesso, navega de volta para a lista de cursos.
        error: (err) => console.error('Erro ao atualizar curso', err) // Em caso de erro, mostra no console.
      });
    } else {
      // Se não for edição, chama o método de CRIAR do serviço.
      this.cursoService.createCurso(cursoData).subscribe({
        next: () => this.router.navigate(['/cursos']), // Em caso de sucesso, navega de volta para a lista.
        error: (err) => console.error('Erro ao criar curso', err) // Em caso de erro, mostra no console.
      });
    }
  }

  /**
   * Método chamado pelo botão "Cancelar".
   */
  cancelar(): void {
    // Simplesmente navega de volta para a página da lista de cursos, sem salvar nada.
    this.router.navigate(['/cursos']);
  }
}