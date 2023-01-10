package com.example.truquinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Historico extends AppCompatActivity {
    Button bt_pesquisar;
    Button bt_selecionar;
    Button bt_criar;
    Button bt_remover;
    EditText edit_nome;
    EditText edit_id;
    String nome;
    RecyclerView recyclerView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historico);

        bt_pesquisar = findViewById(R.id.bt_pesquisar);
        bt_selecionar = findViewById(R.id.bt_selecionar);
        bt_criar = findViewById(R.id.bt_novaPartida);
        bt_remover = findViewById(R.id.bt_remover);
        edit_id = findViewById(R.id.editIdPesquisar);
        edit_nome = findViewById(R.id.editNomePesquisar);

        bt_pesquisar.setOnClickListener(this::pesquisar);
        bt_selecionar.setOnClickListener(this::selecionar);
        bt_criar.setOnClickListener(this::criar);
        bt_remover.setOnClickListener(this::remover);

        recyclerView = findViewById(R.id.lista);

        Bundle extras = getIntent().getExtras(); //buscando da tela anteriores valores enviados por chave-valor
        if (extras != null){
            String valor = extras.getString("valor");

            List<Registro> registros = sqlHelper.getInstance(this).getRegistro(valor);
            Log.d("teste", registros.toString());
            ListaValores adapter = new ListaValores(registros);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    public void criar(View view){
        Intent intent = new Intent(Historico.this, Nova_Partida.class);
        startActivity(intent);
    }

    public void pesquisar(View view){
        if (!edit_nome.getText().toString().equals("")) {
            Bundle extras = getIntent().getExtras(); //buscando da tela anteriores valores enviados por chave-valor
            if (extras != null) {
                String valor = extras.getString("valor");
                nome = edit_nome.getText().toString();

                List<Registro> registros = sqlHelper.getInstance(this).getRegistroPesquisa(valor, nome);
                // Log.d("teste", registros.toString());
                ListaValores adapter = new ListaValores(registros);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
            }
        } else {
            Toast.makeText(Historico.this, R.string.campo_vazio, Toast.LENGTH_SHORT).show();
        }
    }

    public void selecionar(View view){
        if (!edit_id.getText().toString().equals("")){
            Bundle extras = getIntent().getExtras(); //buscando da tela anteriores valores enviados por chave-valor
            if (extras != null) {
                String valor = extras.getString("valor");
                id = Integer.parseInt(edit_id.getText().toString());
                boolean check = sqlHelper.getInstance(this).checkID(valor, id);
                if (check) {
                   Intent intent = new Intent(Historico.this, Partidas.class);
                   intent.putExtra("id", id);
                   startActivity(intent);
                } else {
                    Toast.makeText(Historico.this, R.string.erro, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(Historico.this, R.string.campo_vazio, Toast.LENGTH_SHORT).show();
        }
    }

    public void remover(View view) {
        if (!edit_id.getText().toString().equals("")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Historico.this, R.style.CustomDialogTheme);

            dialog.setTitle(getString(R.string.remover));
            dialog.setMessage(R.string.remover_partida);

            dialog.setNegativeButton(R.string.sim, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bundle extras = getIntent().getExtras(); //buscando da tela anteriores valores enviados por chave-valor
                    if (extras != null) {
                        String valor = extras.getString("valor");
                        id = Integer.parseInt(edit_id.getText().toString());
                        long id_banco = sqlHelper.getInstance(Historico.this).removePartida(id);
                        if (id_banco > 0) {
                            Toast.makeText(Historico.this, R.string.salvo, Toast.LENGTH_LONG).show();
                        }
                        List<Registro> registros = sqlHelper.getInstance(Historico.this).getRegistro(valor);
                        // Log.d("teste", registros.toString());
                        ListaValores adapter = new ListaValores(registros);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Historico.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            });
            dialog.setPositiveButton(R.string.nao, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.create();
            dialog.show();
        } else {
            Toast.makeText(Historico.this, R.string.campo_vazio, Toast.LENGTH_SHORT).show();
        }
    }

    private class ListaValores extends RecyclerView.Adapter<ListaValores.ListaValoresViewHolder>{

        private List<Registro> dados;
        //private AdapterView.OnItemClickListener listener;

        public ListaValores(List<Registro> dados){
            this.dados = dados;
        }
        @NonNull
        @Override
        public ListaValoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
            return new ListaValoresViewHolder(getLayoutInflater().inflate(android.R.layout.simple_list_item_1,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListaValoresViewHolder holder, int position){
            Registro dado = dados.get(position);
            holder.bind(dado);
        }
        @Override
        public int getItemCount(){
            return dados.size();
        }
        private class ListaValoresViewHolder extends RecyclerView.ViewHolder{
            public ListaValoresViewHolder(@NonNull View itemView){
                super(itemView);
            }
            public void bind(Registro dado){
                ((TextView) itemView).setText(""+dado.id+": "+dado.nome+" -- Pt. Máxima: "+dado.pt_maxima+" -- "+dado.pt_nos+" x "+dado.pt_eles);

            }
        }

    }
}