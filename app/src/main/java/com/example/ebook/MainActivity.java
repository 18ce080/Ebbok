package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.internal.view.SupportMenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
     String book="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,Login_Activity.class));}
        return true;

    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do You Want to Exit ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        // MainActivity.super.onBackPressed();


    }

    public void btn_c(View view) {
        book="C lang";
        Intent intent=new Intent(MainActivity.this,Books_Of_C.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
    public void btncpp(View view) {
        book="C++ lang";
       // startActivity(new Intent(getApplicationContext(),Books_Of_Cpp.class));
        Intent intent=new Intent(MainActivity.this,Books_Of_C.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
    public void btnjava(View view) {
        book="java lang";
       // startActivity(new Intent(getApplicationContext(),Books_Of_Java.class));
        Intent intent=new Intent(MainActivity.this,Books_Of_C.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }
    public void btnpy(View view) {
       // Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
        book="py lang";
        // startActivity(new Intent(getApplicationContext(),Books_Of_Java.class));
        Intent intent=new Intent(MainActivity.this,Books_Of_C.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }

}

