// Importa o módulo de testes do Angular
import { TestBed } from '@angular/core/testing';

// Importa o serviço Turma (possível erro aqui, veja comentário abaixo)
import { Turma } from './turma.service';

// Descreve o grupo de testes para a entidade/serviço "Turma"
describe('Turma', () => {
  let service: Turma;

  // Antes de cada teste, configura o ambiente de testes
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Turma); // Injeta o serviço a ser testado
  });

  // Testa se o serviço foi criado corretamente
  it('should be created', () => {
    expect(service).toBeTruthy(); // Espera que a instância do serviço seja válida
  });
});
