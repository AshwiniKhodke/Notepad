package com.example.intel.notepad1

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.room.Room



/**
 * Created by intel on 17/02/2018.
 */

@Database(entities = [(Note::class)], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    private var noteDB: NoteDatabase? = null
    fun getInstance(context: Context): NoteDatabase? {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context)
        }
        return noteDB
    }

    private fun buildDatabaseInstance(context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "Note.db" ).build()
    }



    fun cleanUp() {
        noteDB = null
    }

}