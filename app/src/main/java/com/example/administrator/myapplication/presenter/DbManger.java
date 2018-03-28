package com.example.administrator.myapplication.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myapplication.db.MyDbHelper;

/**
 * Created by Administrator on 2018/3/27.
 * 懒汉单列
 */

public class DbManger {
    private static MyDbHelper helper;
    public static MyDbHelper getIntance(Context mContext)
    {
           if(helper ==null)
           {
               helper = new MyDbHelper(mContext);
           }
           return helper;
    }

    /**
     * 执行sql语句
     * @param db 数据对象
     * @param sql 语句
     */
    public static void exeSql(SQLiteDatabase db,String sql)
    {
        if(db != null)
        {
            if(sql !=null&&!"".equals(sql))
            {
                db.execSQL(sql);
            }

        }

    }

    /**
     * 使用sql查询
     * @param db 数据库对象
     * @param sql 语句
     * @param select 占位符
     * @return 返回游标结果
     */
    public static Cursor selectSql (SQLiteDatabase db , String sql, String[] select)
    {
        Cursor mCursor = null;
        if(db != null)
        {
           mCursor = db.rawQuery(sql,select);
        }
        return mCursor;
    }

}
