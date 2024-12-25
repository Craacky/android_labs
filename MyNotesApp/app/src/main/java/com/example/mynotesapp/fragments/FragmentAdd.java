package com.example.mynotesapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotesapp.data.DatabaseHelper;
import com.example.mynotesapp.MainActivity;
import com.example.mynotesapp.R;

public class FragmentAdd extends Fragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        EditText editText = view.findViewById(R.id.editTextAdd);
        Button buttonAdd = view.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> {
            String description = editText.getText().toString().trim();
            if (!description.isEmpty()) {
                databaseHelper.addNote(description);
                Toast.makeText(getContext(), "Note added", Toast.LENGTH_SHORT).show();
                editText.setText("");


                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).refreshShowFragment(); // Обновляем список заметок
                }
            } else {
                Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
