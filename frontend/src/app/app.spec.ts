// Importa utilitários de teste do Angular
import { TestBed } from '@angular/core/testing';

// Importa o componente principal da aplicação (App)
import { App } from './app';

// Define o grupo de testes relacionado ao componente App
describe('App', () => {

  // Antes de cada teste, configura o ambiente de testes assíncrono
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App], // Importa o próprio componente App (no Angular standalone, o componente é importado diretamente)
    }).compileComponents(); // Compila os componentes usados no teste
  });

  // Teste 1: Verifica se o componente App é criado corretamente
  it('should create the app', () => {
    const fixture = TestBed.createComponent(App);         // Cria uma instância do componente
    const app = fixture.componentInstance;                // Acessa a instância do componente
    expect(app).toBeTruthy();                             // Verifica se a instância existe (foi criada com sucesso)
  });

  // Teste 2: Verifica se o componente renderiza um <h1> com o texto esperado
  it('should render title', () => {
    const fixture = TestBed.createComponent(App);         // Cria a instância do componente
    fixture.detectChanges();                              // Dispara a detecção de mudanças (para renderizar o template)
    const compiled = fixture.nativeElement as HTMLElement;// Acessa o DOM renderizado
    expect(compiled.querySelector('h1')?.textContent)
      .toContain('Hello, frontend');                      // Verifica se o <h1> contém o texto esperado
  });
});
