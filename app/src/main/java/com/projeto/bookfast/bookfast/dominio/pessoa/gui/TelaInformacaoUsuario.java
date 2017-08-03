package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;

import java.util.Arrays;
import java.util.List;

public class TelaInformacaoUsuario extends AppCompatActivity {
    Button btEditaMinhasInformacoes;
    TextView textViewMinhasInformacoes;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_informacao_usuario);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        textViewMinhasInformacoes = (TextView) findViewById(R.id.textViewMinhasInformacoes);
        btEditaMinhasInformacoes = (Button) findViewById(R.id.btEditaMinhasInformacoes);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            List<String> quantidadeLivrosAludado = Arrays.asList(pessoa.getLivros().trim().split(" "));
            String dadosPessoa = "Nome: " + pessoa.getNome() + ", Email: " + pessoa.getEmail() + ", Cpf: " + pessoa.getCpf()
                    + ", Qtd livro ocupado: " + quantidadeLivrosAludado.size();
            textViewMinhasInformacoes.setText(dadosPessoa);
        }
        btEditaMinhasInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaEditaInfoUsuario = new Intent(TelaInformacaoUsuario.this, TelaEditaInfoUsuario.class);
                AbreTelaEditaInfoUsuario.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaEditaInfoUsuario);
            }
        });
    }
}
