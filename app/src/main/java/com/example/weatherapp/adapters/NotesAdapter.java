package com.example.weatherapp.adapters;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.entities.Note;
import com.example.weatherapp.listeners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    private List<Note> notes;
    private NotesListener notesListener;

    public NotesAdapter(List<Note> notes, NotesListener notesListener) {
        this.notes = notes;
        this.notesListener = notesListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.diary_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                notesListener.onNoteClicked(notes.get(position),position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView textDate;
        LinearLayout layoutNote;
        RoundedImageView imamgeNote;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.text_date);
            imamgeNote = itemView.findViewById(R.id.imageNote);
            layoutNote = itemView.findViewById(R.id.layoutNote);
        }

        void setNote(Note note){

            textDate.setText(note.getDateTime());

            //在展示葉面可以出現
            if (note.getImagePath()!=null){
                imamgeNote.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
                imamgeNote.setVisibility(View.VISIBLE);
            }else {
                imamgeNote.setVisibility(View.GONE);
            }


        }
    }
}
