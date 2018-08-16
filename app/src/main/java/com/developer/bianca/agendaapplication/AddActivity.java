package com.developer.bianca.agendaapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.bianca.agendaapplication.Utils.Constants;

import java.io.FileOutputStream;

public class AddActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextTelephone;
    EditText editTextEmail;
    EditText editTextCity;

    FileOutputStream fileOutputStream;

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


        try {
            /*File outFile = new File(getFilesDir(), AddActivity.CONTACTS_FILENAME);
            OutputStream outputStream = new FileOutputStream(outFile, true);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);*/

            fileOutputStream = openFileOutput(String.valueOf(Constants.CONTACTS_FILENAME), Context.MODE_APPEND | Context.MODE_PRIVATE);

            Person newContact = new Person(editTextName.getText().toString(), editTextTelephone.getText().toString(), editTextEmail.getText().toString(), editTextCity.getText().toString());
            newContact.setName(editTextName.getText().toString());
            newContact.setTelephone(editTextTelephone.getText().toString());
            newContact.setEmail(editTextEmail.getText().toString());
            newContact.setCity(editTextCity.getText().toString());

            // escreve no arquivo
            //writer.write("#\n");
            //writer.write(contactCard.toString());

            fileOutputStream.write("#\n".getBytes());
            fileOutputStream.write(newContact.getName().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(newContact.getTelephone().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(newContact.getEmail().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(newContact.getCity().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.close();

            //writer.close();

            Toast.makeText(getApplicationContext(), "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO: Implementar botão para dar clean no formulário, para adicionar um novo contato.
}