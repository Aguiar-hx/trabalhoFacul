// Importa as ferramentas essenciais do Angular para realizar testes.
import { ComponentFixture, TestBed } from '@angular/core/testing';

// Importa o componente que queremos testar.
import { DisciplinaListComponent } from './disciplina-list.component';

// 'describe' cria um "conjunto de testes" ou uma suíte de testes. É um bloco que agrupa testes relacionados.
// O primeiro argumento é uma descrição do que está sendo testado. O nome aqui estava 'DisciplinaList', mas a convenção é usar o nome do componente para clareza.
describe('DisciplinaListComponent', () => {
  
  // Declaração das variáveis que usaremos em nossos testes.
  let component: DisciplinaListComponent; // Esta variável vai armazenar a instância da nossa classe do componente.
  let fixture: ComponentFixture<DisciplinaListComponent>; // 'fixture' é um utilitário de teste que nos ajuda a interagir com o componente e seu template HTML.

  // 'beforeEach' é uma função que é executada uma vez ANTES de cada teste individual ('it' block) dentro desta suíte.
  // É usada para configurar o ambiente de teste de forma limpa para cada teste.
  beforeEach(async () => {
    // 'TestBed' é a principal ferramenta de teste do Angular.
    // 'configureTestingModule' cria um módulo de teste especial do Angular para isolar o componente.
    await TestBed.configureTestingModule({
      // Para componentes 'standalone', nós simplesmente os importamos aqui.
      // Se o componente tivesse dependências no construtor (como DisciplinaService ou Router), teríamos que provê-las aqui também,
      // geralmente com versões "falsas" (mocks), o que torna os testes mais complexos e foi a causa de alguns dos seus erros de compilação.
      imports: [DisciplinaListComponent]
    })
    .compileComponents(); // Compila o HTML e CSS do componente.

    // Cria uma instância do componente no ambiente de teste.
    fixture = TestBed.createComponent(DisciplinaListComponent);
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