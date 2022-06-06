package com.example.roomentityrelationshipdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomentityrelationshipdemo.dao.BookReaderDao;
import com.example.roomentityrelationshipdemo.entity.AccountEntity;
import com.example.roomentityrelationshipdemo.entity.BookEntity;
import com.example.roomentityrelationshipdemo.entity.BookLibraryEntity;
import com.example.roomentityrelationshipdemo.entity.BookListBookCrossRefEntity;
import com.example.roomentityrelationshipdemo.entity.BookListEntity;

@Database(entities = {AccountEntity.class, BookLibraryEntity.class, BookListEntity.class, BookEntity.class, BookListBookCrossRefEntity.class}, version = 1)
public abstract class BookLibraryDatabase extends RoomDatabase {
    public abstract BookReaderDao bookReaderDao();
}
