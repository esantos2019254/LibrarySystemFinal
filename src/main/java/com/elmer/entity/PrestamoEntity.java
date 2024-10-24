package com.elmer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoEntity {
    @Id
    private String id;
    private String libroId;
    private String usuarioId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
}
