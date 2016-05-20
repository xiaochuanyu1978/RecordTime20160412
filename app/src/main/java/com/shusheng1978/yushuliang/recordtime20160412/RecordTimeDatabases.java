package com.shusheng1978.yushuliang.recordtime20160412;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yushuliang on 16/4/12.
 */

public class RecordTimeDatabases extends SQLiteOpenHelper {

    // 数据库版本号
    private static final int VERSION = 1;
    // 数据库名
    private static final String DATABASE_NAME = "ReTimeDB.db";

    // 数据表名，一个数据库中可以有多个表（虽然本例中只建立了一个表）
    public static final String TABLE_NAME = "ReTimeTable";

    public static final String ID = "_id";
    public static final String STARTTIME = "StartTime";
    public static final String ENDTIME = "EndTime";
    public static final String SPENDTIME = "SpendTime";
    public static final String TITLE = "Title";
    public static final String CONTENT = "Content";
    public static final String TIME = "time";


    public RecordTimeDatabases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public RecordTimeDatabases(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);

        // 数据库实际被创建是在getWritableDatabase()或getReadableDatabase()方法调用时
       
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // 调用时间：数据库第一次创建时onCreate()方法会被调用

        // onCreate方法有一个 SQLiteDatabase对象作为参数，根据需要对这个对象填充表和初始化数据
        // 这个方法中主要完成创建数据库后对数据库的操作


        // 构建创建表的SQL语句（可以从SQLite Expert工具的DDL粘贴过来加进StringBuffer中）

         String sql = ("Create table " + TABLE_NAME + "(" + ID
                    + " integer primary key autoincrement,"
                    + STARTTIME + " TEXT NOT NULL,"
                    + ENDTIME + " TEXT NOT NULL,"
                    + SPENDTIME + " TEXT NOT NULL,"
                    + TITLE + " TEXT NOT NULL,"
                    + CONTENT + " TEXT NOT NULL"
                    +")");

        System.out.println(sql);

        // 执行创建表的SQL语句
        db.execSQL(sql);

        // 即便程序修改重新运行，只要数据库已经创建过，就不会再进入这个onCreate方法

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // 调用时间：如果DATABASE_VERSION值被改为别的数,系统发现现有数据库版本不同,即会调用onUpgrade

        // onUpgrade方法的三个参数，一个 SQLiteDatabase对象，一个旧的版本号和一个新的版本号
        // 这样就可以把一个数据库从旧的模型转变到新的模型
        // 这个方法中主要完成更改数据库版本的操作

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        // 上述做法简单来说就是，通过检查常量值来决定如何，升级时删除旧表，然后调用onCreate来创建新表
        // 一般在实际项目中是不能这么做的，正确的做法是在更新数据表结构时，还要考虑用户存放于数据库中的数据不丢失

    }
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        // 每次打开数据库之后首先被执行

    }


}