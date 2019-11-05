package com.example.easylearning;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
}
