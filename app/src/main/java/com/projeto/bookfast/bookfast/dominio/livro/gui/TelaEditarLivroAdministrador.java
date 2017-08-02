package com.projeto.bookfast.bookfast.dominio.livro.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.livro.negocio.ValidarCampoEditaLivro;
import com.projeto.bookfast.bookfast.dominio.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.dominio.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.negocio.LimparTela;

public class TelaEditarLivroAdministrador extends AppCompatActivity {

    Button btEdtarLivro, btCancelar;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_livro);
        editIsbn = (EditText) findViewById(R.id.editIsbn);
        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        editEdicao = (EditText) findViewById(R.id.editEdicao);
        editAno = (EditText) findViewById(R.id.editAno);
        editQuantidadeTotal = (EditText) findViewById(R.id.editQuantidadeTotal);
        editQuantidadeAlugada = (EditText) findViewById(R.id.editQuantidadeAlugada);

        btEdtarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);

        btEdtarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLivro atualizarLivro = new UpdateLivro(getApplicationContext());
                ValidarCampoEditaLivro validarEdicao = new ValidarCampoEditaLivro();
                ViewGroup group = (ViewGroup) findViewById(R.id.raizEditLivro);
                LimparTela limparTela = new LimparTela();

                if (!validarEdicao.vefificaEdicaoLivro(editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada)) {
                    Long isbn = Long.parseLong(editIsbn.getText().toString());
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
                        livro.setIsbn(isbn);
                        livro.setNumEdicao(edicao);
                        livro.setAno(ano);
                        livro.setQtdTotal(quantidadeTotal);
                        livro.setQtdAlugado(quanitdadeAlugada);
                        livro.setNome(nome);
                        livro.setGenero(genero);
                        livro.setAutor(autor);
                        atualizarLivro.updateLivro(livro);
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.AtualizcaoLivro, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.LvroNaoExiste, Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(TelaEditarLivroAdministrador.this, R.string.CampoInvalido, Toast.LENGTH_SHORT).show();
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