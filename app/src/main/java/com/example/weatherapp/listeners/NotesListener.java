package com.example.weatherapp.listeners;

import com.example.weatherapp.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
