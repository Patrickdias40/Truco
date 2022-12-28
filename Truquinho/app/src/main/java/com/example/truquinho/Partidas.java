package com.example.truquinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Partidas extends AppCompatActivity {

    int pontos_nos = 0;
    int pontos_eles = 0;

    TextView texto_nos;
    TextView texto_eles;

    Button mais_nos;
    Button mais_eles;
    Button menos_nos;
    Button menos_eles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidas);
        texto_nos = findViewById(R.id.pontos_nos);
        texto_eles = findViewById(R.id.pontos_eles);
        mais_nos = findViewById(R.id.mais_nos);
        mais_eles = findViewById(R.id.mais_eles);
        menos_nos = findViewById(R.id.menos_nos);
        menos_eles = findViewById(R.id.menos_eles);

        texto_nos.setText(String.valueOf(pontos_nos));
        texto_eles.setText(String.valueOf(pontos_eles));

        mais_nos.setOnClickListener(this::adiciona_nos);
        mais_eles.setOnClickListener(this::adiciona_eles);
        menos_nos.setOnClickListener(this::retira_nos);
        menos_eles.setOnClickListener(this::retira_eles);
    }
    public void adiciona_nos(View view){
        pontos_nos += 1;
        texto_nos.setText(String.valueOf(pontos_nos));
    }
    public void adiciona_eles(View view){
        pontos_eles += 1;
        texto_eles.setText(String.valueOf(pontos_eles));
    }
    public void retira_nos(View view){
        pontos_nos -= 1;
        texto_nos.setText(String.valueOf(pontos_nos));
    }
    public void retira_eles(View view){
        pontos_eles -= 1;
        texto_eles.setText(String.valueOf(pontos_eles));
    }
}