package com.lco.todolast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addnotes extends AppCompatActivity {
    Button bsuubmit;
    EditText title,desc;
    DatabaseReference mDatabase;
    String titletxt,desctxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        title=findViewById(R.id.edtitle);
        desc=findViewById(R.id.eddesc);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        bsuubmit=findViewById(R.id.btnsubmit);
        bsuubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titletxt=title.getText().toString();
                desctxt=desc.getText().toString();
                writeNewUser(titletxt,desctxt);
                startActivity(new Intent(Addnotes.this,MainActivity.class));
            }
        });

    }
    public void writeNewUser(String title,String desc){
        String UserId=mDatabase.push().getKey();
        Todo todo=new Todo(UserId,title,desc);
        mDatabase.child("notes").child(UserId).setValue(todo);


    }
}
