// Importa as ferramentas essenciais do Angular para realizar testes.
import { ComponentFixture, TestBed } from '@angular/core/testing';

// Importa o componente que queremos testar.
import { CurriculoFormComponent } from './curriculo-form.component';

// 'describe' cria um "conjunto de testes" ou uma suíte de testes. É um bloco que agrupa testes relacionados.
// O primeiro argumento é uma descrição do que está sendo testado, geralmente o nome do componente.
describe('CurriculoFormComponent', () => { // O nome aqui estava 'CurriculoForm', mas a convenção é usar o nome do componente.
  
  // Declaração das variáveis que usaremos em nossos testes.
  let component: CurriculoFormComponent; // Esta variável vai armazenar a instância da nossa classe do componente.
  let fixture: ComponentFixture<CurriculoFormComponent>; // 'fixture' é um utilitário de teste que nos ajuda a interagir com o componente e seu template HTML.

  // 'beforeEach' é uma função que é executada uma vez ANTES de cada teste individual ('it' block) dentro desta suíte.
  // É usada para configurar o ambiente de teste.
  beforeEach(async () => {
    // 'TestBed' é a principal ferramenta de teste do Angular.
    // 'configureTestingModule' cria um módulo de teste especial do Angular para isolar o componente.
    await TestBed.configureTestingModule({
      // Para componentes 'standalone', nós simplesmente os importamos aqui.
      // Se o componente tivesse dependências (como HttpClient ou Router), teríamos que provê-las aqui também,
      // geralmente com versões "falsas" (mocks), o que torna os testes mais complexos.
      imports: [CurriculoFormComponent]
    })
    .compileComponents(); // Compila o HTML e CSS do componente.

    // Cria uma instância do componente no ambiente de teste.
    fixture = TestBed.createComponent(CurriculoFormComponent);
    // Pega a instância da classe TypeScript do componente para que possamos interagir com ela (chamar métodos, verificar propriedades, etc.).
    component = fixture.componentInstance;
    // Dispara o ciclo de detecção de mudanças do Angular, o que faz com que o componente seja renderizado.
    fixture.detectChanges();
  });

  // 'it' define um teste individual.
  // A descrição ('should create') diz o que este teste específico deve fazer.
  it('should create', () => {
    // 'expect' é a asserção do teste. É onde verificamos se o resultado é o esperado.
    // 'expect(component).toBeTruthy()' verifica se a variável 'component' foi criada com sucesso (ou seja, se não é nula ou indefinida).
    expect(component).toBeTruthy();
  });
});