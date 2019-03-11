package com.example.tiktalk.UI.Activities.Buyer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tiktalk.R
import com.example.tiktalk.UI.Activities.Seller.SellerSignupActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import spencerstudios.com.bungeelib.Bungee

class BuyerSignupActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    lateinit var nameEditTxt: EditText
    lateinit var emailEditTxt: EditText
    lateinit var passwordEditTxt: EditText
    lateinit var signUpBtn: Button
    lateinit var partnerSignUpBtn: TextView
    lateinit var signinTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_signup)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        nameEditTxt = findViewById(R.id.signup_name)
        emailEditTxt = findViewById(R.id.signup_email)
        passwordEditTxt = findViewById(R.id.signup_password)
        signUpBtn = findViewById(R.id.signup_btn)
        partnerSignUpBtn = findViewById(R.id.partnerSignup_Btn)
        signinTextView = findViewById(R.id.signin_textView)

        signUpBtn.setOnClickListener {

            signUp()
        }

        partnerSignUpBtn.setOnClickListener {

            val intent = Intent(this, SellerSignupActivity::class.java)
            startActivity(intent)
        }

        signinTextView.setOnClickListener {

            val intent = Intent(this, BuyerLoginActivity::class.java)
            startActivity(intent)
            Bungee.slideUp(this)

        }
    }

    private fun signUp() {

        val name = nameEditTxt.text.toString()
        val email = emailEditTxt.text.toString()
        val password = passwordEditTxt.text.toString()

        val intent = Intent(this, UploadPhotoActivity:: class.java)
        intent.putExtra("name", name)
        intent.putExtra("email", email)
        intent.putExtra("password", password)
        startActivity(intent)

        /*val users: HashMap<String, String> = HashMap<String, String>()
        users.put("username", name)
        users.put("email", email)
        users.put("password", password)

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser?.uid

                        firestore.collection("users")
                                .document(currentUser!!)
                                .set(users)
                                .addOnSuccessListener(this){

                                    val intent = Intent(this, UploadPhotoActivity:: class.java)
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

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}