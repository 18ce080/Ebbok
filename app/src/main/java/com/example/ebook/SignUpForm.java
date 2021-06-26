package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpForm extends AppCompatActivity {

    EditText textEmail,textPassword,textConfirmPassword;
    Button buttonRegister;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);
        //getSupportActionBar().setTitle("SignUp");
        textEmail=(EditText)findViewById(R.id.textEmail);
        textPassword=(EditText)findViewById(R.id.textPassword);
        textConfirmPassword=(EditText)findViewById(R.id.textConfirmPassword);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
       progressBar=(ProgressBar)findViewById(R.id.progressBar);


        firebaseAuth= FirebaseAuth.getInstance();



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String Confirm_Password=textConfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(SignUpForm.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){

                    Toast.makeText(SignUpForm.this,"Please Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Confirm_Password)){

                    Toast.makeText(SignUpForm.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(SignUpForm.this,"Password too Short.",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(view.VISIBLE);

                if(password.equals(Confirm_Password))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpForm.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                   progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        setEmailVerification();
                                       // Toast.makeText(SignUpForm.this,"Registration Complete.",Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(SignUpForm.this,"AuthentiCation Faild. ",Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }
                else
                {
                    Toast.makeText(SignUpForm.this,"Password Does Not Match.",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }


            }
        });
    }
    private void setEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(SignUpForm.this, "Verification Mail sent.", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                    }
                    else
                    {
                        Toast.makeText(SignUpForm.this, "Server Is Busy.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }



}
