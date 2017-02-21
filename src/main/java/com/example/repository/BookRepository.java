package com.example.repository;

import com.example.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookRepository extends CrudRepository<Book, Integer> {

    public List<Book> getByBookName(@Param("bookName") String bookName);

    @Override
    Book findOne(Integer id);
}
