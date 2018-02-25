package com.example.intel.notepad1

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.view.View
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.widget.ImageView
import java.io.File
import java.lang.ref.WeakReference


class NoteActivity : AppCompatActivity() {



    var et_id:Int?=null
     var et_title: TextInputEditText? = null
    var et_content: TextInputEditText? = null
    var noteDatabase: NoteDatabase? = null
    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        et_title = findViewById(R.id.et_title)
        et_content = findViewById(R.id.et_content)


        val imgVw = findViewById(R.id.imgVw) as ImageView
       /* imgVw.setOnClickListener{
            startActivity(Intent(this@NoteActivity,Gallery::class.java))
        }*/


        imgVw!!.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {

                selectImage()

            }

        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds options to the action bar if it is present.

        menuInflater.inflate(R.menu.menu_main, menu)

        return true

    }


    private fun selectImage() {


        val options=arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")


        val builder=AlertDialog.Builder(this@NoteActivity)

        builder.setTitle("Add Photo!")

        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {

                val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                val f=File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg")

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))

                startActivityForResult(intent, 1)

            } else if (options[item] == "Choose from Gallery") {

                val intent=Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                startActivityForResult(intent, 2)


            } else if (options[item] == "Cancel") {

                dialog.dismiss()

            }
        })

        builder.show()



    val button = findViewById<Button>(R.id.but_save)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // fetch data and create note object
                note=Note(et_id?.toInt(),
                        et_title.toString(),
                        et_content.toString())

                // create worker thread to insert data into database
                InsertTask(this@NoteActivity).execute()
            }
        })
    }




    private fun setResult(note: Note, flag: Int) {
        setResult(
                flag,
                Intent("note")
        )
        finish()
    }

    private class InsertTask// only retain a weak reference to the activity
    internal constructor(context: NoteActivity) : AsyncTask<Void, Void, Boolean>() {

        private val activityReference: WeakReference<NoteActivity>

        init {
            activityReference = WeakReference(context)
        }

        // doInBackground methods runs on a worker thread
        override fun doInBackground(vararg objs: Void): Boolean? {
            activityReference.get()!!.noteDatabase
            return true
        }

        // onPostExecute runs on main thread
        override fun onPostExecute(bool: Boolean?) {
            super.onPostExecute(bool)
            if (bool!!) {

                activityReference.get()?.setResult(1)
            }
        }

        }
}