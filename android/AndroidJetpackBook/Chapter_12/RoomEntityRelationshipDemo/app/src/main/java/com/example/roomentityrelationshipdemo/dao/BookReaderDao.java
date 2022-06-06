package com.example.roomentityrelationshipdemo.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.roomentityrelationshipdemo.entity.AccountAndBookLibraryEntity;
import com.example.roomentityrelationshipdemo.entity.BookLibraryWithBookListsAndBooksEntity;
import com.example.roomentityrelationshipdemo.entity.BookLibraryWithBookListsEntity;
import com.example.roomentityrelationshipdemo.entity.BookListWithBooksEntity;
import com.example.roomentityrelationshipdemo.entity.BookWithBookListsEntity;

import java.util.List;

@Dao
public interface BookReaderDao {

    @Transaction
    @Query("SELECT * FROM account_info")
    public List<AccountAndBookLibraryEntity> getAccountAndBookLibrary();

    @Transaction
    @Query("SELECT * FROM book_library")
    public List<BookLibraryWithBookListsEntity> getBookLibraryWithBookListsEntity();

    @Transaction
    @Query("SELECT * FROM book_list")
    public List<BookListWithBooksEntity> getBookListWithBooksEntity();

    @Transaction
    @Query("SELECT * FROM book")
    public List<BookWithBookListsEntity> getBookWithBookListsEntity();

    @Transaction
    @Query("SELECT * FROM book_library")
    public List<BookLibraryWithBookListsAndBooksEntity> getBookLibraryWithBookListsAndBooksEntity();

}
