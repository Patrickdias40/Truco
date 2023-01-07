package com.example.truquinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Partidas extends AppCompatActivity {

    int pontos_nos = 0;
    int pontos_eles = 0;
    int pt_maxima = 0;

    String nome = "teste";

    TextView titulo;
    TextView texto_nos;
    TextView texto_eles;

    Button mais_nos;
    Button mais_eles;
    Button menos_nos;
    Button menos_eles;

    Button bt_salvar;
    Button bt_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidas);
        titulo = findViewById(R.id.title);
        texto_nos = findViewById(R.id.pontos_nos);
        texto_eles = findViewById(R.id.pontos_eles);
        mais_nos = findViewById(R.id.mais_nos);
        mais_eles = findViewById(R.id.mais_eles);
        menos_nos = findViewById(R.id.menos_nos);
        menos_eles = findViewById(R.id.menos_eles);

        bt_salvar = findViewById(R.id.bt_salvar);
        bt_sair = findViewById(R.id.bt_sair);

        titulo.setText(nome);
        texto_nos.setText(String.valueOf(pontos_nos));
        texto_eles.setText(String.valueOf(pontos_eles));

        mais_nos.setOnClickListener(this::adiciona_nos);
        mais_eles.setOnClickListener(this::adiciona_eles);
        menos_nos.setOnClickListener(this::retira_nos);
        menos_eles.setOnClickListener(this::retira_eles);
        bt_salvar.setOnClickListener(this::salva_e_sai);
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
    public void salva_e_sai(View view){
        long id_banco = com.example.truquinho.sqlHelper.getInstance(Partidas.this).addPartida(nome, pt_maxima, pontos_nos, pontos_eles);
        if (id_banco>0) {
            Toast.makeText(Partidas.this, R.string.salvo, Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(Partidas.this, MainActivity.class);
        startActivity(intent);
    }
}