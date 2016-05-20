package com.shusheng1978.yushuliang.recordtime20160412;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by yushuliang on 16/5/2.
 */
public class SelectAct extends AppCompatActivity implements View.OnClickListener {

    private Button s_btnSave, s_btnDelect;
    private TextView s_StartTime, s_EndTime, s_SpendTime;
    private EditText s_Title, s_Context;
    DateTimePickDialogUtil dateTimePicKDialog;
    private String initStartDateTime = "2016年5月3日 14:44"; // 初始化开始时间
    private String initEndDateTime = "2016年5月4日 17:44"; // 初始化结束时间


    private RecordTimeDatabases s_DbManger;
    private SQLiteDatabase s_DbDelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectact);

       // System.out.println(getIntent().getIntExtra(RecordTimeDatabases.ID, 0));
        s_btnSave = (Button) findViewById(R.id.select_save);
        s_btnDelect = (Button) findViewById(R.id.select_delect);
        s_StartTime = (TextView) findViewById(R.id.select_StartTime);
        s_EndTime = (TextView) findViewById(R.id.select_EndTime);
        s_SpendTime = (TextView) findViewById(R.id.select_SpendTime);
        s_Title = (EditText) findViewById(R.id.select_Title);
        s_Context = (EditText) findViewById(R.id.select_Content);

        s_DbManger= new RecordTimeDatabases(this);;
        s_DbDelect = s_DbManger.getWritableDatabase();

        s_btnSave.setOnClickListener(this);
        s_btnDelect.setOnClickListener(this);


        SelectDB();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_save:
                finish();
                break;
            case R.id.select_delect:
                DBdelelct();
                finish();
                break;


        }

    }

    //数据显示操作
    public void SelectDB(){
        s_StartTime.setText(getIntent().getStringExtra(RecordTimeDatabases.STARTTIME));
        s_EndTime.setText(getIntent().getStringExtra(RecordTimeDatabases.ENDTIME));
        s_SpendTime.setText(getIntent().getStringExtra(RecordTimeDatabases.SPENDTIME));
        s_Title.setText(getIntent().getStringExtra(RecordTimeDatabases.TITLE));
        s_Context.setText(getIntent().getStringExtra(RecordTimeDatabases.CONTENT));

    }

    //删除数据库数据操作
    public void DBdelelct(){
        s_DbDelect.delete(RecordTimeDatabases.TABLE_NAME,"_id=" +getIntent().getIntExtra(RecordTimeDatabases.ID, 0), null);

    }
}
