// A palavra-chave 'export' torna a interface 'Curriculo' visível e utilizável por outros arquivos da sua aplicação Angular.
// Sem o 'export', arquivos como o CurriculoService ou CurriculoListComponent não conseguiriam importá-la.
export interface Curriculo {

  // O '?' depois do nome do campo 'id' significa que este campo é opcional.
  // Isso é útil porque, ao criar um novo currículo, ele ainda não tem um ID (que será gerado pelo banco de dados).
  id?: string;

  // Define que todo objeto Curriculo deve ter uma propriedade 'cursoId', que será do tipo string (texto).
  // Este campo é usado para associar o currículo a um curso específico.
  cursoId: string;

  // Define a propriedade 'ano' como um número (number). Representa o ano de vigência do currículo.
  ano: number;

  // Define a propriedade 'semestre' como um número. Representa o semestre de vigência (ex: 1 ou 2).
  semestre: number;

  // Define a propriedade 'disciplinasObrigatorias' como um array de strings (string[]).
  // Cada item nesta lista será o ID de uma disciplina considerada obrigatória neste currículo.
  disciplinasObrigatorias: string[];

  // Define a propriedade 'disciplinasOptativas' também como um array de strings.
  // Cada item nesta lista será o ID de uma disciplina considerada optativa neste currículo.
  disciplinasOptativas: string[];
}