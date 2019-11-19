package com.example.easylearning;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
}
