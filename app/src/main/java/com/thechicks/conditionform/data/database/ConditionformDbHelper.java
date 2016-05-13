package com.thechicks.conditionform.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thechicks.conditionform.util.Constants;

/**
 * Created by opklnm102 on 2016-05-13.
 */
public class ConditionformDbHelper extends SQLiteOpenHelper {

    public static final String TAG = ConditionformDbHelper.class.getSimpleName();

    private static final int VER_RELEASE_A = 0x01;  //app version 1.0.0

    private static final int CUR_DATABASE_VERSION = VER_RELEASE_A;

    private static final String DATABASE_NAME = "conditionform.db";



    public ConditionformDbHelper(Context context) {
        super(context, DATABASE_NAME, null, CUR_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_DISEASE_TABLE = "CREATE TABLE " + Constants.DiseaseEntray.TABLE_NAME + " (" +
                Constants.DiseaseEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.DiseaseEntray.COLUMN_DISEASE_NAME + " TEXT," +
                //Todo: sql문 정의

                " );";


        db.execSQL(SQL_CREATE_DISEASE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, " db open");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, " onUpgrade() from " + oldVersion + "to " + newVersion);

        int version = oldVersion;

        if(version != CUR_DATABASE_VERSION){
            Log.d(TAG, "Upgrade unsuccessful -- destroying old data during upgrade");

            db.execSQL("DROP TABLE IF EXISTS " + Constants.DiseaseEntray.TABLE_NAME);
            //Todo: 코드 추가

            onCreate(db);
            version = CUR_DATABASE_VERSION;
        }
    }

    public static void deleteDatabase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
