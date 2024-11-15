package com.example.faturamentoanualmei;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private EditText companyName;
    private Button saveCompanyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        companyName = findViewById(R.id.companyNameInput);
        saveCompanyButton = findViewById(R.id.saveCompanyButton);

        saveCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeFantasia = companyName.getText().toString();

                if (!nomeFantasia.isEmpty()){
                    getSharedPreferences(MainActivity.MyDataFile, Context.MODE_PRIVATE)
                            .edit().putString("nomeFantasia", nomeFantasia).apply();
                }
            }
        });
    }
}