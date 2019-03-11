package com.example.tiktalk.UI.Activities.Buyer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.*
import com.example.tiktalk.R
import com.example.tiktalk.UI.Activities.ResetPasswordActivity
import com.example.tiktalk.UI.Activities.Seller.SellerLoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import spencerstudios.com.bungeelib.Bungee

class BuyerLoginActivity: AppCompatActivity() {

    val RC_SIGN_IN = 123
    val TAG = "BuyerLoginActivity"

    lateinit var profilePicture: ImageView
    lateinit var signupBtn: TextView
    lateinit var googleSignInBtn: Button
    lateinit var emailCardView: CardView
    lateinit var passwordCardView: CardView
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginBtn: Button
    lateinit var partnerLoginBtn: TextView
    lateinit var forgotPasswordBtn: TextView

    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        googleSignInBtn = findViewById(R.id.googleSignInBtn)
        signupBtn = findViewById(R.id.signup_textView)
        emailCardView = findViewById(R.id.login_email_cardview)
        passwordCardView = findViewById(R.id.login_password_cardview)
        emailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_btn)
        partnerLoginBtn = findViewById(R.id.partnerLogin_Btn)
        forgotPasswordBtn = findViewById(R.id.forget_textView)
        //profilePicture = findViewById(R.id.picture) as ImageView

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInBtn.setOnClickListener {

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        signupBtn.setOnClickListener {

            var intent = Intent(this, BuyerSignupActivity::class.java);
            startActivity(intent)
            Bungee.slideUp(this)
        }

        passwordEditText.setOnClickListener {

            passwordCardView.cardElevation = 15f
            emailCardView.cardElevation = 0f
        }

        emailEditText.setOnClickListener {

            passwordCardView.cardElevation = 0f
            emailCardView.cardElevation = 15f
        }

        emailEditText.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus){

                emailCardView.cardElevation = 10f
                passwordCardView.cardElevation = 0f
            }
        }

        passwordEditText.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus){

                passwordCardView.cardElevation = 10f
                emailCardView.cardElevation = 0f
            }
        }

        loginBtn.setOnClickListener {

            login()
        }

        partnerLoginBtn.setOnClickListener {

            val intent = Intent(this, SellerLoginActivity::class.java)
            startActivity(intent)

        }

        forgotPasswordBtn.setOnClickListener {

            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {

        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()

        val intent = Intent(this, BuyerDashBoardActivity::class.java)
        startActivity(intent)

        /*if (TextUtils.isEmpty(email)){
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(emailCardView);
            return
        }

        if (TextUtils.isEmpty(password)){
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(passwordCardView);
            return
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->

                    if (task.isSuccessful){

                        val intent = Intent(this, BuyerSignupActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()

                    }
                    else
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }*/
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                //val string = account?.zab()
                val personPhotoUrl = account?.photoUrl
                //Glide.with(this).load(personPhotoUrl).into(profilePicture)
//                Picasso.with(this).load(personPhotoUrl).into(profilePicture)
//                Toast.makeText(this, account?.id, Toast.LENGTH_SHORT).show()
                Log.i(TAG, account?.idToken)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                //firebaseAuthWithGoogle(null)
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        if (acct != null) {
            Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        }

        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        //Toast.makeText(this, acct.displayName, Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, BuyerDashBoardActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()

                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        //Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    // ...
                }
    }
}