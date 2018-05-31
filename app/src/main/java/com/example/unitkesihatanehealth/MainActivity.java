package com.example.unitkesihatanehealth;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = findViewById(R.id.editText1);
        final EditText password = findViewById(R.id.editText2);
        final Button login = findViewById(R.id.button1);
        final Button register = findViewById(R.id.button2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("users/" + username.getText().toString().trim() + "/password").exists()) {

                            if (dataSnapshot.child("users/" + username.getText().toString().trim() + "/password").getValue().toString().equals(password.getText().toString().trim())) {



                                if (dataSnapshot.child("users/" + username.getText().toString().trim() + "/privilege").getValue().toString().equals("staff")) {

                                    Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (dataSnapshot.child("users/" + username.getText().toString().trim() + "/privilege").getValue().toString().equals("user")) {

                                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                Toast.makeText(getApplicationContext(), "Log In Success", Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                            }
                        }

                        else {

                            Log.d("assalam", "No user found");
                            Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Log.w("assalam", "Failed to read password. " + databaseError.toException());

                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
