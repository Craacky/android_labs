package com.example.mynotesapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotesapp.adapters.CustomAdapter;
import com.example.mynotesapp.data.DatabaseHelper;
import com.example.mynotesapp.data.Note;
import com.example.mynotesapp.R;

import java.util.ArrayList;

public class FragmentShow extends Fragment {

    private DatabaseHelper databaseHelper;
    private CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        ListView listView = view.findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(getContext());
        ArrayList<Note> notes = databaseHelper.getAllNotes();

        customAdapter = new CustomAdapter(getContext(), notes);
        listView.setAdapter(customAdapter);

        return view;
    }

    public void refreshNotes() {
        ArrayList<Note> notes = databaseHelper.getAllNotes();
        customAdapter.clear();
        customAdapter.addAll(notes);
        customAdapter.notifyDataSetChanged();
    }

}

