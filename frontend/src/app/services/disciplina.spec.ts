// Importa as ferramentas essenciais do Angular para realizar testes.
import { TestBed } from '@angular/core/testing';

// Importa o SERVIÇO que queremos testar, e não a interface.
import { DisciplinaService } from './disciplina.service';

// Declaração da variável que vai armazenar a instância do nosso serviço.
  let service: DisciplinaService;

  // 'beforeEach' é uma função que é executada antes de cada teste individual ('it' block).
  // É usada para configurar o ambiente de teste.
  beforeEach(() => {
    // 'TestBed' é a principal ferramenta de teste do Angular.
    // 'configureTestingModule' cria um módulo de teste especial para o nosso serviço.

// 'it' define um teste individual. A descrição diz o que o teste deve fazer.
  it('should be created', () => {
    // 'expect' é a asserção do teste. É onde verificamos se o resultado é o esperado.
    // 'expect(service).toBeTruthy()' verifica se a variável 'service' foi criada com sucesso.
    // Este é um teste básico apenas para garantir que o serviço pode ser instanciado sem erros.
    expect(service).toBeTruthy();
  });
});