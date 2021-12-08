package com.example.cadastrodelivros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LivroAdapter extends ArrayAdapter<Livro> {

    private List<Livro> listaLivro;

    public LivroAdapter(@NonNull Context context, List<Livro> listaLivro) {
        super(context, 0,listaLivro);
        this.listaLivro = listaLivro;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_layout_livro,null);
        }
        Livro livro = listaLivro.get(position);
        if(livro != null){
            TextView nome = v.findViewById(R.id.textViewNome);
            TextView autor = v.findViewById(R.id.textViewAutor);
            TextView editora = v.findViewById(R.id.textViewEditora);
            TextView paginas = v.findViewById(R.id.textViewPaginas);
            TextView genero = v.findViewById(R.id.textViewGenero);
            TextView status = v.findViewById(R.id.textViewStatus);
            nome.setText(livro.nome);
            autor.setText(livro.autor);
            editora.setText(livro.editora);
            paginas.setText(livro.paginas);
            genero.setText(livro.genero);
            status.setText(livro.status);
        }
        return v;
    }
}