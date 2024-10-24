package com.elmer.service;

import com.elmer.entity.BookEntity;
import com.elmer.entity.PrestamoEntity;
import com.elmer.repository.BookRepository;
import com.elmer.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final PrestamoRepository prestamoRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository, PrestamoRepository prestamoRepository) {
        this.bookRepository = bookRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public boolean prestarLibro(String libroId, String usuarioId) {
        Optional<BookEntity> libroOpt = bookRepository.findById(libroId);
        if (libroOpt.isPresent() && libroOpt.get().isDisponible()) {
            BookEntity libro = libroOpt.get();
            libro.setDisponible(false);
            bookRepository.save(libro);

            PrestamoEntity prestamo = new PrestamoEntity();
            prestamo.setLibroId(libroId);
            prestamo.setUsuarioId(usuarioId);
            prestamo.setFechaPrestamo(new Date());
            prestamoRepository.save(prestamo);
            return true;
        }
        return false;
    }

    public boolean devolverLibro(String libroId, String usuarioId) {
        Optional<BookEntity> libroOpt = bookRepository.findById(libroId);

        if (libroOpt.isPresent()) {

            BookEntity libro = libroOpt.get();
            Optional<PrestamoEntity> prestamoOpt = prestamoRepository.findByLibroIdAndUsuarioId(libroId, usuarioId);

            if (prestamoOpt.isPresent()) {
                PrestamoEntity prestamo = prestamoOpt.get();
                libro.setDisponible(true);
                bookRepository.save(libro);
                prestamo.setFechaDevolucion(new Date());
                prestamoRepository.save(prestamo);

                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}