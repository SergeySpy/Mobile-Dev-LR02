package com.spy.lr02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText First_Text_Area;
    private EditText Seccond_Text_Area;
    private EditText Third_Text_Area;
    private double First_Number_Int;
    private double Seccond_Number_Int;
    private double Result = 0;
    public static int REQUEST_CODE = 100;

    public void calculate() {
        String First_Number = First_Text_Area.getText().toString();
        First_Number_Int = Double.parseDouble(First_Number);

        String Seccond_Number = Seccond_Text_Area.getText().toString();
        Seccond_Number_Int = Double.parseDouble(Seccond_Number);
    }

    public void handleClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("Авторизация")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Отменено", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_OK) {

                if (data != null) {
                    String text = data.getStringExtra("result");
                    if (text != null) {
                        TextView tv = findViewById(R.id.textView3);
                        tv.setText(text);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        First_Text_Area = findViewById(R.id.editTextNumber);
        Seccond_Text_Area = findViewById(R.id.editTextNumber2);
        Third_Text_Area = findViewById(R.id.editTextNumber3);

        Button button_mul = findViewById(R.id.button);
        button_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Result = First_Number_Int * Seccond_Number_Int;
                Third_Text_Area.setText(String.valueOf(Result));
            }
        });
        Button button_div = findViewById(R.id.button2);
        button_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Result = First_Number_Int / Seccond_Number_Int;
                Third_Text_Area.setText(String.valueOf(Result));
            }
        });
        Button button_add = findViewById(R.id.button3);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Result = First_Number_Int + Seccond_Number_Int;
                Third_Text_Area.setText(String.valueOf(Result));
            }
        });
        Button button_sub = findViewById(R.id.button4);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Result = First_Number_Int - Seccond_Number_Int;
                Third_Text_Area.setText(String.valueOf(Result));
            }
        });
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(Intent.ACTION_SEND);
                TextView tv = findViewById(R.id.textView3);
                String sendText;

                i.setType("text/plain");
                if (!tv.getText().toString().equals("Не авторизован")) {sendText = "Пользователь " + tv.getText().toString() + " получил результат " + String.valueOf(Result);}
                else {sendText = "Полученный результат " + String.valueOf(Result);}
                i.putExtra(Intent.EXTRA_TEXT,sendText);

                startActivity(i);
            }
        });
    }

}