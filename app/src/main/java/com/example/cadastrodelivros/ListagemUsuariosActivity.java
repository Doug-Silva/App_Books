package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ListagemUsuariosActivity extends AppCompatActivity {

    BancoDadosUsuario bancoDadosUsuario = new BancoDadosUsuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_usuarios);

        List<Usuario> listaUsuario =  bancoDadosUsuario.listarTodosUsuarios();

        ListView retornaLista = findViewById(R.id.listViewListagemUsuarios);

        UsuarioAdapter adapter = new UsuarioAdapter(this,listaUsuario);
        retornaLista.setAdapter(adapter);

    }
}