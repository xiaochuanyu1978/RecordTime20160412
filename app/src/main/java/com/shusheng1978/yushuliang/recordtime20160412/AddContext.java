package com.shusheng1978.yushuliang.recordtime20160412;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by yushuliang on 16/4/27.
 * 每件事数据设计Activity
 */
public class AddContext extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnCancel;
    private EditText edStartTime, edEndTime, edSpendTime, edTitle, edContent;
    private DBManager DbManger;
    private SQLiteDatabase DBWriter;
    private String initStartDateTime = cTime(); // 初始化开始时间
    private String initEndDateTime = cTime(); // 初始化结束时间
    DateTimePickDialogUtil dateTimePicKDialog;
    private String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontext);
        btnSave = (Button) findViewById(R.id.save);
        btnCancel = (Button) findViewById(R.id.cancel);
        edStartTime = (EditText) findViewById(R.id.enterStartTime);
        edEndTime = (EditText) findViewById(R.id.enterEndTime);
        edSpendTime = (EditText) findViewById(R.id.enterSpendTime);
        edTitle = (EditText) findViewById(R.id.enterTitle);
        edContent = (EditText) findViewById(R.id.enterContent);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //时间选择按钮
        edStartTime.setOnClickListener(this);
        edEndTime.setOnClickListener(this);

        edStartTime.setText(initStartDateTime);
        edEndTime.setText(initEndDateTime);

        DbManger = new DBManager(this);


    }

    @Override
    public void onClick(View v) {
         switch(v.getId()) {
             case R.id.save:
                 addDB();
                 finish();
                 //重新刷新这个Activity;
                 Intent intent = new Intent(AddContext.this, AddContext.class);
                 startActivity(intent);

                 break;
             case R.id.cancel:

                 finish();

                 break;
             case R.id.enterStartTime:
                 dateTimePicKDialog = new DateTimePickDialogUtil(AddContext.this, initStartDateTime);
                 dateTimePicKDialog.dateTimePicKDialog(edStartTime);
                 break;
             case R.id.enterEndTime:
                 dateTimePicKDialog = new DateTimePickDialogUtil(AddContext.this, initEndDateTime);
                 dateTimePicKDialog.dateTimePicKDialog(edEndTime);
                 edSpendTime.setText(SubSTime());
                 break;
        }

    }

    public String cTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        str = formatter.format(curDate);
        return str;
    }

    //时间差值计算
    public String SubSTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        String SSpTime = null;
        try {
            Date STime = df.parse(String.valueOf(edStartTime.getText()));
            Date ETime = df.parse(String.valueOf(edEndTime.getText()));

            long SpTime = ETime.getTime() - STime.getTime();
            long SpMinutes = SpTime / (1000 * 60);
            SSpTime = String.valueOf(SpMinutes);


        } catch (ParseException e) {
            e.printStackTrace();

        } finally {
            return SSpTime;
        }


    }

    //添加将写好的数据写道SQLite数据库
    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(RecordTimeDatabases.STARTTIME, edStartTime.getText().toString());
        cv.put(RecordTimeDatabases.ENDTIME, edEndTime.getText().toString());
        cv.put(RecordTimeDatabases.SPENDTIME, edSpendTime.getText().toString());
        cv.put(RecordTimeDatabases.TITLE, edTitle.getText().toString());
        cv.put(RecordTimeDatabases.CONTENT, edContent.getText().toString());

        DbManger.addDB(cv);

    }




}
