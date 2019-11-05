package com.example.easylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class SubActivityToAnalize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_to_analyze);

        AppDatabase database = App.getInstance().getDatabase();
        WordDao itemsDao = database.wordDao();

        TextView textR = findViewById(R.id.thewordRus);
        TextView textE = findViewById(R.id.thewordEng);

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", 0);
        if (id != 0) {
            Item item = itemsDao.getById(id);
            textE.setText(item.getTheword());
            textR.setText(item.getSlovo());

        }





        Button appl = findViewById(R.id.fabApply);

        appl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                item.setTheword(textE.getText().toString());
                item.setSlovo(textR.getText().toString());
                item.setLanguage(true);
                itemsDao.insert(item);

            }
        });




    }
}
