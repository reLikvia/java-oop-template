package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookRepository, AuthorService authorService) {
        this.schoolBookRepository = schoolBookRepository;
        this.authorService = authorService;
    }


    @Override
    public boolean save(SchoolBook book) {
        Objects.requireNonNull(book, "Book must not be null");

        String authorName = book.getAuthorName();
        String authorLastName = book.getAuthorLastName();

        Objects.requireNonNull(authorName, "Author's name must not be null");
        Objects.requireNonNull(authorLastName, "Author's last name must not be null");

        if (authorService.findByFullName(authorName,authorLastName) != null) {
            return schoolBookRepository.save(book);
        } else {
            return false;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        Objects.requireNonNull(name, "Book's name must not be null");
        return schoolBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        Objects.requireNonNull(name, "Book's name must not be null");
        return schoolBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] foundedBooks = schoolBookRepository.findByName(name);
        if (foundedBooks.length == 0) return null;
        return authorService.findByFullName(foundedBooks[0].getAuthorName(),foundedBooks[0].getAuthorLastName());
    }
}
