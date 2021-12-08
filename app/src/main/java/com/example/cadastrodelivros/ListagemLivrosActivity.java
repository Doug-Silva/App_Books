package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ListagemLivrosActivity extends AppCompatActivity {

    BancoDadosLivros bancoDadosLivros = new BancoDadosLivros(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_livros);

        List<Livro> listaLivros =  bancoDadosLivros.listarTodosLivros();

        ListView retornaLista = findViewById(R.id.listViewListagemLivros);

        LivroAdapter adapter = new LivroAdapter(this,listaLivros);
        retornaLista.setAdapter(adapter);
    }
}