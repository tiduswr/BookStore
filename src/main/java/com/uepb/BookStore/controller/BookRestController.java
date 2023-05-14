package com.uepb.BookStore.controller;

import com.uepb.BookStore.model.Book;
import com.uepb.BookStore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody @Valid Book book,
                                           BindingResult bindingResult){

        return bookService.saveBook(book, bindingResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBook(@PathVariable(value = "id") Long id,
                                      @RequestBody @Valid Book book,
                                      BindingResult bindingResult){

        return bookService.updateBook(id, book, bindingResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id){
        return bookService.deleteBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listBooks(){
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book findBookById(@PathVariable(value = "id") Long id){
        return bookService.findBookById(id);
    }

}
