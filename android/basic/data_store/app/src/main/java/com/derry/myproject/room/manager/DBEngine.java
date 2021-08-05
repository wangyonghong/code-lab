package com.derry.myproject.room.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.derry.myproject.room.Student;
import com.derry.myproject.room.StudentDao;
import com.derry.myproject.room.StudentDatabase;

import java.util.List;

// DB的引擎
public class DBEngine {

    // 我只需要拿到dao，就能够对数据库 增删改查了
    private StudentDao studentDao;

    public DBEngine(Context context) {
        StudentDatabase studentDatabase = StudentDatabase.getInstance(context);
        studentDao = studentDatabase.getStudentDao();
    }

    // dao 增删改查

    // insert 插入
    public void insertStudents(Student... students) {
        new InsertAsyncTask(studentDao).execute(students);
    }

    // update 更新
    public void updateStudents(Student... students) {
        new UpdateAsyncTask(studentDao).execute(students);
    }

    // delete 删除 条件
    public void deleteStudents(Student... students) {
        new DeleteAsyncTask(studentDao).execute(students);
    }

    // delete 全部删除
    public void deleteAllStudents() {
        new DeleteAllAsyncTask(studentDao).execute();
    }

    // 查询全部
    public void queryAllStudents() {
        new QueryAllAsyncTask(studentDao).execute();
    }

    // 如果我们想玩数据库 默认是异步线程  ============  异步操作

    // insert 插入
    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao dao;

        public InsertAsyncTask(StudentDao studentDao) {
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.insertStudents(students);
            return null;
        }
    }

    // update 更新
    static class UpdateAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao dao;

        public UpdateAsyncTask(StudentDao studentDao) {
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.updateStudents(students);
            return null;
        }
    }

    // delete 删除 条件
    static class DeleteAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao dao;

        public DeleteAsyncTask(StudentDao studentDao) {
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) { // 既然传递了 student 进来，就是条件删除
            dao.deleteStudents(students);
            return null;
        }
    }

    // 删除 全部删除
    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private StudentDao dao;

        public DeleteAllAsyncTask(StudentDao studentDao) {
            dao = studentDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllStudents();
            return null;
        }
    }

    // 全部 查询
    private static class QueryAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private StudentDao dao;

        public QueryAllAsyncTask(StudentDao studentDao) {
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Student> allStudent = dao.getAllStudent();

            // 遍历全部查询的结构
            for (Student student : allStudent) {
                Log.e("Derry", "doInBackground: 全部 查询 每一项：" + student.toString() );
            }

            return null;
        }
    }

}
