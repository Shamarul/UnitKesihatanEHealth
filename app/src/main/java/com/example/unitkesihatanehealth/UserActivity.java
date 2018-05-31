package com.example.unitkesihatanehealth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // database instance
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        // interface components
        final RadioButton critical = findViewById(R.id.radioButton);
        final RadioButton mild = findViewById(R.id.radioButton2);
        final EditText symptoms = findViewById(R.id.editText);
        Button submit = findViewById(R.id.button1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get value from interface component
                final String symptomsTxt = symptoms.getText().toString().trim();

                // check for condition
                String condition = "";

                if (critical.isChecked()) {
                    condition = "critical";
                    Toast.makeText(getApplicationContext(), "Critical checked", Toast.LENGTH_SHORT).show();
                }
                else if (mild.isChecked()) {
                    condition = "mild";
                    Toast.makeText(getApplicationContext(), "Mild checked", Toast.LENGTH_SHORT).show();

                }
                // add data into database
                myRef.child("cases/" + condition).setValue(new CasesClass(symptomsTxt, condition));
                Toast.makeText(getApplicationContext(), "Report successfully sent.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
