package com.uepb.BookStore.service;

import com.uepb.BookStore.model.Book;
import com.uepb.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Object> saveBook(Book book, BindingResult bindingResult) {
        book.setId(null);

        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest()
                    .body(createErrorFieldResponse(bindingResult));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRepository.save(book));
    }

    public ResponseEntity<?> updateBook(Long id, Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest()
                    .body(createErrorFieldResponse(bindingResult));
        }else{
            return bookRepository.findById(id)
                    .map(bookToUpdate -> {
                        bookToUpdate.setTitle(book.getTitle());
                        bookToUpdate.setSummary(book.getSummary());
                        bookToUpdate.setTotalPages(book.getTotalPages());
                        bookToUpdate.setReleaseDate(book.getReleaseDate());
                        return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(bookRepository.save(bookToUpdate));
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    public ResponseEntity<Object> deleteBook(Long id){
        return bookRepository.findById(id)
                .map(book -> {
                        bookRepository.delete(book);
                        return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long id) throws ResponseStatusException{
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    public Map<String, Object> createErrorFieldResponse(BindingResult bindingResult){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.put("message", "Validation error");

        Map<String, String> errors = new HashMap<>();
        for(FieldError error : bindingResult.getFieldErrors()){
            System.out.println(error);
            errors.put(error.getField(), error.getDefaultMessage());
        }
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
