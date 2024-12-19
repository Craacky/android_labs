package by.craacky.taxi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SetPathActivity extends AppCompatActivity {
    private EditText pointAStreet, pointANumber, pointBStreet, pointBNumber;
    private Button setPathButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_path);

        pointAStreet = findViewById(R.id.pointAStreet);
        pointANumber = findViewById(R.id.pointANumber);
        pointBStreet = findViewById(R.id.pointBStreet);
        pointBNumber = findViewById(R.id.pointBNumber);
        setPathButton = findViewById(R.id.setPathButton);

        // Предустановленные значения
        pointAStreet.setText("Dolma Street");
        pointANumber.setText("1");
        pointBStreet.setText("Park Avenue");
        pointBNumber.setText("5");

        setPathButton.setOnClickListener(v -> {
            String fromPoint = pointAStreet.getText().toString() + ", " + pointANumber.getText().toString();
            String toPoint = pointBStreet.getText().toString() + ", " + pointBNumber.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("fromPoint", fromPoint);
            resultIntent.putExtra("toPoint", toPoint);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}