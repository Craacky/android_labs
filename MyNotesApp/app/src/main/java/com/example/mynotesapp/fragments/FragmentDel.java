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

public class FragmentDel extends Fragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_del, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        EditText editText = view.findViewById(R.id.editTextDel);
        Button buttonDel = view.findViewById(R.id.buttonDel);

        buttonDel.setOnClickListener(v -> {
            String idText = editText.getText().toString().trim();
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);

                if (databaseHelper.deleteNoteById(id)) {
                    Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();

                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).refreshShowFragment();
                    }
                } else {
                    Toast.makeText(getContext(), "ID not found", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            } else {
                Toast.makeText(getContext(), "ID cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
