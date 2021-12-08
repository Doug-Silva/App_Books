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

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private List<Usuario> listaUsuario;

    public UsuarioAdapter(@NonNull Context context, List<Usuario> listaUsuario) {
        super(context, 0,listaUsuario);
        this.listaUsuario = listaUsuario;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            Context ctx = getContext();
            LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_layout_usuario,null);
        }
        Usuario usuario = listaUsuario.get(position);
        if(usuario != null){
            TextView novoLogin = v.findViewById(R.id.textViewLogin);
            TextView novaSenha = v.findViewById(R.id.textViewSenha);
            novoLogin.setText(usuario.login);
            novaSenha.setText(usuario.senha);
        }
        return v;
    }
}