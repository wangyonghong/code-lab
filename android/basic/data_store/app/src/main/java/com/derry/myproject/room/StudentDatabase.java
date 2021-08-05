package com.derry.myproject.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 数据库 关联  之前的 表  数据库信息
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    // 用户只需要操作dao，我们必须暴漏dao，dao被用户拿到后，就能对数据库 增删改查了
    public abstract StudentDao getStudentDao();

    // 单例模式  返回 DB
    private static StudentDatabase INSTANCE;
    public static synchronized StudentDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(), StudentDatabase.class, "student_database")

                    // 如果我们想玩数据库 默认是异步线程

                    // 慎用：强制开启 主线程 也可以操作 数据库  （测试可以用， 真实环境下 不要用）
                    //.allowMainThreadQueries()

                    .build();
        }
        return INSTANCE;
    }

}
