package com.lco.todolast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewDetails extends AppCompatActivity {
    EditText t1, d1;
    Button delete, update;
    String titlese, dessend;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        t1 = findViewById(R.id.t1);
        d1 = findViewById(R.id.d1);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        final Intent i = getIntent();
        String gettitle = i.getStringExtra("title");
        String getdesc = i.getStringExtra("desc");
        final String id = i.getStringExtra("id");
        t1.setText(gettitle);
        d1.setText(getdesc);
        delete.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                delete1(id);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update1(id);
            }
        });
    }

    public void delete1(String id) {
        databaseReference.child("notes").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ViewDetails.this, "Notes deleted here....", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewDetails.this, MainActivity.class);
                startActivity(i);

            }
        });

    }

    public void update1(String id) {
        titlese = t1.getText().toString();
        dessend = d1.getText().toString();
        Todo user = new Todo(id, titlese, dessend);
        databaseReference.child("notes").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ViewDetails.this, "Notes Added here....", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewDetails.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
