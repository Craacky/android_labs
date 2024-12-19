package by.craacky.taxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {
    private TextView userInfo, pathInfo;
    private Button setPathButton, callTaxiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        userInfo = findViewById(R.id.userInfo);
        pathInfo = findViewById(R.id.pathInfo);
        setPathButton = findViewById(R.id.setPathButton);
        callTaxiButton = findViewById(R.id.callTaxiButton);

        SharedPreferences preferences = getSharedPreferences("TaxiAppPrefs", MODE_PRIVATE);
        String firstName = preferences.getString("firstName", "");
        String lastName = preferences.getString("lastName", "");
        String phoneNumber = preferences.getString("phoneNumber", "");

        userInfo.setText(String.format("Name: %s %s\nPhone: %s", firstName, lastName, phoneNumber));
        callTaxiButton.setEnabled(false);

        setPathButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserInfoActivity.this, SetPathActivity.class);
            startActivityForResult(intent, 1);
        });

        callTaxiButton.setOnClickListener(v -> {
            Toast.makeText(UserInfoActivity.this, "Taxi on ride!", Toast.LENGTH_SHORT).show();
            callTaxiButton.setEnabled(false);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String fromPoint = data.getStringExtra("fromPoint");
            String toPoint = data.getStringExtra("toPoint");
            pathInfo.setText(String.format("From: %s\nTo: %s\nETA: 5-10 min", fromPoint, toPoint));
            callTaxiButton.setEnabled(true);
        }
    }
}
