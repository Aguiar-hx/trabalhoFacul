// frontend/src/app/components/curriculo/curriculo-form/curriculo-form.component.ts
import { Component, OnInit } from '@angular/core'; // Funções principais do Angular para criar componentes.
import { CommonModule } from '@angular/common'; // Módulo que fornece diretivas como *ngIf e *ngFor.
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormArray } from '@angular/forms'; // Ferramentas essenciais para criar formulários reativos. FormArray é usado para listas de campos.
import { ActivatedRoute, Router, RouterModule } from '@angular/router'; // Ferramentas para ler a URL e navegar entre páginas.
import { Curriculo } from '../../../models/curriculo.model'; // Importa a "forma" de um objeto Curriculo.
import { CurriculoService } from '../../../services/curriculo.service'; // Importa o serviço que se comunica com o backend.

@Component({
  selector: 'app-curriculo-form', // O nome da tag HTML para este componente (ex: <app-curriculo-form></app-curriculo-form>).
  standalone: true, // Indica que este é um componente independente que gerencia suas próprias dependências.
  imports: [
    CommonModule,          // Necessário para usar *ngFor, *ngIf, etc. no HTML.
    ReactiveFormsModule,   // Essencial para usar diretivas como [formGroup] e formControlName no HTML.
    RouterModule           // Necessário se usarmos a diretiva 'routerLink' no HTML.
  ],
  templateUrl: './curriculo-form.component.html', // Aponta para o arquivo HTML que define a aparência do componente.
  styleUrls: ['./curriculo-form.component.css']   // Aponta para o arquivo CSS com os estilos do componente.
})
export class CurriculoFormComponent implements OnInit {
  // Declaração das propriedades da classe.
  curriculoForm: FormGroup; // Esta propriedade vai armazenar a estrutura e o estado do nosso formulário.
  isEditMode = false;       // Uma "bandeira" para sabermos se estamos criando (false) ou editando (true).
  curriculoId: string | null = null; // Para guardar o ID do currículo quando estivermos no modo de edição.

  /**
   * O construtor é onde a "Injeção de Dependência" do Angular acontece.
   * Pedimos ao Angular para nos fornecer as ferramentas de que precisamos.
   */
  constructor(
    private fb: FormBuilder, // O FormBuilder é uma ferramenta que ajuda a criar formulários complexos.
    private curriculoService: CurriculoService, // Nosso serviço para falar com o backend.
    private router: Router, // Serviço do Angular para navegar entre páginas.
    private route: ActivatedRoute // Serviço para obter informações sobre a rota atual, como o ID na URL.
  ) {
    // Aqui, no construtor, criamos a estrutura do formulário usando o FormBuilder.
    this.curriculoForm = this.fb.group({
      // Cada chave aqui corresponde a um campo no formulário.
      cursoId: ['', Validators.required], // Valor inicial vazio, e é um campo obrigatório.
      ano: [null, [Validators.required, Validators.min(1900)]], // Valor inicial nulo, obrigatório e o valor mínimo é 1900.
      semestre: [null, [Validators.required, Validators.min(1), Validators.max(2)]], // Obrigatório, e o valor deve ser 1 ou 2.
      disciplinasObrigatorias: this.fb.array([]), // Define como um FormArray, uma lista de campos de formulário que começa vazia.
      disciplinasOptativas: this.fb.array([])    // Define também como um FormArray vazio.
    });
  }

  /**
   * ngOnInit é um "gancho de ciclo de vida" do Angular.
   * Este método é executado automaticamente uma vez, logo após o componente ser criado.
   * É o lugar ideal para buscar os dados iniciais.
   */
  ngOnInit(): void {
    // Tentamos pegar o parâmetro 'id' da URL (ex: /curriculos/editar/123).
    this.curriculoId = this.route.snapshot.paramMap.get('id');

    // Se um ID foi encontrado na URL, significa que estamos no modo de edição.
    if (this.curriculoId) {
      this.isEditMode = true;
      // Usamos o serviço para buscar os dados do currículo específico.
      this.curriculoService.getCurriculoById(this.curriculoId).subscribe(curriculo => {
        // 'patchValue' preenche os campos do formulário (cursoId, ano, semestre) com os dados recebidos.
        this.curriculoForm.patchValue(curriculo);
        // Os FormArrays precisam ser preenchidos manualmente.
        this.disciplinasObrigatorias.clear(); // Limpa o array para não duplicar dados se houver re-renderização.
        this.disciplinasOptativas.clear();
        // Para cada disciplina na lista de dados, adiciona um novo campo de controle ao FormArray.
        curriculo.disciplinasObrigatorias.forEach(disciplina => this.disciplinasObrigatorias.push(this.fb.control(disciplina)));
        curriculo.disciplinasOptativas.forEach(disciplina => this.disciplinasOptativas.push(this.fb.control(disciplina)));
      });
    }
  }

  // ** MÉTODOS DE AJUDA PARA AS LISTAS DINÂMICAS **

  // Um "getter" para acessar o FormArray de obrigatórias de forma mais fácil no template HTML.
  get disciplinasObrigatorias() {
    return this.curriculoForm.get('disciplinasObrigatorias') as FormArray;
  }

  // Um "getter" para o FormArray de optativas.
  get disciplinasOptativas() {
    return this.curriculoForm.get('disciplinasOptativas') as FormArray;
  }

  // Método chamado pelo botão "Adicionar Disciplina Obrigatória" no HTML.
  addDisciplinaObrigatoria() {
    // Adiciona um novo campo de controle de formulário (FormControl) em branco ao final do FormArray.
    this.disciplinasObrigatorias.push(this.fb.control(''));
  }

  // Método chamado pelo botão "Adicionar Disciplina Optativa".
  addDisciplinaOptativa() {
    this.disciplinasOptativas.push(this.fb.control(''));
  }

  // Método chamado pelo botão "Remover" de uma disciplina obrigatória.
  removerDisciplinaObrigatoria(index: number) {
    // Remove o campo na posição (índice) especificada do FormArray.
    this.disciplinasObrigatorias.removeAt(index);
  }

  // Método chamado pelo botão "Remover" de uma disciplina optativa.
  removerDisciplinaOptativa(index: number) {
    this.disciplinasOptativas.removeAt(index);
  }

  /**
   * Método executado quando o formulário é enviado (clique no botão 'submit').
   */
  onSubmit(): void {
    // Se o formulário não for válido (alguma regra de validação falhou), não continua.
    if (this.curriculoForm.invalid) {
      this.curriculoForm.markAllAsTouched(); // Mostra as mensagens de erro de todos os campos.
      return;
    }

    // Pega todos os valores atuais do formulário.
    const curriculoData: Curriculo = this.curriculoForm.value;

    // Verifica se está no modo de edição para decidir se deve atualizar ou criar.
    if (this.isEditMode && this.curriculoId) {
      // Se for edição, chama o método de ATUALIZAR do serviço.
      this.curriculoService.updateCurriculo(this.curriculoId, curriculoData).subscribe({
        next: () => this.router.navigate(['/curriculos']), // Em caso de sucesso, navega de volta para a lista.
        error: (err) => console.error('Erro ao atualizar currículo', err) // Em caso de erro, mostra no console.
      });
    } else {
      // Se não for edição, chama o método de CRIAR do serviço.
      this.curriculoService.createCurriculo(curriculoData).subscribe({
        next: () => this.router.navigate(['/curriculos']), // Em caso de sucesso, navega de volta para a lista.
        error: (err) => console.error('Erro ao criar currículo', err) // Em caso de erro, mostra no console.
      });
    }
  }

  /**
   * Método chamado pelo botão "Cancelar".
   */
  cancelar(): void {
    // Simplesmente navega de volta para a página da lista de currículos.
    this.router.navigate(['/curriculos']);
  }
}