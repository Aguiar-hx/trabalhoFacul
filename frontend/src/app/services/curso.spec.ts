// Importa a ferramenta principal de teste do Angular.
import { TestBed } from '@angular/core/testing';

// Importa o serviço que queremos testar.
// Geralmente, não é necessário adicionar a extensão .js, o TypeScript resolve isso sozinho.
import { CursoService } from './curso.service';

// 'describe' cria um "conjunto de testes" ou uma suíte de testes. É um bloco que agrupa testes relacionados.
// O primeiro argumento é uma descrição do que está sendo testado. O nome aqui estava 'Curso', mas a convenção é usar o nome do serviço para clareza.
describe('CursoService', () => {
  
  // Declaração da variável que usaremos em nossos testes.
  let service: CursoService; // Esta variável vai armazenar a instância da nossa classe de serviço.

  // 'beforeEach' é uma função que é executada uma vez ANTES de cada teste individual ('it' block) dentro desta suíte.
  // É usada para configurar o ambiente de teste de forma limpa para cada teste.
  beforeEach(() => {
    // 'TestBed' é a principal ferramenta de teste do Angular.
    // 'configureTestingModule' cria um módulo de teste especial do Angular para isolar o serviço.
    TestBed.configureTestingModule({});
    
    // 'TestBed.inject()' é usado para obter uma instância do serviço, da mesma forma que o Angular faria
    // através da injeção de dependência na aplicação real.
    // Se o CursoService dependesse de outros serviços (como o HttpClient), teríamos que provê-los
    // dentro do configureTestingModule acima, geralmente com versões "falsas" (mocks).
    service = TestBed.inject(CursoService);
  });

  // 'it' define um teste individual.
  // A descrição ('should be created') diz o que este teste específico deve fazer.
  it('should be created', () => {
    // 'expect' é a asserção do teste. É onde verificamos se o resultado é o esperado.
    // 'expect(service).toBeTruthy()' verifica se a variável 'service' foi criada com sucesso (ou seja, se não é nula ou indefinida).
    // Este é um teste básico apenas para garantir que o serviço pode ser instanciado sem erros.
    expect(service).toBeTruthy();
  });
});