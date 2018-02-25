package com.example.intel.notepad1

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by intel on 16/02/2018.
 */
@Entity
 public class Note {
    @PrimaryKey
    var id: Int?=null

    @ColumnInfo(name="title")
    var title: String?=null

    @ColumnInfo(name="content")
    var content: String?=null


    constructor(id: Int?, title: String?, content: String?) {
        this.id=id
        this.title=title
        this.content=content
    }

    fun getid(): Int? {
        return id
    }

    fun setid(id: Int) {
        this.id=id
    }

    fun getcontent(): String? {
        return content
    }

    fun setcontent(content: String) {
        this.content=content
    }

    fun gettitle(): String? {
        return title
    }

    fun settitle(title: String) {
        this.title=title
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Note) return false

        val note=o as Note?

        if (id !== note!!.id) return false
        return if (title != null) title.equals(note!!.title) else note!!.title == null
    }


    override fun hashCode(): Int {
        var result=id
        result=31 * result!! + if (title != null) title!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Note{" +
                "note_id=" + id +
                ", content='" + content + '\''.toString() +
                ", title='" + title + '\''.toString() +
                '}'.toString()
    }

    

}

