package com.example.administrator.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.myapplication.model.Constant;

/**
 * Created by Administrator on 2018/3/27.
 */

public class MyDbHelper extends SQLiteOpenHelper {
    /**
     * 构造函数
     * @param context 上下文对象
     * @param name 表示创建数据库的名称
     * @param factory 游标工厂
     * @param version 表示创建数据版本 》=1
     */
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MyDbHelper(Context mContext)
    {
          super(mContext, Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "create table "+Constant.TABLE_NAME+"("+Constant._ID +
               " Integer primary key," +Constant.USER +" varchar(10))";
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
