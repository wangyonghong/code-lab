package com.derry.myproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // Database access object == 对表进行 增删改查
public interface StudentDao {

    // 增
    @Insert
    void insertStudents(Student ... students);

    // 改
    @Update
    void updateStudents(Student... students);

    // 删  条件
    @Delete
    void deleteStudents(Student... students);

    // 删除 所有      @Delete 单个条件删除用的
    @Query("DELETE FROM Student")
    void deleteAllStudents();

    // 查询 所有  倒序 查询
    @Query("SELECT * FROM Student ORDER BY ID DESC")
    List<Student> getAllStudent();
}
