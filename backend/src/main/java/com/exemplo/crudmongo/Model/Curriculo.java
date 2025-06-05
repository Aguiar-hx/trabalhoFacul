package com.exemplo.crudmongo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data // Lombok para getters, setters, toString, etc.
@Document(collection = "curriculos") // Especifica o nome da coleção no MongoDB
public class Curriculo {

    @Id
    private String id; // Corresponde ao _id no MongoDB

    private String cursoId;

    private Integer ano;

    private Integer semestre;

    private List<String> disciplinasObrigatorias;

    private List<String> disciplinasOptativas;

    // Construtores, se necessário (Lombok pode gerar @NoArgsConstructor, @AllArgsConstructor)
    // Getters e Setters são gerados pelo @Data do Lombok
}