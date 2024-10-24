package com.elmer.controller;
import com.elmer.entity.BookEntity;
import com.elmer.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void getAllLibros_ShouldReturnListOfBooks() {

        List<BookEntity> libros = Arrays.asList(
                new BookEntity("1", "Don Quijote de la Mancha", "Miguel de Cervantes", "978-0-14-243723-0", true),
                new BookEntity("2", "Cien años de soledad", "Gabriel García Márquez", "978-0-06-088328-7", true)
        );

        when(bookService.allLibros()).thenReturn(libros);

        ResponseEntity<List<BookEntity>> response = bookController.getAllLibros();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(libros.size(), response.getBody().size());
        assertEquals(libros, response.getBody());

        verify(bookService, times(1)).allLibros();
    }
}