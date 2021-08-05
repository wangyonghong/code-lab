package com.derry.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.derry.myproject.room.Student;
import com.derry.myproject.room.manager.DBEngine;

// Room 的学习
public class MainActivity4 extends AppCompatActivity {

    private DBEngine dbEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        dbEngine = new DBEngine(this);
    }

    /**
     * 插入
     * @param view
     */
    public void insertAction(View view) {
        Student student1 = new Student("张三", 20);
        Student student2 = new Student("李四", 23);
        Student student3 = new Student("王五", 27);
        dbEngine.insertStudents(student1, student2, student3);
    }

    /**
     * 修改  下标为 3   修改成："李元霸", 40
     * @param view
     */
    public void updateAction(View view) {
        Student student = new Student("李元霸", 40);
        student.setId(3);
        dbEngine.updateStudents(student);
    }

    /**
     * 删除 条件 下标为 3
     * @param view
     */
    public void deleteAction(View view) {
        Student student = new Student(null, 0);
        student.setId(3);
        dbEngine.deleteStudents(student);
    }

    /**
     * 查询
     * @param view
     */
    public void queryAction(View view) {
        dbEngine.queryAllStudents();
    }

    /**
     * 全部 删除
     * @param view
     */
    public void deleteAllAction(View view) {
        dbEngine.deleteAllStudents();
    }
}