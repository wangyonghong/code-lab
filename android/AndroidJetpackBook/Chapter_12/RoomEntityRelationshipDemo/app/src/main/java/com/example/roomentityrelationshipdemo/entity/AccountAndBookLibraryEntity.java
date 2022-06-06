package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AccountAndBookLibraryEntity {

    @Embedded
    public AccountEntity accountEntity;

    @Relation(parentColumn = "accountId", entityColumn = "libraryId")
    public BookLibraryEntity bookLibraryEntity;
}
