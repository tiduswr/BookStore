package com.uepb.BookStore.repository;

import com.uepb.BookStore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {
}
