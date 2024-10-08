package com.example.sqlite2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewContactsActivity extends AppCompatActivity {

    private ArrayList<ContactModal> contactModalArrayList;
    private DBHandler dbHandler;
    private ContactRVAdapter contactRVAdapter;
    private RecyclerView contactsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        contactModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewContactsActivity.this);

        contactModalArrayList = dbHandler.readContacts();
        contactRVAdapter = new ContactRVAdapter(contactModalArrayList, ViewContactsActivity.this);
        contactsRV = findViewById(R.id.idRVContacts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewContactsActivity.this, RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);
        contactsRV.setAdapter(contactRVAdapter);
    }
}
