package com.developer.bianca.agendaapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developer.bianca.agendaapplication.Utils.Constants;

import java.io.File;
import java.util.List;

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
        File file = getFileStreamPath(Constants.CONTACTS_FILENAME);
        if(file.length() == 0){
            Toast.makeText(this, "Lista vazia. Adicione um novo contato!", Toast.LENGTH_LONG).show();
            return;
        } else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void removeContacts(View view) {
        File file = new File(getFilesDir(), Constants.CONTACTS_FILENAME);
            if(file.exists()){
                file.delete();
                Toast.makeText(this, "Agenda zerada com sucesso!", Toast.LENGTH_LONG).show();
            }
            try{
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
}