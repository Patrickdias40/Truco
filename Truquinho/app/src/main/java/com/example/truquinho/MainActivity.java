package com.example.truquinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button regras;
    Button partidas;
    Button rapida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regras = findViewById(R.id.bt_Regras);
        partidas = findViewById(R.id.bt_Partida);
        rapida = findViewById(R.id.rapida);

        regras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Regras.class);
                startActivity(intent);
            }
        });
        partidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Historico.class);
                startActivity(intent);
            }
        });
        rapida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Partidas.class);
                startActivity(intent);
            }
        });
    }
}