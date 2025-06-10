// Define o pacote e importa as ferramentas e classes necessárias do Angular e de outras partes do projeto.
import { Injectable } from '@angular/core'; // Importa o decorador @Injectable, que marca a classe como um serviço que pode ser injetado em outros componentes.
import { HttpClient } from '@angular/common/http'; // Importa o HttpClient, a principal ferramenta do Angular para fazer requisições web (API calls).
import { Observable } from 'rxjs'; // Importa o Observable, que é usado para lidar com operações assíncronas, como uma chamada de API.
import { Aluno } from '../models/aluno.model'; // Importa a "forma" de um objeto Aluno do nosso arquivo de modelo.

/**
 * A anotação @Injectable marca esta classe como um serviço que pode ser gerenciado pelo sistema de Injeção de Dependência do Angular.
 * providedIn: 'root' significa que o Angular criará uma única instância (singleton) deste serviço e ela estará disponível para toda a aplicação.
 */
@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  
  // Define a URL base para todos os endpoints da API de Alunos.
  // 'private' significa que esta variável só pode ser acessada de dentro desta classe.
  // A chamada para /api/alunos será redirecionada pelo proxy para o backend.
  private apiUrl = '/api/alunos';

  /**
   * O construtor da classe. O Angular injeta aqui uma instância do HttpClient.
   * @param http A instância do HttpClient fornecida pelo Angular para fazer requisições HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Método para buscar a lista de todos os alunos no backend.
   * @returns Um Observable que, quando "escutado" (com .subscribe()), emitirá um array de Alunos (Aluno[]).
   */
  getAlunos(): Observable<Aluno[]> {
    // Faz uma requisição HTTP GET para a apiUrl (/api/alunos) e espera receber um array de Aluno.
    return this.http.get<Aluno[]>(this.apiUrl);
  }

  /**
   * Método para buscar um único aluno pelo seu ID.
   * @param id O ID do aluno a ser buscado.
   * @returns Um Observable que emitirá um único objeto Aluno.
   */
  getAlunoById(id: string): Observable<Aluno> {
    // Usa o acento grave (crase) para criar um "template literal", que permite inserir variáveis diretamente na string.
    // Faz uma requisição HTTP GET para a URL montada (ex: /api/alunos/123).
    return this.http.get<Aluno>(`${this.apiUrl}/${id}`);
  }

  /**
   * Método para criar um novo aluno.
   * @param aluno O objeto Aluno com os dados a serem salvos.
   * @returns Um Observable que emitirá o objeto Aluno recém-criado (geralmente com o ID preenchido pelo banco).
   */
  createAluno(aluno: Aluno): Observable<Aluno> {
    // Faz uma requisição HTTP POST para a apiUrl (/api/alunos).
    // O segundo argumento ('aluno') é o corpo (body) da requisição, enviado como JSON.
    return this.http.post<Aluno>(this.apiUrl, aluno);
  }

  /**
   * Método para atualizar um aluno existente.
   * @param id O ID do aluno a ser atualizado.
   * @param aluno O objeto Aluno com os dados atualizados.
   * @returns Um Observable que emitirá o objeto Aluno com os dados já atualizados.
   */
  updateAluno(id: string, aluno: Aluno): Observable<Aluno> {
    // Faz uma requisição HTTP PUT para a URL específica do aluno (ex: /api/alunos/123).
    // O objeto 'aluno' é enviado no corpo da requisição.
    return this.http.put<Aluno>(`${this.apiUrl}/${id}`, aluno);
  }

  /**
   * Método para deletar um aluno.
   * @param id O ID do aluno a ser deletado.
   * @returns Um Observable que não emite nenhum valor (void), apenas um sinal de "completo" ou "erro".
   */
  deleteAluno(id: string): Observable<void> {
    // Faz uma requisição HTTP DELETE para a URL específica do aluno (ex: /api/alunos/123).
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}