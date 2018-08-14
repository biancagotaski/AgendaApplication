package com.developer.bianca.agendaapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

      List<ListContactCard> listActivities;
      public static final String CONTACTS_FILENAME = "contactsCards.txt";
      RecyclerView contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person defaultPerson = new Person(0,
                "Bianca",
                "(21)96844-7916",
                "gotaskibianca@gmail.com",
                "RJ");
        listActivities = new ArrayList<>();

        for (int i = 1; i < 5; i++){
            listActivities.add(new ListContactCard(defaultPerson.getName(),
                    defaultPerson.getTelephone(), defaultPerson.getEmail(), defaultPerson.getCity()));
        }

        ListContactAdapter adapter = new ListContactAdapter(listActivities);

        contactsList = findViewById(R.id.contacts_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        contactsList.setLayoutManager(llm);

        contactsList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        contactsList.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    final List<ListContactCard> contactCards = new ArrayList<>();
                    File inputFile = new File(getFilesDir(), CONTACTS_FILENAME);
                    try {
                        InputStream inputStream = new FileInputStream(inputFile);
                        InputStreamReader reader = new InputStreamReader(inputStream);
                        // para ler textos de maneira estruturada
                        BufferedReader bufferedReader = new BufferedReader(reader);

                        String line = bufferedReader.readLine();

                        while (line != null) {
                            if (line.equals("#")) {
                                String nameContact = bufferedReader.readLine();
                                //TODO: deal with Date datatype
                                String telephoneContact = bufferedReader.readLine();
                                String emailContact = bufferedReader.readLine();
                                String cityContact = bufferedReader.readLine();
                                ListContactCard contactCard = new ListContactCard(nameContact, telephoneContact, emailContact, cityContact);
                                contactCards.add(contactCard);
                            }
                            line = bufferedReader.readLine();
                        }

                    } catch (final FileNotFoundException exception) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    } catch (final IOException exception) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    contactsList.post(
                            new Runnable() {
                                @Override
                                public void run() {
                                    ListContactAdapter adapter = new ListContactAdapter(listActivities);
                                    contactsList.setAdapter(adapter);
                                    // atualizar visualizção dos elementos da RecyclerView
                                    adapter.notifyDataSetChanged();
                                }
                            }
                    );
                    }
                }
        ).start();
    }

    public void addContact(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void listContacts(View view){
        Intent intent = new Intent(this, ListContactCard.class);
        startActivity(intent);
    }

    public void removeContacts(View view){
        Intent intent = new Intent(this, RemoveActivity.class);
        startActivity(intent);
    }
}
