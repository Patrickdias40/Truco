package com.example.truquinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Historico extends AppCompatActivity {
    Button bt_pesquisar;
    Button bt_selecionar;
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
        edit_id = findViewById(R.id.editIdPesquisar);
        edit_nome = findViewById(R.id.editNomePesquisar);

        bt_pesquisar.setOnClickListener(this::pesquisar);

        recyclerView = findViewById(R.id.lista);

        Bundle extras = getIntent().getExtras(); //buscando da tela anteriores valores enviados por chave-valor
        if (extras != null){
            String valor = extras.getString("valor");

            List<Registro> registros = sqlHelper.getInstance(this).getRegistro(valor);
            // Log.d("teste", registros.toString());
            ListaValores adapter = new ListaValores(registros);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    public void pesquisar(View view){
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
    }

    public void selecionar(View view){
        id = Integer.parseInt(edit_id.getText().toString());
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