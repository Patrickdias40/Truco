package com.example.truquinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Nova_Partida extends AppCompatActivity {

    EditText editNome;
    EditText editPontos;
    Button btCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_partida);

        editNome = findViewById(R.id.editNome);
        editPontos = findViewById(R.id.editPontos);
        btCriar = findViewById(R.id.bt_criar);

        btCriar.setOnClickListener(this::novaPartida);

    }

    public void novaPartida(View view){
        String nome = editNome.getText().toString();
        int pt_maxima = 0;
        if (nome.equals("")){
            nome = "Sem nome";
        }
        if (editPontos.getText().toString().equals("")){
            pt_maxima = 0;
        } else {
            pt_maxima = Integer.parseInt(editPontos.getText().toString());
        }
        Intent intent = new Intent(Nova_Partida.this, Partidas.class);
        intent.putExtra("valor", "Portugues");
        intent.putExtra("id", 0);
        intent.putExtra("nome", nome);
        intent.putExtra("pt_maxima", pt_maxima);
        startActivity(intent);
    }
}