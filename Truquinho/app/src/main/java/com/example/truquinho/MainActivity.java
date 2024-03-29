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
        regras = findViewById(R.id.bt_regras);
        partidas = findViewById(R.id.bt_partida);
        rapida = findViewById(R.id.bt_rapida);

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
                intent.putExtra("valor","Portugues");
                startActivity(intent);
            }
        });
        rapida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Partidas.class);
                intent.putExtra("id", -1);
                intent.putExtra("valor", "Portugues");
                startActivity(intent);
            }
        });
    }
}