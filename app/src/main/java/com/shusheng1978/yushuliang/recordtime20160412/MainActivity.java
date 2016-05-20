package com.shusheng1978.yushuliang.recordtime20160412;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Add, Delete;
    private ListView List;
    private MyAdapter adapter;
    private Cursor cursor;
    private RecordTimeDatabases dbManager;
    private SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



    }

    public void initView(){
        List = (ListView)findViewById(R.id.listView);
        Add = (Button) findViewById(R.id.add);
        Delete = (Button) findViewById(R.id.delete);

        Add.setOnClickListener(this);
        Delete.setOnClickListener(this);

        //实例化数据库
        dbManager = new RecordTimeDatabases(this);
        //添加读取数据库权限
        dbReader = dbManager.getReadableDatabase();
        //listView添加监听事件
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取当前cursor是哪一个位置
               // cursor.moveToPosition(position);
                cursor.moveToPosition(position);
                Intent i = new Intent(MainActivity.this, SelectAct.class);
                i.putExtra(dbManager.ID, cursor.getInt(cursor.getColumnIndex(dbManager.ID)));
                i.putExtra(dbManager.STARTTIME, cursor.getString(cursor.getColumnIndex(dbManager.STARTTIME)));
                i.putExtra(dbManager.ENDTIME, cursor.getString(cursor.getColumnIndex(dbManager.ENDTIME)));
                i.putExtra(dbManager.SPENDTIME, cursor.getString(cursor.getColumnIndex(dbManager.SPENDTIME)));
                i.putExtra(dbManager.TITLE, cursor.getString(cursor.getColumnIndex(dbManager.TITLE)));
                i.putExtra(dbManager.CONTENT, cursor.getString(cursor.getColumnIndex(dbManager.CONTENT)));

                startActivity(i);

            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                Intent it= new Intent(); //创建一个新的intent对象

                it.setClass(MainActivity.this, AddContext.class); //设置Intent的源地址和目标地址
                startActivity(it);       //调用startActivity方法发送意图给系统
                //MainActivity.this.finish();
                break;
            case R.id.delete:

                break;
            default:
                break;
        }
    }

    //数据查询方法
    public void selectDB(){
       cursor = dbReader.query(dbManager.TABLE_NAME, null, null, null, null, null ,null);

        adapter = new MyAdapter(this, cursor);
        List.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
