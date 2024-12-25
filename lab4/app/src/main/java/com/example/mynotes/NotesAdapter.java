package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private ArrayList<Note> notesList;
    private OnNoteClickListener noteClickListener;
    private OnDeleteClickListener deleteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Note note);
    }

    public NotesAdapter(ArrayList<Note> notesList, OnNoteClickListener noteClickListener,
                        OnDeleteClickListener deleteClickListener) {
        this.notesList = notesList;
        this.noteClickListener = noteClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.itemView.setOnClickListener(v -> noteClickListener.onNoteClick(note));
        holder.deleteImageView.setOnClickListener(v -> deleteClickListener.onDeleteClick(note));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView deleteImageView;

        NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            deleteImageView = itemView.findViewById(R.id.delete_image_view);
        }
    }
}
