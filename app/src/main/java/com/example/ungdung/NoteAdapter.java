package com.example.ungdung;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnitemclickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textviewtitle.setText(currentNote.getTitle());
        holder.textViewdescription.setText(currentNote.getDiscription());
        holder.textviewpriority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textviewtitle;
        private TextView textviewpriority;
        private TextView textViewdescription;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textviewtitle = itemView.findViewById(R.id.text_view_title);
            textviewpriority = itemView.findViewById(R.id.text_view_priority);
            textViewdescription = itemView.findViewById(R.id.text_view_descriptions);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onitemclick(notes.get(position));

                }
            });

        }
    }

    public interface OnitemclickListener {
        void onitemclick(Note note);

    }

    public void setOnitemclickListener(OnitemclickListener listener) {
        this.listener = listener;
    }


}
