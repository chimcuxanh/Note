package com.example.ungdung;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteReponsitory reponsitory;

    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        reponsitory = new NoteReponsitory(application);
        allNotes = reponsitory.getAllNotes();
    }

    public  void insert(Note note){
        reponsitory.insert(note);
    }
    public  void update(Note note){
        reponsitory.update(note);
    }

    public  void delete(Note note){

        reponsitory.delete(note);
    }
    public  void deleteAllNotes(){
        reponsitory.deleteAllNotes();
    }
    public LiveData<List<Note>> getAllNotes(){
        return  allNotes;
    }
}
