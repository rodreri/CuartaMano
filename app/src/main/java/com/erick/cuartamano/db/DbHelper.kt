package com.erick.cuartamano.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "autos.db"
        private const val TABLE_GAMES = "autos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_GAMES (id INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT NOT NULL, año TEXT NOT NULL, modelo TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_GAMES")
        onCreate(db)
    }
}