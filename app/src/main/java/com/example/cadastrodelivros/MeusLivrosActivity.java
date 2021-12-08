package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Optional;

public class MeusLivrosActivity extends AppCompatActivity {

    ArrayList<String> status = new ArrayList<String>();
    BancoDadosLivros bancoDadosLivros = new BancoDadosLivros(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_livros);

        //Aqui adiciono minha lista do spinner
        status.add("Já li");
        status.add("Leitura em andamento");
        status.add("Não lido");
        status.add("Quero ler");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinnerStatus);
        spinner.setAdapter(adapter);
    }

    public void cadastrarNovoLivro(View view) {

        EditText nome = findViewById(R.id.editTextNome);
        EditText autor = findViewById(R.id.editTextAutor);
        EditText editora = findViewById(R.id.editTextEditora);
        EditText paginas = findViewById(R.id.editTextPaginas);
        EditText genero = findViewById(R.id.editTextGenero);
        Spinner status = findViewById(R.id.spinnerStatus);

        Livro livro = new Livro();
        livro.nome = nome.getText().toString();
        livro.autor = autor.getText().toString();
        livro.editora = editora.getText().toString();
        livro.paginas = paginas.getText().toString();
        livro.genero = genero.getText().toString();
        livro.status = (String) status.getSelectedItem();

        bancoDadosLivros.salvarLivro(livro);
        Toast.makeText(this,"Cadastrado Com Sucesso",Toast.LENGTH_SHORT).show();
    }

    public void listarLivros(View view) {

        Intent intent = new Intent(this,ListagemLivrosActivity.class);
        startActivity(intent);
    }

    public void AbreMeusLivrosActivity2(View view) {

        Intent intent = new Intent(MeusLivrosActivity.this, MeusLivrosActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Deseja voltar para tela de Login?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MeusLivrosActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}