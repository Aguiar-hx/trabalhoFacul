// Importa a ferramenta principal de teste do Angular.
import { TestBed } from '@angular/core/testing';

// Importa a interface 'Aluno'. Uma interface é apenas um "contrato" ou uma "forma" para os dados, não é uma classe com lógica.
import { Aluno } from './aluno';

// 'describe' cria um conjunto de testes para 'Aluno'.
describe('Aluno', () => {
  // A variável 'service' está declarada com o tipo 'Aluno'.
  let service: Aluno;

  // Bloco de configuração que roda antes de cada teste.
  beforeEach(() => {
    TestBed.configureTestingModule({});

    // AQUI ESTÁ O ERRO PRINCIPAL:
    // TestBed.inject() é usado para obter uma instância de um SERVIÇO que foi "injetado".
    // 'Aluno' é uma INTERFACE, não um serviço. Interfaces não existem em tempo de execução no JavaScript,
    // elas são apenas uma ferramenta do TypeScript para verificação de tipos durante o desenvolvimento.
    // Portanto, você não pode "injetar" uma interface. Esta linha causaria um erro ao rodar os testes.
    service = TestBed.inject(Aluno); 
  });

  // Teste individual que verifica se o "serviço" foi criado.
  it('should be created', () => {
    // Este teste falharia por causa do erro no 'beforeEach'.
    expect(service).toBeTruthy();
  });
});