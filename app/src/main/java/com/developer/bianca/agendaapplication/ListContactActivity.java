package com.developer.bianca.agendaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.developer.bianca.agendaapplication.Utils.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListContactActivity extends AppCompatActivity {

    List<ListContactCard> listActivities;
    ListContactAdapter listContactAdapter;
    RecyclerView contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        listActivities = new ArrayList<>();

        listContactAdapter = new ListContactAdapter(listActivities);
        contactsList = findViewById(R.id.contacts_list);
        contactsList.setLayoutManager(new LinearLayoutManager(this));
        contactsList.setAdapter(listContactAdapter);

        contactsList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        final List<ListContactCard> contactCards = new ArrayList<>();
                        File inputFile = new File(getFilesDir(), Constants.CONTACTS_FILENAME);
                        try {
                            InputStream inputStream = new FileInputStream(inputFile);
                            InputStreamReader reader = new InputStreamReader(inputStream);
                            // para ler textos de maneira estruturada
                            BufferedReader bufferedReader = new BufferedReader(reader);

                            String line = bufferedReader.readLine();

                            while (line != null) {
                                if (line.equals("#")) {
                                    String nameContact = bufferedReader.readLine();
                                    String telephoneContact = bufferedReader.readLine();
                                    String emailContact = bufferedReader.readLine();
                                    String cityContact = bufferedReader.readLine();
                                    ListContactCard contactCard = new ListContactCard(nameContact, telephoneContact, emailContact, cityContact);
                                    contactCards.add(contactCard);
                                }
                                line = bufferedReader.readLine();
                            }

                        } catch (final FileNotFoundException exception) {
                            ListContactActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ListContactActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                        } catch (final IOException exception) {
                            ListContactActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ListContactActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        contactsList.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ListContactAdapter adapter = new ListContactAdapter(contactCards);
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
}
