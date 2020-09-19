package com.example.triviaapp.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.triviaapp.model.DataModelClass

class DatabaseHandler (contex:Context): SQLiteOpenHelper(contex,DATABASE_NAME,null,DATABASE_VERSION){
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TriviaDatabase"
        private val TABLE_DETAILS = "TriviaTable"
        private val USER_NAME = "name"
        private val CRICKETER_NAME = "cricketer_name"
        private var FLAG_COLOR = "flag_color"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_DETAILS + "("
                + USER_NAME + " TEXT,"
                + CRICKETER_NAME + " TEXT,"
                + FLAG_COLOR + " TEXT"
                +")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS)
        onCreate(db)
    }

    fun insertData(dataModel: DataModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_NAME, dataModel.userName)
        contentValues.put(CRICKETER_NAME,dataModel.cricktrerName )
        contentValues.put(FLAG_COLOR,dataModel.flagColorName)
        val success = db.insert(TABLE_DETAILS, null, contentValues)

        db.close()
        return success
    }

    fun viewData():List<DataModelClass>{
        val dataList:ArrayList<DataModelClass> = ArrayList<DataModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_DETAILS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        var cricketName: String
        var flagColor:String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                cricketName = cursor.getString(cursor.getColumnIndex("cricketer_name"))
                flagColor = cursor.getString(cursor.getColumnIndex("flag_color"))

                val emp= DataModelClass( userName = userName, cricktrerName = cricketName,flagColorName = flagColor)
                dataList.add(emp)
            } while (cursor.moveToNext())
        }
        return dataList
    }
}