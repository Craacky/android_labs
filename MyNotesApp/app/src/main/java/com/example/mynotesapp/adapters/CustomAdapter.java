package com.example.mynotesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.example.mynotesapp.data.Note;
import com.example.mynotesapp.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Note> {

    public CustomAdapter(@NonNull Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }

        Note note = getItem(position);

        TextView noteNumber = convertView.findViewById(R.id.noteNumber);
        TextView noteDescription = convertView.findViewById(R.id.noteDescription);

        assert note != null;
        noteNumber.setText(String.valueOf(note.getId()));
        noteDescription.setText(note.getDescription());

        return convertView;
    }
}
