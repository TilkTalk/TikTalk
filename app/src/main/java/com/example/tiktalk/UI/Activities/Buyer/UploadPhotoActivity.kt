package com.example.tiktalk.UI.Activities.Buyer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.tiktalk.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import spencerstudios.com.bungeelib.Bungee

class UploadPhotoActivity : AppCompatActivity() {

    val Image_Request_Code = 1

    lateinit var signUpImage: ImageView
    lateinit var doneBtn: Button
    lateinit var uploadPhotoBtn: Button
    lateinit var cancel_btn: Button

    lateinit var name: String
    lateinit var email: String
    lateinit var password: String

    lateinit var imageUri: Uri

    lateinit var auth: FirebaseAuth
    lateinit var storage: StorageReference
    lateinit var firestore: FirebaseFirestore
    lateinit var currentUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_uploadphoto)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance().getReference("ProfileImages")
        firestore = FirebaseFirestore.getInstance()
        currentUser = auth.currentUser?.uid!!

        signUpImage = findViewById(R.id.signup_image)
        doneBtn = findViewById(R.id.done_btn)
        uploadPhotoBtn = findViewById(R.id.uploadPhoto_btn)
        cancel_btn = findViewById(R.id.uploadPhoto_cancel_btn)

        name = intent.getStringExtra("name")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")

        uploadPhotoBtn.setOnClickListener {

            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Please select Image!"), Image_Request_Code)
        }

        cancel_btn.setOnClickListener {
            finish()
        }

        doneBtn.setOnClickListener {

            val intent = Intent(this, BuyerDashBoardActivity:: class.java)
            startActivity(intent)
            Bungee.slideDown(this)

            /*val users: HashMap<String, String> = HashMap<String, String>()
            users.put("username", name)
            users.put("email", email)
            users.put("password", password)

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){task: Task<AuthResult> ->

                        if (task.isSuccessful) {

                            uploadImage()

                            firestore.collection("users")
                                    .document(currentUser)
                                    .set(users)
                                    .addOnSuccessListener(this){

                                        val intent = Intent(this, BuyerDashBoardActivity:: class.java)
                                        intent.putExtra("name", name)
                                        intent.putExtra("email", email)
                                        intent.putExtra("password", password)
                                        startActivity(intent)

                                        //showToast("Created")
                                    }
                        } else
                            showToast("Failed")
                    }*/
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == Image_Request_Code && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            imageUri = data.data
            Picasso.with(this@UploadPhotoActivity).load(imageUri).centerCrop().resize(500, 500).into(signUpImage)

        }
    }

    fun uploadImage(){

        if (imageUri != null){

            var fileRef: StorageReference = storage.child(currentUser + "." + getFileExtension(imageUri))

            fileRef.putFile(imageUri)
                    .addOnSuccessListener {

                        fileRef.downloadUrl
                                .addOnSuccessListener { uri ->

                                    val map: HashMap<String, String> = HashMap<String, String>()
                                    map.put("imageUrl", uri.toString())

                                    firestore.collection("users")
                                            .document(currentUser)
                                            .update(map as Map<String, String>)
                                }
                        showToast("Image uploaded!")
                    }

        }
        else{
            showToast("No image selected!")
        }

    }

    private fun getFileExtension(uri: Uri): String? {
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(uri))
    }

    fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}