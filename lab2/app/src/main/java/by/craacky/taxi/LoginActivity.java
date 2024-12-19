package by.craacky.taxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText firstNameInput, lastNameInput, phoneNumberInput;
    private Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        registrationButton = findViewById(R.id.registrationButton);

        // Устанавливаем начальный текст для номера телефона
        phoneNumberInput.setText("+375");
        phoneNumberInput.setInputType(InputType.TYPE_CLASS_PHONE);

        // Добавляем TextWatcher для форматирования номера
        phoneNumberInput.addTextChangedListener(new TextWatcher() {
            private boolean isEditing = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Нет действий до изменений
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isEditing) return;

                isEditing = true;

                String input = s.toString();
                String formatted = "+375";

                // Убираем все лишние символы кроме цифр
                input = input.replaceAll("[^0-9]", "");

                // Ограничиваем длину цифр после +375
                if (input.length() > 12) {
                    input = input.substring(0, 12); // +375 (2 цифры в скобках, 3-3-2 формат)
                }

                // Форматируем по шаблону +375(xx)-xxx-xx-xx
                if (input.length() >= 3) {
                    formatted += "(" + input.substring(3, Math.min(5, input.length())) + ")";
                }
                if (input.length() >= 6) {
                    formatted += "-" + input.substring(5, Math.min(8, input.length()));
                }
                if (input.length() >= 9) {
                    formatted += "-" + input.substring(8, Math.min(10, input.length()));
                }
                if (input.length() >= 11) {
                    formatted += "-" + input.substring(10);
                }

                phoneNumberInput.setText(formatted);
                phoneNumberInput.setSelection(formatted.length());

                isEditing = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Нет действий после изменений
            }
        });

        // Сохранение данных через SharedPreferences
        SharedPreferences preferences = getSharedPreferences("TaxiAppPrefs", MODE_PRIVATE);
        String savedFirstName = preferences.getString("firstName", "");
        String savedLastName = preferences.getString("lastName", "");
        String savedPhoneNumber = preferences.getString("phoneNumber", "");

        // Восстановление данных
        firstNameInput.setText(savedFirstName);
        lastNameInput.setText(savedLastName);
        phoneNumberInput.setText(TextUtils.isEmpty(savedPhoneNumber) ? "+375" : savedPhoneNumber);

        if (!TextUtils.isEmpty(savedFirstName) && !TextUtils.isEmpty(savedLastName) && !TextUtils.isEmpty(savedPhoneNumber)) {
            registrationButton.setText("Log In");
        }

        registrationButton.setOnClickListener(v -> {
            String firstName = firstNameInput.getText().toString().trim();
            String lastName = lastNameInput.getText().toString().trim();
            String phoneNumber = phoneNumberInput.getText().toString().trim();

            // Проверка на пустые поля и правильную длину номера телефона
            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || phoneNumber.length() < 18) {
                Toast.makeText(LoginActivity.this, "Please fill all fields and ensure phone number is correct!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Сохранение данных
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("firstName", firstName);
            editor.putString("lastName", lastName);
            editor.putString("phoneNumber", phoneNumber);
            editor.apply();

            // Переход на вторую активность
            Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
            startActivity(intent);
        });
    }
}
