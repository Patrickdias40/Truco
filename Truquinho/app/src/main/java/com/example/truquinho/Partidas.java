package com.example.truquinho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Partidas extends AppCompatActivity {
    String valor;

    int id = 0;
    int pontos_nos;
    int pontos_eles;
    int pt_maxima;

    String nome;

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

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id", 0);
        valor = extras.getString("valor", "Portugues");

        if (id > 0){
            Registro registro = new Registro();
            try {
                registro = sqlHelper.getInstance(this).getRegistroUnico(valor, id);
            } catch (Exception e) {
                Toast.makeText(Partidas.this, R.string.erro, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Partidas.this, MainActivity.class);
                startActivity(intent);
            }
            nome = registro.nome;
            pt_maxima = registro.pt_maxima;
            pontos_eles = registro.pt_eles;
            pontos_nos = registro.pt_nos;
        } else if (id == 0){
            nome = extras.getString("nome");
            pt_maxima = extras.getInt("pt_maxima");
            pontos_eles = 0;
            pontos_nos = 0;
        } else {
            nome = "Partida Rápida";
            pt_maxima = 0;
            pontos_eles = 0;
            pontos_nos = 0;
        }

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
        if (pt_maxima > 0){
            if (pontos_nos >= pt_maxima){
                AlertDialog.Builder dialog = new AlertDialog.Builder(Partidas.this, R.style.CustomDialogTheme);

                dialog.setTitle(getString(R.string.parabens));
                dialog.setMessage("A partida alcançou o número máximo de pontos.");

                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create();
                dialog.show();
            } else {
                pontos_nos += 1;
                texto_nos.setText(String.valueOf(pontos_nos));
            }
        } else {
            pontos_nos += 1;
            texto_nos.setText(String.valueOf(pontos_nos));
        }
    }
    public void adiciona_eles(View view){
        if (pt_maxima > 0){
            if (pontos_eles >= pt_maxima){
                AlertDialog.Builder dialog = new AlertDialog.Builder(Partidas.this, R.style.CustomDialogTheme);

                dialog.setTitle(R.string.parabens);
                dialog.setMessage("A partida alcançou o número máximo de pontos.");

                dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create();
                dialog.show();
            } else {
                pontos_eles += 1;
                texto_eles.setText(String.valueOf(pontos_eles));
            }
        } else {
            pontos_eles += 1;
            texto_eles.setText(String.valueOf(pontos_eles));
        }
    }
    public void retira_nos(View view){
        if (pontos_nos > 0) {
            pontos_nos -= 1;
            texto_nos.setText(String.valueOf(pontos_nos));
        } else {
            Toast.makeText(Partidas.this, "Pontuação já é 0", Toast.LENGTH_SHORT).show();
        }
    }
    public void retira_eles(View view){
        if (pontos_eles > 0) {
            pontos_eles -= 1;
            texto_eles.setText(String.valueOf(pontos_nos));
        } else {
            Toast.makeText(Partidas.this, "Pontuação já é 0", Toast.LENGTH_SHORT).show();
        }
    }
    public void salva_e_sai(View view) {
        long id_banco;
        if (id == 0){
            id_banco = com.example.truquinho.sqlHelper.getInstance(Partidas.this).addPartida(nome, pt_maxima, pontos_nos, pontos_eles);
        } else {
            id_banco = com.example.truquinho.sqlHelper.getInstance(Partidas.this).replacePartida(id, nome, pt_maxima, pontos_nos, pontos_eles);
        }
        if (id_banco > 0) {
            Toast.makeText(Partidas.this, R.string.salvo, Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(Partidas.this, MainActivity.class);
        startActivity(intent);
    }
}