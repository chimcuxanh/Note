package com.example.ungdung;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1  )
public abstract class Notedatabase extends RoomDatabase {

    private static  Notedatabase instance;

    public abstract  NoteDao noteDao();

    public static synchronized Notedatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Notedatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback =  new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private  static  class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private PopulateDbAsyncTask(Notedatabase db){
            noteDao = db.noteDao();
        }

        @Override
        // insert khac voi clip
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 4","Discription",1));
            noteDao.insert(new Note("Title 4","Discription",2));
            noteDao.insert(new Note("Title 4","Discription",3));
            return null;
        }
    }
}
