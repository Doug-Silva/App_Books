package com.example.cadastrodelivros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfiguracoesActivity extends AppCompatActivity {

    Button botaoDarkMode;
    Boolean isDarkModeOn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        botaoDarkMode=findViewById(R.id.buttonDarkMode);

        //Obtenha o status atual
        isDarkModeOn=getDarkModeStatus();
        if(isDarkModeOn){
            botaoDarkMode.setText("Tema Light Mode");
        }else{
            botaoDarkMode.setText("Tema Dark Mode");
        }

        botaoDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDarkModeOn){
                    //se o modo escuro estiver ativado, desligue
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    botaoDarkMode.setText("Tema Dark Mode");
                    isDarkModeOn=false;
                }else{
                    //se o modo escuro estiver desligado, ligue
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    botaoDarkMode.setText("Tema Light Mode");
                    isDarkModeOn=true;
                }
            }
        });
    }

    private boolean getDarkModeStatus(){
        int nightModeFlags=
                ConfiguracoesActivity.this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags){
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return false;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Deseja voltar para tela de Login?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ConfiguracoesActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}