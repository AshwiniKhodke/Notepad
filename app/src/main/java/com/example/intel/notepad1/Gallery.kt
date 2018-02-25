package com.example.intel.notepad1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

class Gallery : AppCompatActivity() {

    val REQUEST_SELECT_IMAGE_IN_ALBUM=0
    val REQUEST_TAKE_PHOTO=1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        /*val takePicture=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture,0)

        val pickPhoto=Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 1)*/

        fun selectImageInAlbum() {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {

                startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
            }
        }
        fun takePhoto() {
            val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent1.resolveActivity(packageManager) != null) {

                startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
            }


        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}
