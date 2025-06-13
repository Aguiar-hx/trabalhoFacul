// Define o pacote e importa as ferramentas e classes necessárias do Angular e de outras partes do projeto.
import { Injectable } from '@angular/core'; // Importa o decorador @Injectable, que marca a classe como um serviço que pode ser injetado em outros componentes.
import { HttpClient } from '@angular/common/http'; // Importa o HttpClient, a principal ferramenta do Angular para fazer requisições web (API calls).
import { Observable } from 'rxjs'; // Importa o Observable, que é usado para lidar com operações assíncronas, como uma chamada de API.
import { Curso } from '../models/curso.model'; // Importa a "forma" de um objeto Curso do nosso arquivo de modelo.

/**
 * A anotação @Injectable marca esta classe como um serviço que pode ser gerenciado pelo sistema de Injeção de Dependência do Angular.
 * providedIn: 'root' significa que o Angular criará uma única instância (singleton) deste serviço e ela estará disponível para toda a aplicação.
 */
@Injectable({
  providedIn: 'root'
})
export class CursoService {
  
  // Define a URL base para todos os endpoints da API de Cursos.
  // 'private' significa que esta variável só pode ser acessada de dentro desta classe.
  // A chamada para /api/cursos será redirecionada pelo proxy para o backend.
  private apiUrl = '/api/cursos';

  /**
   * O construtor da classe. O Angular injeta aqui uma instância do HttpClient.
   * @param http A instância do HttpClient fornecida pelo Angular para fazer requisições HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Método para buscar a lista de todos os cursos no backend.
   * @returns Um Observable que, quando "escutado" (com .subscribe()), emitirá um array de Cursos (Curso[]).
   */
  getCursos(): Observable<Curso[]> {
    // Faz uma requisição HTTP GET para a apiUrl (/api/cursos) e espera receber um array de Curso.
    return this.http.get<Curso[]>(this.apiUrl);
  }

  /**
   * Método para buscar um único curso pelo seu ID.
   * @param id O ID do curso a ser buscado.
   * @returns Um Observable que emitirá um único objeto Curso.
   */
  getCursoById(id: string): Observable<Curso> {
    // Usa o acento grave (crase) para criar um "template literal", que permite inserir variáveis diretamente na string.
    // Faz uma requisição HTTP GET para a URL montada (ex: /api/cursos/123).
    return this.http.get<Curso>(`${this.apiUrl}/${id}`);
  }

  /**
   * Método para criar um novo curso.
   * @param curso O objeto Curso com os dados a serem salvos.
   * @returns Um Observable que emitirá o objeto Curso recém-criado (geralmente com o ID preenchido pelo banco).
   */
  createCurso(curso: Curso): Observable<Curso> {
    // Faz uma requisição HTTP POST para a apiUrl (/api/cursos).
    // O segundo argumento ('curso') é o corpo (body) da requisição, enviado como JSON.
    return this.http.post<Curso>(this.apiUrl, curso);
  }

  /**
   * Método para atualizar um curso existente.
   * @param id O ID do curso a ser atualizado.
   * @param curso O objeto Curso com os dados atualizados.
   * @returns Um Observable que emitirá o objeto Curso com os dados já atualizados.
   */
  updateCurso(id: string, curso: Curso): Observable<Curso> {
    // Faz uma requisição HTTP PUT para a URL específica do curso (ex: /api/cursos/123).
    // O objeto 'curso' é enviado no corpo da requisição.
    return this.http.put<Curso>(`${this.apiUrl}/${id}`, curso);
  }

  /**
   * Método para deletar um curso.
   * @param id O ID do curso a ser deletado.
   * @returns Um Observable que não emite nenhum valor (void), apenas um sinal de "completo" ou "erro".
   */
  deleteCurso(id: string): Observable<void> {
    // Faz uma requisição HTTP DELETE para a URL específica do curso (ex: /api/cursos/123).
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}