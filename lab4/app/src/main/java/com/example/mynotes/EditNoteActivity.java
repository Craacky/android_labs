package com.example.mynotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText titleEditText, contentEditText;
    private SQLiteDatabase database;
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEditText = findViewById(R.id.title_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);

        Button saveButton = findViewById(R.id.save_button);

        database = new NotesDatabaseHelper(this).getWritableDatabase();

        noteId = getIntent().getIntExtra("NOTE_ID", -1);

        if (noteId != -1) {
            loadNote();
            saveButton.setOnClickListener(v -> updateNote());
        } else {
            saveButton.setOnClickListener(v -> addNote());
        }
    }

    private void loadNote() {
        Cursor cursor = database.rawQuery("SELECT * FROM notes WHERE id=?", new String[]{String.valueOf(noteId)});

        if (cursor.moveToFirst()) {
            titleEditText.setText(cursor.getString(1));
            contentEditText.setText(cursor.getString(2));
        }

        cursor.close();
    }

    private void addNote() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        database.execSQL("INSERT INTO notes (title, content) VALUES (?, ?)", new Object[]{title, content});

        finish();
    }

    private void updateNote() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        database.execSQL("UPDATE notes SET title=?, content=? WHERE id=?", new Object[]{title, content, noteId});

        finish();
    }
}
