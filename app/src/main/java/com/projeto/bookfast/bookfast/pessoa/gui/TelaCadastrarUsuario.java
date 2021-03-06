package com.projeto.bookfast.bookfast.pessoa.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.UpdatePessoa;

import static com.projeto.bookfast.bookfast.pessoa.dominio.EnumCurso.listaEnumCurso;

public class TelaCadastrarUsuario extends AppCompatActivity {
    EditText editNovoUsuario, editNovaSenha, editNovoEmail, editNovoNome;
    Button btRegistrar, btCancelarRegistro;
    private Pessoa pessoa;
    private Spinner spinner;
    private String[] listaCurso = listaEnumCurso();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_usuario);
        editNovoNome = (EditText) findViewById(R.id.editNovoNome);
        editNovoEmail = (EditText) findViewById(R.id.editNovoEmail);
        editNovoUsuario = (EditText) findViewById(R.id.editNovoUsuario);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        btCancelarRegistro = (Button) findViewById(R.id.btCancelarRegistro);
        btCancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaCurso);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spnCurso);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup group = (ViewGroup) findViewById(R.id.raizCadastroUsuario);
                ReadPessoa buscar = new ReadPessoa(getApplicationContext());
                boolean resultado = false;
                String cpf = editNovoUsuario.getText().toString();
                String nome = editNovoNome.getText().toString();
                String email = editNovoEmail.getText().toString();
                String senha = editNovaSenha.getText().toString();
                String cursoTexto = (String) spinner.getSelectedItem();
                if (!ValidarCpf.validarCpf(cpf)) {
                    resultado = true;
                    editNovoUsuario.setError("CPF inváldo!");
                    editNovoUsuario.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(nome)) {
                    resultado = true;
                    editNovoNome.setError("Nome inválido!");
                    editNovoNome.requestFocus();
                } else if (!ValidarEmail.isEmailValido(email)) {
                    resultado = true;
                    editNovoEmail.setError("Email inválido!");
                    editNovoEmail.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(senha)) {
                    resultado = true;
                    editNovaSenha.setError("Senha inválida!");
                    editNovaSenha.requestFocus();
                }
                if (!resultado) {
                    pessoa = buscar.getPessoa(Long.parseLong(cpf));
                    if (pessoa != null && pessoa.getStatus().equals("0")) {
                        pessoa.setStatus("1");
                        pessoa.setNome(nome);
                        pessoa.setEmail(email);
                        UpdatePessoa atualisaPessoa = new UpdatePessoa(getApplicationContext());
                        atualisaPessoa.updatePessoa(pessoa);
                    } else if (pessoa == null) {
                        Pessoa pessoa2 = new Pessoa();
                        LimparTela.clearForm(group);
                        editNovoNome.requestFocus();
                        pessoa2.setNome(nome);
                        pessoa2.setEmail(email);
                        pessoa2.setCpf(Long.parseLong(cpf));
                        pessoa2.setSenha(senha);
                        pessoa2.setListaAluguel("");
                        pessoa2.setStatus("1");
                        pessoa2.setCurso(cursoTexto);
                        UpdatePessoa inserir = new UpdatePessoa(getApplicationContext());
                        if (inserir.insertPessoa(pessoa2)) {
                            Toast.makeText(TelaCadastrarUsuario.this, R.string.CadastroSucesso, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(TelaCadastrarUsuario.this, R.string.ErroInserirPessoa, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaCadastrarUsuario.this, R.string.CPFJaCadastrado, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
