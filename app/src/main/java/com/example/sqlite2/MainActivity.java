package com.example.sqlite2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEdt, lastNameEdt, addressEdt, cityEdt, ageEdt;
    private Button addContactBtn, viewContactsBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEdt = findViewById(R.id.idEdtFirstName);
        lastNameEdt = findViewById(R.id.idEdtLastName);
        addressEdt = findViewById(R.id.idEdtAddress);
        cityEdt = findViewById(R.id.idEdtCity);
        ageEdt = findViewById(R.id.idEdtAge);
        addContactBtn = findViewById(R.id.idBtnAddContact);
        viewContactsBtn = findViewById(R.id.idBtnViewContacts);

        dbHandler = new DBHandler(MainActivity.this);

        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEdt.getText().toString();
                String lastName = lastNameEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String city = cityEdt.getText().toString();
                int age = Integer.parseInt(ageEdt.getText().toString());

                if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || age == 0) {
                    Toast.makeText(MainActivity.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewContact(firstName, lastName, address, city, age);
                Toast.makeText(MainActivity.this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                firstNameEdt.setText("");
                lastNameEdt.setText("");
                addressEdt.setText("");
                cityEdt.setText("");
                ageEdt.setText("");
            }
        });

        viewContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewContactsActivity.class);
                startActivity(i);
            }
        });
    }
}
