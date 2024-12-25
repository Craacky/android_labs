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

public class FragmentUpdate extends Fragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        EditText editTextId = view.findViewById(R.id.editTextUpdateId);
        EditText editTextDescription = view.findViewById(R.id.editTextUpdateDescription);
        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(v -> {
            String idText = editTextId.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();
            if (!idText.isEmpty() && !description.isEmpty()) {
                int id = Integer.parseInt(idText);

                if (databaseHelper.updateNoteById(id, description)) {
                    Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).refreshShowFragment();
                    }
                } else {
                    Toast.makeText(getContext(), "ID not found", Toast.LENGTH_SHORT).show();
                }

                editTextId.setText("");
                editTextDescription.setText("");
            } else {
                Toast.makeText(getContext(), "Both fields are required", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
