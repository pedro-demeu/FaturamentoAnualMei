package com.example.faturamentoanualmei;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView saldo;
    private EditText value;
    private NumberPicker numberPicker;
    private RadioGroup radioGroup;
    private Button saveButton;
    private Button charterButton;

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

        saldo = findViewById(R.id.saldoInput);
        value = findViewById(R.id.valueInput);
        numberPicker = findViewById(R.id.numberPicker);
        radioGroup = findViewById(R.id.radioGroup);
        saveButton = findViewById(R.id.buttonSave);
        charterButton = findViewById(R.id.charterButton);
    }
}