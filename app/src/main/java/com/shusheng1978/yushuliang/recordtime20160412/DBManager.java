package com.shusheng1978.yushuliang.recordtime20160412;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by yushuliang on 16/4/13.
 */
public class DBManager {
    private RecordTimeDatabases helper;
    private SQLiteDatabase db;

    //创建表
    public DBManager(Context context)
    {

        helper = new RecordTimeDatabases(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     *  数据插入函数程序，插入对应数据
     * @param cv
     */

    public void addDB(ContentValues cv) {
        db = helper.getWritableDatabase();

        /*
        String sql=("INSERT INTO ReTimeTable(StartTime, EndTime, SpendTime, Title, Content) VALUES('2016/04/25 16:08', '2016/04/25 17:08', '60', 'Android学习', '学习创建SQLite表格')");
        System.out.println(sql);
        db.execSQL(sql);
        */

        db.insert(RecordTimeDatabases.TABLE_NAME, null, cv);

    }




}
