package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AbreMeusLivrosActivity(View view) {

        EditText login = findViewById(R.id.editTextLogin);
        EditText senha = findViewById(R.id.editTextSenha);

        // verifica se login e senha estao corretos
        if (login.getText().toString().equals("admin") && senha.getText().toString().equals("123")) {
            Intent intent = new Intent(MainActivity.this, MeusLivrosActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"ERRO! Login ou Senha Invalidos", Toast.LENGTH_LONG).show();
        }
    }

    public void AbreNovaContaActivity(View view) {

        Intent intent = new Intent(MainActivity.this, NovaContaActivity.class);
        startActivity(intent);
    }

    public void AbreConfiguracoesActivity(View view) {

        Intent intent = new Intent(MainActivity.this, ConfiguracoesActivity.class);
        startActivity(intent);
    }
}