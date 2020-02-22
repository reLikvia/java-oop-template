package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook>{
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        int newSize = schoolBooks.length+1;
        schoolBooks = Arrays.copyOf(schoolBooks,newSize);
        schoolBooks[newSize-1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return (SchoolBook[]) Arrays.stream(schoolBooks)
                .filter((book)->book.getName().equalsIgnoreCase(name))
                .toArray();
    }

    @Override
    public boolean removeByName(String name) {
        int oldSize = schoolBooks.length;
        schoolBooks = (SchoolBook[]) Arrays.stream(schoolBooks)
                .filter((book)->!book.getName().equalsIgnoreCase(name))
                .toArray();
        return oldSize != schoolBooks.length;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
