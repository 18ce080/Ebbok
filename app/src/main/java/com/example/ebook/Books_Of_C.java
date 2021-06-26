package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Books_Of_C extends AppCompatActivity {

    List<String> list=new ArrayList<>();
    Spinner spinner;
    String book;
String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books__of__c);
        spinner=(Spinner) findViewById(R.id.spinner);
       book =getIntent().getStringExtra("book");
if(book.equals("C lang")) {
    list.clear();
            list.add("-:    Select Book   :-");
            list.add("Anci C");
            list.add("Let Us C");
            list.add("Programming In C");
          //  list.add("BOOK");
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);
        }

        if(book.equals("C++ lang")) {
            list.clear();
            list.add("-:    Select Book   :-");
            list.add("Balaguruswamy OOP with C++");
            list.add("Object Oriented Programming in C++(Robert Lafore)");
            list.add("C++ For Begineers");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }

        if(book.equals("java lang")) {
            list.clear();
            list.add("-:    Select Book   :-");
            list.add("A Programmer Guide to Java SCJP Certification");
            list.add("Head First Java Second Edition");
            list.add("Java The Complete Reference Eighth Edition");
            list.add("OCA OCP Java SE 7 Programmer I and II Study Guide");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }

        if(book.equals("py lang")) {
            list.clear();
            list.add("-:    Select Book   :-");
            list.add("The Python Book The ultimate guide to coding with Python");
            list.add("Python Programming for the Absolute Beginner");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                name=list.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void Go(View view)
    {
        if(name=="-:    Select Book   :-")
        {
            Toast.makeText(this, "Select the book first", Toast.LENGTH_SHORT).show();
        }
        else{
        Intent intent=new Intent(Books_Of_C.this,Main2Activity.class);
        intent.putExtra("book",book);
        intent.putExtra("name",name);
        startActivity(intent);
    }}
}


