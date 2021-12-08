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

public class BancoDadosLivros extends SQLiteOpenHelper {

    private static String NOME_BANCO = "meusLivros";
    private static int VERSAO = 1;

    public BancoDadosLivros(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table livros( nome TEXT, autor TEXT, editora TEXT, paginas TEXT, genero TEXT, status TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void salvarLivro(Livro livro) {
        ContentValues valores = new ContentValues();
        valores.put("nome", livro.nome);
        valores.put("autor", livro.autor);
        valores.put("editora", livro.editora);
        valores.put("paginas", livro.paginas);
        valores.put("genero", livro.genero);
        valores.put("status", livro.status);
        final long ret = getWritableDatabase().insert("livros", null, valores);
        Log.i("trabalho", "Salvo no banco de dados -  " + ret);

    }

    public List<Livro> listarTodosLivros() {
        List<Livro> lista = new ArrayList<>();
        String sql = "select * from livros";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Livro livro = new Livro();
            livro.nome = cursor.getString(0);
            livro.autor = cursor.getString(1);
            livro.editora = cursor.getString(2);
            livro.paginas = cursor.getString(3);
            livro.genero = cursor.getString(4);
            livro.status = cursor.getString(5);
            lista.add(livro);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    public Optional<Livro> buscaLivroPeloNome(String nomeLivro) {

        Optional<Livro> retorno = Optional.empty();
        String sql = "select * from livros where nome = ? ";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{nomeLivro});
        cursor.moveToFirst();
        Livro livro = new Livro();
        for (int i = 0; i < cursor.getCount(); i++) {
            livro.nome = cursor.getString(0);
            livro.autor = cursor.getString(1);
            livro.editora = cursor.getString(2);
            livro.paginas = cursor.getString(3);
            livro.genero = cursor.getString(4);
            livro.status = cursor.getString(5);
            cursor.moveToNext();
            retorno = Optional.of(livro);
        }
        cursor.close();
        return retorno;
    }

    public void excluirLivrosPeloNome(String nomeLivro) {
        String sql = "delete from livros where nome = ?";
        getWritableDatabase().execSQL(sql, new String[]{nomeLivro});
        Log.i("trabalho", "Excluido no banco de dados pelo nome -  " + nomeLivro);
    }

    public void atualizarLivrosPeloNome(Livro livro){
        String sql = "update livros set status = ? where nome = ?";
        getWritableDatabase().execSQL(sql,new String[] {livro.status,livro.nome});
        Log.i("trabalho","Atualizado no banco de dados -  " + livro.nome);
    }

}