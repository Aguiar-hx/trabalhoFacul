package com.exemplo.crudmongo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "alunos") // Especifica o nome da coleção no MongoDB
public class Aluno {

    @Id
    private String id; // Corresponde ao _id no MongoDB

    private String nome;

    private Double ira; // "number" no OpenAPI geralmente é mapeado para Double em Java

    private String cursoId;

    private String periodoIngressoId;

    // Getters e Setters são gerados pelo @Data do Lombok
    // Construtores, se necessário (Lombok pode gerar @NoArgsConstructor, @AllArgsConstructor)
}