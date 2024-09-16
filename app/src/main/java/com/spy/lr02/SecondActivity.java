package com.spy.lr02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        EditText userName = findViewById(R.id.textUserName);

        Button button = findViewById(R.id.button6);
        button.setOnClickListener(v -> {
            // Считываем значение поля
            String text = userName.getText().toString();

            if (text.isEmpty()){setResult(RESULT_CANCELED); finish();}

            // Формируем "пустое" намерение
            Intent intent = new Intent();
            // Добавляем в намерение данные
            intent.putExtra("result",text);

            // Устанавливаем результат
            setResult(RESULT_OK,intent);

            // Закрываем Activity
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED);
    }
}