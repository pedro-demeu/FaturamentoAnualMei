package com.example.faturamentoanualmei;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public static final String MyDataFile = "My Data File";
    private TextView saldo;
    private EditText value;
    private NumberPicker numberPicker;
    private RadioGroup radioGroup;
    private Button saveButton;
    private Button charterButton;


    private void adicionarValor(int ano, float valor){
        SharedPreferences sp = getSharedPreferences(MyDataFile, Context.MODE_PRIVATE);
        float valorAtual = sp.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual + valor;

        sp.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }
    private void exibirSaldo(int ano){
        SharedPreferences sp = getSharedPreferences(MyDataFile, Context.MODE_PRIVATE);
        float valor = sp.getFloat(String.valueOf(ano), 0);
        saldo.setText(String.format("R$ %f", valor));
    }
    private void excluirValor(int ano, float valor){
        SharedPreferences sp = getSharedPreferences(MyDataFile, Context.MODE_PRIVATE);
        float valorAtual = sp.getFloat(String.valueOf(ano), 0);
        float novoValor = valorAtual - valor;

        if (novoValor < 0) {
            novoValor = 0;
        }

        sp.edit().putFloat(String.valueOf(ano), novoValor).apply();
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

        saldo = findViewById(R.id.saldoInput);
        value = findViewById(R.id.valueInput);
        numberPicker = findViewById(R.id.numberPicker);
        radioGroup = findViewById(R.id.radioGroup);
        saveButton = findViewById(R.id.buttonSave);
        charterButton = findViewById(R.id.charterButton);

        numberPicker.setMinValue(2015);
        numberPicker.setMaxValue(2030);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                exibirSaldo(newVal);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (value.getText().toString().isEmpty()) return;

                float valor = Float.parseFloat(value.getText().toString());
                int ano = numberPicker.getValue();
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (checkedRadioButtonId == R.id.radioBtnAdd) {
                    adicionarValor(ano, valor);
                } else if (checkedRadioButtonId == R.id.radioBtnExcluir) {
                    excluirValor(ano, valor);
                }

                exibirSaldo(ano);
            }
        });
    }

}