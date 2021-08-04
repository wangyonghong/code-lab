package com.derry.myproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 *  MySqliteOpenHelper 工具类   单例模式（1.构造函数私有化  2.对外提供函数）
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper {

    // 2.对外提供函数  单例模式
    private static SQLiteOpenHelper mInstance;
    public static synchronized SQLiteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySqliteOpenHelper(context, "derryDB.db", null, 1); // 以后想要数据库升级  修改成2   修改成3
        }
        return mInstance;
    }

    // 1.构造函数私有化
    private MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 创建表  表数据初始化  数据库第一次创建的时候调用    第二次发现有了 就不会重复创建了，也意味着：次函数只会执行一次
    // 数据库初始化时用的
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表：persons表  _id  name

        // 主键： primary key  必须唯一的
        // 自动增长数字：例如：1 2 3 4 5 6  autoincrement
        //_id标准的写法（要求1）     只能使用   主键只能是Integer类型的（要求2）
        // id 不标准

        String sql = "create table persons(_id integer primary key autoincrement, name text)";

        db.execSQL(sql);
    }

    // 数据库升级用的
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
