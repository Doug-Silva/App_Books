package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Optional;

public class MeusLivrosActivity2 extends AppCompatActivity {

    ArrayList<String> atualizaStatusLivro = new ArrayList<String>();
    BancoDadosLivros bancoDadosLivros = new BancoDadosLivros(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_livros2);

        //Aqui adiciono minha lista do spinner
        atualizaStatusLivro.add("Quero ler");
        atualizaStatusLivro.add("Não lido");
        atualizaStatusLivro.add("Leitura em andamento");
        atualizaStatusLivro.add("Já li");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,atualizaStatusLivro);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinnerAtualizaStatus);
        spinner.setAdapter(adapter);
    }

    public void atualizarLivros(View view) {

        EditText nomeParaAtualizar = findViewById(R.id.editTextIDParaAtualizar);
        Spinner atualizaStatus = findViewById(R.id.spinnerAtualizaStatus);

        Livro livroParaAtualizar = new Livro();
        livroParaAtualizar.nome = nomeParaAtualizar.getText().toString();
        livroParaAtualizar.status = (String) atualizaStatus.getSelectedItem();

        Optional<Livro> op = bancoDadosLivros.buscaLivroPeloNome(livroParaAtualizar.nome);
        if (op.isPresent()) {
            bancoDadosLivros.atualizarLivrosPeloNome(livroParaAtualizar);
            Toast.makeText(MeusLivrosActivity2.this, "Atualização Realizada Com Sucesso", Toast.LENGTH_LONG).show();
            nomeParaAtualizar.setText("");
            atualizaStatus.getSelectedItem();
        } else {
            Toast.makeText(MeusLivrosActivity2.this, "ERRO! Nome Do Livro Não Encontrado", Toast.LENGTH_LONG).show();
        }
    }

    public void deletarLivros(View view) {

        EditText nomeLivroDelete = findViewById(R.id.editTextDeletar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remoção de dados");
        builder.setMessage("Deseja realmente excluir ?")
                .setCancelable(false)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Optional<Livro> op = bancoDadosLivros.buscaLivroPeloNome(nomeLivroDelete.getText().toString());
                        if(op.isPresent()) {
                            bancoDadosLivros.excluirLivrosPeloNome(nomeLivroDelete.getText().toString());
                            Toast.makeText(MeusLivrosActivity2.this, "Livro Deletado", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MeusLivrosActivity2.this, "ERRO! Nome Do Livro Não Encontrado", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}