package com.example.sqlite2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContactActivity extends AppCompatActivity {

    // Variables for edit text, button, strings, and DBHandler
    private EditText firstNameEdt, lastNameEdt, addressEdt, cityEdt, ageEdt;
    private Button updateContactBtn, deleteContactBtn;
    private DBHandler dbHandler;
    String originalFirstName, originalLastName, originalAddress, originalCity;
    int originalAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        // Initializing all variables
        firstNameEdt = findViewById(R.id.idEdtFirstName);
        lastNameEdt = findViewById(R.id.idEdtLastName);
        addressEdt = findViewById(R.id.idEdtAddress);
        cityEdt = findViewById(R.id.idEdtCity);
        ageEdt = findViewById(R.id.idEdtAge);
        updateContactBtn = findViewById(R.id.idBtnUpdateContact);
        deleteContactBtn = findViewById(R.id.idBtnDeleteContact);

        // Initializing DBHandler
        dbHandler = new DBHandler(UpdateContactActivity.this);

        // Getting data from the intent
        originalFirstName = getIntent().getStringExtra("first_name");
        originalLastName = getIntent().getStringExtra("last_name");
        originalAddress = getIntent().getStringExtra("address");
        originalCity = getIntent().getStringExtra("city");
        originalAge = getIntent().getIntExtra("age", 0);

        // Setting data to edit texts
        firstNameEdt.setText(originalFirstName);
        lastNameEdt.setText(originalLastName);
        addressEdt.setText(originalAddress);
        cityEdt.setText(originalCity);
        ageEdt.setText(String.valueOf(originalAge));

        // Update contact button listener
        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updating the contact in the database
                dbHandler.updateContact(
                        originalFirstName,
                        firstNameEdt.getText().toString(),
                        lastNameEdt.getText().toString(),
                        addressEdt.getText().toString(),
                        cityEdt.getText().toString(),
                        Integer.parseInt(ageEdt.getText().toString())
                );
                Toast.makeText(UpdateContactActivity.this, "Contact Updated", Toast.LENGTH_SHORT).show();

                // Navigating back to the main activity
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // Delete contact button listener
        deleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deleting the contact from the database
                dbHandler.deleteContact(originalFirstName);
                Toast.makeText(UpdateContactActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();

                // Navigating back to the main activity
                Intent i = new Intent(UpdateContactActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
