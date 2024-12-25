package com.example.mynotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private ArrayList<Note> notesList = new ArrayList<>();
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new NotesDatabaseHelper(this).getWritableDatabase();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            startActivity(intent);
        });

        loadNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        notesList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM notes", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            notesList.add(new Note(id, title, content));
        }
        cursor.close();

        notesAdapter = new NotesAdapter(notesList, this::onNoteClick, this::onDeleteClick);
        recyclerView.setAdapter(notesAdapter);
    }

    private void onNoteClick(Note note) {
        Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
        intent.putExtra("NOTE_ID", note.getId());
        startActivity(intent);
    }

    private void onDeleteClick(Note note) {
        database.execSQL("DELETE FROM notes WHERE id=?", new Object[]{note.getId()});
        loadNotes();
    }
}
