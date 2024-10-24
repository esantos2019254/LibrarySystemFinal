package com.elmer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "libros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    private String id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
}