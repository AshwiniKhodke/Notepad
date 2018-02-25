package com.example.intel.notepad1

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import android.arch.persistence.room.Update
import android.arch.persistence.room.Delete


/**
 * Created by intel on 16/02/2018.
 */
@Dao
public interface NoteDAO {

    @Query("SELECT * FROM Note ")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

    /*
  * update the object in database
  * @param note, object to be updated
  */
    @Update
    fun update(repos: Note)

    /*
  * delete the object from database
  * @param note, object to be deleted
  */
    @Delete
    fun delete(note: Note)

    /*
  * delete list of objects from database
  * @param note, array of objects to be deleted
  */
    @Delete
    fun delete(vararg note: Note)       // Note... is varargs, here note is an array

}