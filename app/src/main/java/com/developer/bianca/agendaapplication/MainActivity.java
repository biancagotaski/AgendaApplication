package com.developer.bianca.agendaapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developer.bianca.agendaapplication.Utils.Constants;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addContact(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void listContacts(View view){
        Intent intent = new Intent(this, ListContactActivity.class);
        startActivity(intent);
    }

    public void removeContacts(View view) {
        File file = new File(getFilesDir(), Constants.CONTACTS_FILENAME);
        file.delete();
        Toast.makeText(this, "Agenda zerada com sucesso!", Toast.LENGTH_LONG).show();
            try{
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
}