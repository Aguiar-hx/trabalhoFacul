package com.exemplo.crudmongo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "disciplinas") // Especifica o nome da coleção no MongoDB
public class Disciplina {

    @Id
    private String id; // Corresponde ao _id no MongoDB

    private String nome;

    private Integer cargaHoraria; // Carga horária total da disciplina

    private String ementa; // Descrição da ementa da disciplina

    // Getters e Setters são gerados pelo @Data do Lombok
    // Construtores, se necessário (Lombok pode gerar @NoArgsConstructor, @AllArgsConstructor)
}