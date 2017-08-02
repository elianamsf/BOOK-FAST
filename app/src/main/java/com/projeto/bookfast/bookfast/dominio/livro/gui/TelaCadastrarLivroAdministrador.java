package com.projeto.bookfast.bookfast.dominio.livro.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.livro.negocio.ValidarCamposCadastroLivro;
import com.projeto.bookfast.bookfast.dominio.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.dominio.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.dominio.pessoa.negocio.ValidarCampoCadastroPessoa;
import com.projeto.bookfast.bookfast.negocio.LimparTela;

public class TelaCadastrarLivroAdministrador extends Activity {
    Button btCadastrarLivro, btCancelar;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_livro_administrador);
        ValidarCampoCadastroPessoa validarCampo = new ValidarCampoCadastroPessoa();
        editIsbn = (EditText) findViewById(R.id.editIsbn);
        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        editEdicao = (EditText) findViewById(R.id.editEdicao);
        editAno = (EditText) findViewById(R.id.editAno);
        editQuantidadeTotal = (EditText) findViewById(R.id.editQuantidadeTotal);
        editQuantidadeAlugada = (EditText) findViewById(R.id.editQuantidadeAlugada);

        btCadastrarLivro = (Button) findViewById(R.id.btCadastrarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLivro inserirLivro = new UpdateLivro(getApplicationContext());
                ValidarCamposCadastroLivro validarCampo = new ValidarCamposCadastroLivro();
                ViewGroup group = (ViewGroup) findViewById(R.id.raizCadastroLivro);
                LimparTela limparTela = new LimparTela();
                if (!validarCampo.vefificaCadastroLivro(editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada)) {
                    long isbn = Integer.parseInt(editIsbn.getText().toString());
                    int edicao = Integer.parseInt(editEdicao.getText().toString());
                    int ano = Integer.parseInt(editAno.getText().toString());
                    int quantidadeTotal = Integer.parseInt(editQuantidadeTotal.getText().toString());
                    int quanitdadeAlugada = Integer.parseInt(editQuantidadeAlugada.getText().toString());
                    String nome = editNome.getText().toString();
                    String genero = editGenero.getText().toString();
                    String autor = editAutor.getText().toString();
                    limparTela.clearForm(group);
                    editIsbn.requestFocus();
                    ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
                    livro = buscarLivro.getLivro(isbn);
                    if (livro != null) {
                        Toast.makeText(TelaCadastrarLivroAdministrador.this, "LIVRO JÁ CADASTRADO.", Toast.LENGTH_LONG).show();

                    } else {
                        livro = new Livro(isbn, nome, quanitdadeAlugada, autor, genero, quantidadeTotal, ano, edicao);

                        inserirLivro.insertLivro(livro);
                        Toast.makeText(TelaCadastrarLivroAdministrador.this, "LIVRO CADASTRADO COM SUCESSO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TelaCadastrarLivroAdministrador.this, "Campos inválidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


}