package com.example.easylearning;

import android.media.MediaRecorder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;

@Entity(tableName = "items")
public class Item {



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    Integer id;

    @ColumnInfo(name = "slovo")
    String slovo;

    @ColumnInfo(name = "word")
    String theword;

    @ColumnInfo(name = "lang")
    boolean language;

    public boolean isLanguage() {
        return language;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlovo() {
        return slovo;
    }

    public void setSlovo(String record) {
        this.slovo = slovo;
    }

    public String getTheword() {
        return theword;
    }

    public void setTheword(String theword) {
        this.theword = theword;
    }
}
