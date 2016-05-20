package com.shusheng1978.yushuliang.recordtime20160412;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by yushuliang on 16/5/2.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private Cursor cursor;
    private View layout;

    public MyAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载视图权限
        LayoutInflater inflater = LayoutInflater.from(context);
        //创建视图对象
        layout = inflater.inflate(R.layout.cell, null);
        //获取cell控件内容
        TextView contentStartTime = (TextView) layout.findViewById(R.id.startTime);
        TextView contentEndtTime = (TextView) layout.findViewById(R.id.endTime);
        TextView contentSpendTime = (TextView) layout.findViewById(R.id.spendTime);
        TextView contentTitle = (TextView) layout.findViewById(R.id.title);
        TextView contentContent = (TextView) layout.findViewById(R.id.context);
        //移动光标到一个绝对的位置,解释？？？
        cursor.moveToPosition(position);
        //获取内容？？？
        String ReadStartTime = cursor.getString(cursor.getColumnIndex("StartTime"));
        String ReadEndTime = cursor.getString(cursor.getColumnIndex("EndTime"));
        String ReadSpendTime = cursor.getString(cursor.getColumnIndex("SpendTime"));
        String ReadTitle = cursor.getString(cursor.getColumnIndex("Title"));
        String ReadContext = cursor.getString(cursor.getColumnIndex("Content"));
        //读取的数据传递进来
        contentStartTime.setText(ReadStartTime);
        contentEndtTime.setText(ReadEndTime);
        contentSpendTime.setText(ReadSpendTime);
        contentTitle.setText(ReadTitle);
        contentContent.setText(ReadContext);



        return layout;
    }
}









