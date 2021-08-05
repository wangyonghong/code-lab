package com.derry.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.derry.myproject.db.MySqliteOpenHelper;

// SQLite增删改查
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    /**
     * 生成DB文件
     * @param view
     */
    public void createDB(View view) {

        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);

        // (helper.getWritableDatabase() / helper.getReadableDatabase()) databases 文件夹的创建，靠下面这句话
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();

    }

    /**
     * 查询
     * @param view
     */
    public void query(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // 确保数据库打开成功，才能放心操作，规范
        if (db.isOpen()) { // 数据库打开成功 返回true 进入if
            // 返回游标
            Cursor cursor = db.rawQuery("select * from persons", null);

            // 迭代游标  往下面移动来遍历数据
            while(cursor.moveToNext()) {
                // 偷懒的写法
                // int _id = cursor.getInt(0);
                // String name = cursor.getString(1);

                // 规范写法
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));

                Log.d("derry", "query: _id:" + _id + " name:" + name);

            }

            // 一定记得关闭游标 否则耗费性能 规范写法
            cursor.close();
            // 数据库也要关闭 规范写法
            db.close();
        }
    }

    /**
     * 插入数据到数据库
     * @param view
     */
    public void insert(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db.isOpen()) { // 确保数据库打开成功，才能放心操作，规范
            // 插入语句
            String sql = "insert into persons(name) values('Derry老师')";

            db.execSQL(sql);

            // 规范：必须关闭 数据库
            db.close();
        }
    }

    /**
     * 修改 修改第5条数据 为： 李连杰
     * @param view
     */
    public void update(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db.isOpen()) { // 确保数据库打开成功，才能放心操作，规范
            // 修改语句
            String sql = "update persons set name =? where _id =?";

            db.execSQL(sql, new Object[]{"李连杰", 5});

            // 规范：必须关闭 数据库
            db.close();
        }

    }

    /**
     * 删除 第4条数据
     * @param view
     */
    public void delete(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (db.isOpen()) { // 确保数据库打开成功，才能放心操作，规范

            // 删除语句
            String sql = "delete from persons where _id =?";

            db.execSQL(sql, new Object[]{4});

            // 规范：必须关闭 数据库
            db.close();
        }
    }
}