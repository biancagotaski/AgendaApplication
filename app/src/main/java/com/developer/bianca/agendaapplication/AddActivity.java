package com.developer.bianca.agendaapplication;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AddActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextTelephone;
    EditText editTextEmail;
    EditText editTextCity;

    RecyclerView contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = (EditText) findViewById(R.id.name_edit_text);
        editTextTelephone = (EditText) findViewById(R.id.telephone_edit_text);
        editTextEmail = (EditText) findViewById(R.id.email_edit_text);
        editTextCity = (EditText) findViewById(R.id.city_edit_text);
    }

    //write data on file
    public void saveContact(View view){
        String nameContact = editTextName.getText().toString();
        String telephoneContact = editTextName.getText().toString();
        String emailContact = editTextName.getText().toString();
        String cityContact = editTextName.getText().toString();

        final ListContactCard contactCard = new ListContactCard(nameContact, telephoneContact, emailContact, cityContact);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File outFile = new File(getFilesDir(), MainActivity.CONTACTS_FILENAME);
                            OutputStream outputStream = new FileOutputStream(outFile, true);
                            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                            // escreve no arquivo
                            writer.write("#\n");
                            writer.write(contactCard.toString());

                            writer.close();
                        } catch (final FileNotFoundException exception){
                            AddActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                        } catch (final IOException exception){
                            AddActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
        ).start();

        /*try {
            FileOutputStream fileOutputStream = openFileOutput("agenda.txt", MODE_PRIVATE);
            fileOutputStream.write(nameContact.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}