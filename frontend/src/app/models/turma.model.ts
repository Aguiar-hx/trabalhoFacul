// A palavra-chave 'export' torna a interface 'Turma' visível e utilizável por outros arquivos da sua aplicação Angular.
// Sem o 'export', arquivos como o TurmaService ou TurmaListComponent não conseguiriam importá-la.
export interface Turma {

  // O '?' depois do nome do campo 'id' significa que este campo é opcional.
  // Isso é útil porque, ao criar uma nova turma, ela ainda não tem um ID (que será gerado pelo banco de dados).
  id?: string;

  // Define que todo objeto Turma deve ter uma propriedade 'disciplinaId', que será do tipo string (texto).
  // Este campo é usado para associar a turma a uma disciplina específica.
  disciplinaId: string;

  // Define a propriedade 'ano' como um número (number). Representa o ano em que a turma é ofertada.
  ano: number;

  // Define a propriedade 'semestre' como um número. Representa o semestre de oferta (ex: 1 ou 2).
  semestre: number;

  // Define a propriedade 'professor' como uma string.
  // Pode ser o nome do professor ou o ID de um professor responsável pela turma.
  professor: string;
}