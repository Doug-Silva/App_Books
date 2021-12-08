package com.example.cadastrodelivros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BancoDadosUsuario extends SQLiteOpenHelper {

    private static String NOME_BANCO = "usuarios";
    private static int VERSAO = 1;

    public BancoDadosUsuario(@Nullable Context context) { super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table usuario( login TEXT, senha TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void salvarUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("login", usuario.login);
        valores.put("senha", usuario.senha);
        final long ret = getWritableDatabase().insert("usuario", null, valores);
        Log.i("trabalho", "Salvo no banco de dados -  " + ret);

    }

    public List<Usuario> listarTodosUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuario";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Usuario usuario = new Usuario();
            usuario.login = cursor.getString(0);
            usuario.senha = cursor.getString(1);
            lista.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    public Optional<Usuario> buscaUsuarioPeloLogin(String nomeLogin) {

        Optional<Usuario> retorno = Optional.empty();
        String sql = "select * from usuario where login = ? ";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{nomeLogin});
        cursor.moveToFirst();
        Usuario usuario = new Usuario();
        for (int i = 0; i < cursor.getCount(); i++) {
            usuario.login = cursor.getString(0);
            usuario.senha = cursor.getString(1);
            cursor.moveToNext();
            retorno = Optional.of(usuario);
        }
        cursor.close();
        return retorno;
    }

    public void excluirUsuarioPeloLogin(String userLogin) {
        String sql = "delete from usuario where login = ?";
        getWritableDatabase().execSQL(sql, new String[]{userLogin});
        Log.i("trabalho", "Excluido no banco de dados pelo login -  " + userLogin);
    }

}