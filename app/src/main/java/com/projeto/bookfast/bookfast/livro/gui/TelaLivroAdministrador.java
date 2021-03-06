package com.projeto.bookfast.bookfast.livro.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.pessoa.gui.TelaInicialAdministrador;

public class TelaLivroAdministrador extends Activity {
    Button btEditarLivro, btCadastrarLivro, btListarLivros, btDeletarlivro, btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_livro_administrador);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btEditarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCadastrarLivro = (Button) findViewById(R.id.btCadastrarLivro);
        btListarLivros = (Button) findViewById(R.id.btListarLivros);
        btDeletarlivro = (Button) findViewById(R.id.btDeletarlivro);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaCadastrarLivroAdministrador = new Intent(TelaLivroAdministrador.this, TelaCadastrarLivroAdministrador.class);
                startActivity(abreTelaCadastrarLivroAdministrador);
            }
        });

        btEditarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaEditarLivroAdministrador = new Intent(TelaLivroAdministrador.this, TelaEditarLivroAdministrador.class);
                startActivity(abreTelaEditarLivroAdministrador);
            }

        });

        btListarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaListaLivros = new Intent(TelaLivroAdministrador.this, TelaListaTodosLivrosAdm.class);
                startActivity(abreTelaListaLivros);
            }

        });

        btDeletarlivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaListaLivros = new Intent(TelaLivroAdministrador.this, TelaListaTodosLivrosAdm.class);
                startActivity(abreTelaListaLivros);
            }

        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaUsuarioAdministrador = new Intent(TelaLivroAdministrador.this, TelaInicialAdministrador.class);
                startActivity(abreTelaUsuarioAdministrador);
            }
        });
    }
}
