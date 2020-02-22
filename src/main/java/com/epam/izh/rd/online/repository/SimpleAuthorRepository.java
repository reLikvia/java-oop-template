package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;
import java.util.Objects;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) return false;

        int newSize = authors.length+1;
        authors = Arrays.copyOf(authors,newSize);
        authors[newSize-1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {

        return Arrays.stream(authors)
                .filter((author) -> (name.equalsIgnoreCase(author.getName()) && lastname.equalsIgnoreCase(author.getLastName())))
                .findFirst().orElse(null);
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) return false;
        authors = (Author[]) Arrays.stream(authors)
                .filter((item)->!item.equals(author))
                .toArray();
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
