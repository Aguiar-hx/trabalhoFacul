// Define o pacote e importa as ferramentas e classes necessárias do Angular e de outras partes do projeto.
import { Injectable } from '@angular/core'; // Importa o decorador @Injectable, que marca a classe como um serviço que pode ser injetado em outros componentes.
import { HttpClient } from '@angular/common/http'; // Importa o HttpClient, a principal ferramenta do Angular para fazer requisições web (API calls).
import { Observable } from 'rxjs'; // Importa o Observable, que é usado para lidar com operações assíncronas, como uma chamada de API.
import { Disciplina } from '../models/disciplina.model'; // Importa a "forma" de um objeto Disciplina do nosso arquivo de modelo.

/**
 * A anotação @Injectable marca esta classe como um serviço que pode ser gerenciado pelo sistema de Injeção de Dependência do Angular.
 * providedIn: 'root' significa que o Angular criará uma única instância (singleton) deste serviço e ela estará disponível para toda a aplicação.
 */
@Injectable({
  providedIn: 'root'
})
export class DisciplinaService {
  
  // Define a URL base para todos os endpoints da API de Disciplinas.
  // 'private' significa que esta variável só pode ser acessada de dentro desta classe.
  // A chamada para /api/disciplinas será redirecionada pelo proxy para o backend.
  private apiUrl = '/api/disciplinas';

  /**
   * O construtor da classe. O Angular injeta aqui uma instância do HttpClient.
   * @param http A instância do HttpClient fornecida pelo Angular para fazer requisições HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Método para buscar a lista de todas as disciplinas no backend.
   * @returns Um Observable que, quando "escutado" (com .subscribe()), emitirá um array de Disciplinas (Disciplina[]).
   */
  getDisciplinas(): Observable<Disciplina[]> {
    // Faz uma requisição HTTP GET para a apiUrl (/api/disciplinas) e espera receber um array de Disciplina.
    return this.http.get<Disciplina[]>(this.apiUrl);
  }

  /**
   * Método para buscar uma única disciplina pelo seu ID.
   * @param id O ID da disciplina a ser buscada.
   * @returns Um Observable que emitirá um único objeto Disciplina.
   */
  getDisciplinaById(id: string): Observable<Disciplina> {
    // Usa o acento grave (crase) para criar um "template literal", que permite inserir variáveis diretamente na string.
    // Faz uma requisição HTTP GET para a URL montada (ex: /api/disciplinas/123).
    return this.http.get<Disciplina>(`${this.apiUrl}/${id}`);
  }

  /**
   * Método para criar uma nova disciplina.
   * @param disciplina O objeto Disciplina com os dados a serem salvos.
   * @returns Um Observable que emitirá o objeto Disciplina recém-criado (geralmente com o ID preenchido pelo banco).
   */
  createDisciplina(disciplina: Disciplina): Observable<Disciplina> {
    // Faz uma requisição HTTP POST para a apiUrl (/api/disciplinas).
    // O segundo argumento ('disciplina') é o corpo (body) da requisição, enviado como JSON.
    return this.http.post<Disciplina>(this.apiUrl, disciplina);
  }

  /**
   * Método para atualizar uma disciplina existente.
   * @param id O ID da disciplina a ser atualizada.
   * @param disciplina O objeto Disciplina com os dados atualizados.
   * @returns Um Observable que emitirá o objeto Disciplina com os dados já atualizados.
   */
  updateDisciplina(id: string, disciplina: Disciplina): Observable<Disciplina> {
    // Faz uma requisição HTTP PUT para a URL específica da disciplina (ex: /api/disciplinas/123).
    // O objeto 'disciplina' é enviado no corpo da requisição.
    return this.http.put<Disciplina>(`${this.apiUrl}/${id}`, disciplina);
  }

  /**
   * Método para deletar uma disciplina.
   * @param id O ID da disciplina a ser deletada.
   * @returns Um Observable que não emite nenhum valor (void), apenas um sinal de "completo" ou "erro".
   */
  deleteDisciplina(id: string): Observable<void> {
    // Faz uma requisição HTTP DELETE para a URL específica da disciplina (ex: /api/disciplinas/123).
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}