package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView myPdf;
    DatabaseReference databaseReference;
    List<upload> uploadPDFS;

    private FirebaseDatabase firebaseDatabase;
    ArrayAdapter adapter;
//upload Upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final String book=getIntent().getStringExtra("book");
        final String name=getIntent().getStringExtra("name");
        //final String sub=getIntent().getStringExtra("sub");
        //Upload=new upload();
        myPdf=(ListView)findViewById(R.id.listview1);
        uploadPDFS=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference(book).child(name);
        adapter=new ArrayAdapter<>(this,R.layout.custom_layout_1,uploadPDFS);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadPDFS.clear();
                if(dataSnapshot.exists())
                {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        // Upload=postSnapshot.getValue(upload.class);
                        uploadPDFS.add(new upload(postSnapshot.child("name").getValue(String.class),postSnapshot.child("pdf").getValue(String.class)));

                    }}




                final String[] Name = new String[uploadPDFS.size()];
                for (int i = 0; i < Name.length; i++) {
                    Name[i] = uploadPDFS.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Name) {


                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
//
//                           LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            View view= inflater.inflate(R.layout.custom_layout1,parent,false);
                        View  view = super.getView(position, convertView, parent);
                        TextView myText = (TextView) view.findViewById(android.R.id.text1);
                        myText.setText(Name[position]);
                        myText.setTextColor(Color.BLACK);
                        return view;

                    }
                };

                myPdf.setAdapter(adapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        myPdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Uri path = Uri.parse(uploadPDFS.get(i).getPdf());
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try{
                    startActivity(pdfIntent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(Main2Activity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

}

