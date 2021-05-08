package com.example.myapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.database.DatabaseContract.NoteColums.Companion.TABLE_NAME

internal class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbmovie_app"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.NoteColums._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.NoteColums.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColums.DESC} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColums.GENRE} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColums.POSTER} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColums.TRAILER} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColums.RATING} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase , p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}