package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

import java.util.Objects;

public class SimpleAuthorService implements AuthorService {
    private AuthorRepository authorRepository;

    public SimpleAuthorService() {

    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        Objects.requireNonNull(author, "Author must not be null");
        Objects.requireNonNull(author.getName(), "Name must not be null");
        Objects.requireNonNull(author.getLastName(), "Last name must not be null");
        return authorRepository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Objects.requireNonNull(name,"Name must not be null");
        Objects.requireNonNull(lastname,"Last name must not be null");
        return authorRepository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        Objects.requireNonNull(author, "Author must not be null");
        Objects.requireNonNull(author.getName(), "Author's name must not be null");
        Objects.requireNonNull(author.getLastName(), "Author's last name must not be null");
        return authorRepository.remove(author);
    }

    @Override
    public int count() {
        return authorRepository.count();
    }
}
