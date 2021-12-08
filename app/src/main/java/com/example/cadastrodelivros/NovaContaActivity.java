package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Optional;

public class NovaContaActivity extends AppCompatActivity {

    BancoDadosUsuario bancoDadosUsuario = new BancoDadosUsuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);
    }

    public void cadastrarNovoUsuario(View view) {

        EditText novoLogin = findViewById(R.id.editTextNovoLogin);
        EditText novaSenha = findViewById(R.id.editTextNovaSenha);

        Usuario usuario = new Usuario();
        usuario.login = novoLogin.getText().toString();
        usuario.senha = novaSenha.getText().toString();

        bancoDadosUsuario.salvarUsuario(usuario);
        Toast.makeText(this,"Cadastrado Com Sucesso",Toast.LENGTH_SHORT).show();
    }

    public void listarUsuarios(View view) {

        Intent intent = new Intent(this,ListagemUsuariosActivity.class);
        startActivity(intent);
    }

    public void deletarUsuarios(View view) {

        EditText loginUsuarioDelete = findViewById(R.id.editTextDeletarUsuario);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remoção de dados");
        builder.setMessage("Deseja realmente excluir ?")
                .setCancelable(false)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Optional<Usuario> op = bancoDadosUsuario.buscaUsuarioPeloLogin(loginUsuarioDelete.getText().toString());
                        if(op.isPresent()) {
                            bancoDadosUsuario.excluirUsuarioPeloLogin(loginUsuarioDelete.getText().toString());
                            Toast.makeText(NovaContaActivity.this, "Sua Conta Foi Deletada", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(NovaContaActivity.this, "ERRO! Login Não Foi Encontrado", Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Deseja voltar para tela de Login?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NovaContaActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}