package com.github.dgarcoe.maskchecker

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.IOException


class CameraActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var imageUri: Uri? = null
    var resultText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        textViewResult.setMovementMethod(ScrollingMovementMethod())

        val validateButton = findViewById<Button>(R.id.validateButton)
        validateButton.setOnClickListener {
            validateMask(resultText)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, PERMISSION_CODE)
            }
            else{
                openCamera()
            }
        }
        else{
            openCamera()
        }

    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera()
                }
                else{
                    //permission from popup was denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK){

            imageViewCamera.setImageURI(imageUri)

            val image: InputImage
            try {
                image = imageUri?.let { InputImage.fromFilePath(applicationContext, it) }!!

                val textRecognizer = TextRecognition.getClient()

                val result = textRecognizer.process(image)
                        .addOnSuccessListener { visionText ->
                            textViewResult.text = visionText.text
                            resultText = visionText.text
                        }
                        .addOnFailureListener { e ->
                           Log.v("MaskCheck","No text has been found")
                        }

            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun validateMask(text:String) {

        var intent : Intent

        if (text.contains(Regex("CE [0-9]*")) && text.contains("FFP2 NR")
                && text.contains("EN 149:2001") && !text.contains("NIOSH",ignoreCase = true)
                && !text.contains("N95",ignoreCase = true) && !text.contains("1282")
                && !text.contains("FDA",ignoreCase = true)) {
            intent = Intent(this, IsFFP2Activity::class.java)
        } else {
            intent = Intent(this, NotFFP2Activity::class.java)
            intent.putExtra("otherStandard",getString(R.string.other_standard_or_false))
        }

        startActivity(intent)

    }


}