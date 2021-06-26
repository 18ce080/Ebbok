package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_Activity extends AppCompatActivity {

    EditText textEmail, textPassword;
    Button btn_login;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    // SignInButton signin;
FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        //getSupportActionBar().setTitle("Login");
firebaseUser=firebaseAuth.getInstance().getCurrentUser();
if(firebaseUser!=null)
{
    startActivity(new Intent(getApplicationContext(),MainActivity.class));

}
else
{
        textEmail = (EditText) findViewById(R.id.btn_email);
        textPassword = (EditText) findViewById(R.id.btn_password);
        btn_login = (Button) findViewById(R.id.btn_login);
       progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(Login_Activity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(Login_Activity.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(Login_Activity.this, "Password too Short.", Toast.LENGTH_SHORT).show();
                }
              progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   // progressBar.setVisibility(View.GONE);
                                    checkEmailVerification();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));


                                } else {

                                    Toast.makeText(Login_Activity.this, "Login Failed Or User Not Available.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
    }}
    public void btn_SignUpForm(View view) {
        startActivity(new Intent(getApplicationContext(),SignUpForm.class));
    }

    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        boolean emailFlag=firebaseUser.isEmailVerified();
        if(emailFlag)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        else
        {
            Toast.makeText(this, "Verify Your Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }

//    private void startActivities(Intent intent) {
//
}
