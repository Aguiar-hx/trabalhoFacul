// A palavra-chave 'export' torna a interface 'Aluno' visível e utilizável em outras partes da sua aplicação Angular.
// Sem o 'export', outros arquivos (como o AlunoService ou AlunoListComponent) não conseguiriam importá-la.
export interface Aluno {

  // O '?' depois do nome do campo 'id' significa que este campo é opcional.
  // Isso é muito útil porque, ao criar um novo aluno, ele ainda não tem um ID (que será gerado pelo banco de dados).
  // Ao editar um aluno, o ID será obrigatório.
  id?: string;

  // Define que todo objeto Aluno deve ter uma propriedade 'nome', e o valor deve ser do tipo string (texto).
  nome: string;

  // Define a propriedade 'ira' (Índice de Rendimento Acadêmico) como um número (number).
  // Em TypeScript, 'number' pode representar tanto inteiros quanto números com casas decimais.
  ira: number;

  // Define a propriedade 'cursoId' como uma string.
  // Este campo será usado para associar o aluno a um curso específico.
  cursoId: string;

  // Define a propriedade 'periodoIngressoId' como uma string.
  // Representa o semestre de entrada do aluno (ex: "2024.1").
  periodoIngressoId: string;
}