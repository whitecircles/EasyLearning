package com.example.easylearning;

import android.content.Intent;
import android.os.Bundle;

import com.example.easylearning.unused.SubActivityToCreate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();

        if (db == null) {
            AppDatabase dbnew = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database").build();
            db = dbnew;
        }

        WordDao itemsDao = db.wordDao();

        LiveData<List<Item>> liveData = (LiveData<List<Item>>) itemsDao.getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        Item item = new Item();
        item.setLanguage(true);
        item.setSlovo("начните изучать английский.");
        item.setTheword("start learning English.");
        List<Item> item1 = new ArrayList<Item>();
        item1.add(item);
        RecAdapter adapter = new RecAdapter(getApplicationContext(), item1);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                            liveData.observe(MainActivity.this, new Observer<List<Item>>() {
                                @Override
                                public void onChanged(List<Item> items) {

                                        items.get(position).setLanguage(!items.get(position).isLanguage());

                                }
                            });
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

            liveData.observe(this, new Observer<List<Item>>() {
                @Override
                public void onChanged(@Nullable List<Item> value) {

                    if (value != null) {
                        // создаем адаптер
                        RecAdapter adapter = new RecAdapter(getApplicationContext(), value);
                        // устанавливаем для списка адаптер
                        recyclerView.setAdapter(adapter);
                    }





                }
            });



        FloatingActionButton fab = findViewById(R.id.fab);
        Intent intent = new Intent(this, SubActivityToCreate.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });

        Button btnForEnSw = findViewById(R.id.toEn);
        btnForEnSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.observe(MainActivity.this, new Observer<List<Item>>() {
                    @Override
                    public void onChanged(List<Item> items) {
                        for(int i = 0; i < items.size(); i++)
                        {
                            items.get(i).setLanguage(true);
                        }
                    }
                });


            }
        });

        Button btnForRuSw = findViewById(R.id.toEn);
        btnForRuSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.observe(MainActivity.this, new Observer<List<Item>>() {
                    @Override
                    public void onChanged(List<Item> items) {
                        for(int i = 0; i < items.size(); i++)
                        {
                            items.get(i).setLanguage(false);
                        }
                    }
                });


            }
        });






    }




}
