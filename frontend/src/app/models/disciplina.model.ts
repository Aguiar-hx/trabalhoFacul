// A palavra-chave 'export' torna a interface 'Disciplina' visível e utilizável por outros arquivos da sua aplicação Angular.
// Sem o 'export', arquivos como o DisciplinaService ou DisciplinaListComponent não conseguiriam importá-la.
export interface Disciplina {

  // O '?' depois do nome do campo 'id' significa que este campo é opcional.
  // Isso é útil porque, ao criar uma nova disciplina, ela ainda não tem um ID (que será gerado pelo banco de dados).
  id?: string;

  // Define que todo objeto Disciplina deve ter uma propriedade 'nome', e o valor deve ser do tipo string (texto).
  // Exemplo: "Cálculo I".
  nome: string;

  // Define a propriedade 'cargaHoraria' como um número (number).
  // Representa o total de horas da disciplina.
  cargaHoraria: number;

  // Define a propriedade 'ementa' como uma string.
  // Contém a descrição do conteúdo que será abordado na disciplina.
  ementa: string;
}