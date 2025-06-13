// Define o pacote e importa as ferramentas e classes necessárias do Angular e de outras partes do projeto.
import { Injectable } from '@angular/core'; // Importa o decorador @Injectable, que marca a classe como um serviço que pode ser injetado em outros componentes.
import { HttpClient } from '@angular/common/http'; // Importa o HttpClient, a principal ferramenta do Angular para fazer requisições web (API calls).
import { Observable } from 'rxjs'; // Importa o Observable, que é usado para lidar com operações assíncronas, como uma chamada de API.
import { Curriculo } from '../models/curriculo.model'; // Importa a "forma" de um objeto Curriculo do nosso arquivo de modelo.

/**
 * A anotação @Injectable marca esta classe como um serviço que pode ser gerenciado pelo sistema de Injeção de Dependência do Angular.
 * providedIn: 'root' significa que o Angular criará uma única instância (singleton) deste serviço e ela estará disponível para toda a aplicação.
 */
@Injectable({
  providedIn: 'root'
})
export class CurriculoService {
  
  // Define a URL base para todos os endpoints da API de Currículos.
  // 'private' significa que esta variável só pode ser acessada de dentro desta classe.
  // A chamada para /api/curriculos será redirecionada pelo proxy para o backend.
  private apiUrl = '/api/curriculos';

  /**
   * O construtor da classe. O Angular injeta aqui uma instância do HttpClient.
   * @param http A instância do HttpClient fornecida pelo Angular para fazer requisições HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Método para buscar a lista de todos os currículos no backend.
   * @returns Um Observable que, quando "escutado" (com .subscribe()), emitirá um array de Curriculos (Curriculo[]).
   */
  getCurriculos(): Observable<Curriculo[]> {
    // Faz uma requisição HTTP GET para a apiUrl (/api/curriculos) e espera receber um array de Curriculo.
    return this.http.get<Curriculo[]>(this.apiUrl);
  }

  /**
   * Método para buscar um único currículo pelo seu ID.
   * @param id O ID do currículo a ser buscado.
   * @returns Um Observable que emitirá um único objeto Curriculo.
   */
  getCurriculoById(id: string): Observable<Curriculo> {
    // Usa o acento grave (crase) para criar um "template literal", que permite inserir variáveis diretamente na string.
    // Faz uma requisição HTTP GET para a URL montada (ex: /api/curriculos/123).
    return this.http.get<Curriculo>(`${this.apiUrl}/${id}`);
  }

  /**
   * Método para criar um novo currículo.
   * @param curriculo O objeto Curriculo com os dados a serem salvos.
   * @returns Um Observable que emitirá o objeto Curriculo recém-criado (geralmente com o ID preenchido pelo banco).
   */
  createCurriculo(curriculo: Curriculo): Observable<Curriculo> {
    // Faz uma requisição HTTP POST para a apiUrl (/api/curriculos).
    // O segundo argumento ('curriculo') é o corpo (body) da requisição, enviado como JSON.
    return this.http.post<Curriculo>(this.apiUrl, curriculo);
  }

  /**
   * Método para atualizar um currículo existente.
   * @param id O ID do currículo a ser atualizado.
   * @param curriculo O objeto Curriculo com os dados atualizados.
   * @returns Um Observable que emitirá o objeto Curriculo com os dados já atualizados.
   */
  updateCurriculo(id: string, curriculo: Curriculo): Observable<Curriculo> {
    // Faz uma requisição HTTP PUT para a URL específica do currículo (ex: /api/curriculos/123).
    // O objeto 'curriculo' é enviado no corpo da requisição.
    return this.http.put<Curriculo>(`${this.apiUrl}/${id}`, curriculo);
  }

  /**
   * Método para deletar um currículo.
   * @param id O ID do currículo a ser deletado.
   * @returns Um Observable que não emite nenhum valor (void), apenas um sinal de "completo" ou "erro".
   */
  deleteCurriculo(id: string): Observable<void> {
    // Faz uma requisição HTTP DELETE para a URL específica do currículo (ex: /api/curriculos/123).
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}