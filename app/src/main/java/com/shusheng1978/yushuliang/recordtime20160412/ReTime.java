package com.shusheng1978.yushuliang.recordtime20160412;

/**
 * Created by yushuliang on 16/4/18.
 */
//数据库插入数据使用
public class ReTime {

    public int _id;
    public String startTime;
    public String endTime;
    public String spendTime;
    public String title;
    public String content;

    public ReTime()
    {
    }

    public ReTime(String startTime, String endTime, String spendTime,String title,String content)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.spendTime = spendTime;
        this.title = title;
        this.content = content;
    }
}
