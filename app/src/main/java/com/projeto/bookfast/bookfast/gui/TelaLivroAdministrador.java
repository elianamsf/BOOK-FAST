package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.projeto.bookfast.bookfast.R;

public class TelaLivroAdministrador extends Activity {
    Button btEditarLivro, btCadastrarLivro, btListarLivros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_livro_administrador);

        btEditarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCadastrarLivro = (Button) findViewById(R.id.btCadastrarLivro);
        btListarLivros = (Button) findViewById(R.id.btListarLivros);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaCadastrarLivroAdministrador = new Intent(TelaLivroAdministrador.this, TelaCadastrarLivroAdministrador.class);
                startActivity(abreTelaCadastrarLivroAdministrador);
            }
        });

        btEditarLivro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(TelaLivroAdministrador.this, "FALTA IMPLEMENTAR AINDA", Toast.LENGTH_LONG).show();

            }

        });

        btListarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaListaLivros = new Intent(TelaLivroAdministrador.this, TelaListaLivros.class);
                startActivity(abreTelaListaLivros);
            }

        });

    }

}