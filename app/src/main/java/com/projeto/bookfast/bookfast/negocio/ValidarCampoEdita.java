package com.projeto.bookfast.bookfast.negocio;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by oi on 28/07/2017.
 */

public class ValidarCampoEdita {
    public boolean vefificaEdicaoLivro(EditText isbn, EditText nome, EditText qtdAlugado, EditText autor, EditText genero, EditText qtTotal, EditText ano, EditText numEdicao) {
        String Isbn = isbn.getText().toString();
        String Nome = nome.getText().toString();
        String QtAlugado = qtdAlugado.getText().toString();
        String Autor = autor.getText().toString();
        String Genero = genero.getText().toString();
        String QtTotal = qtTotal.getText().toString();
        String Ano = ano.getText().toString();
        String NumEdicao = numEdicao.getText().toString();
        boolean resultado = false;
        if (isCampoVazio(Isbn)) {
            resultado = true;
            isbn.requestFocus();
        } else if (isCampoVazio(Nome)) {
            resultado = true;
            nome.requestFocus();
        } else if (isCampoVazio(QtAlugado)) {
            resultado = true;
            qtdAlugado.requestFocus();
        } else if (isCampoVazio(Autor)) {
            resultado = true;
            autor.requestFocus();
        } else if (isCampoVazio(Genero)) {
            resultado = true;
            genero.requestFocus();
        } else if (isCampoVazio(QtTotal)) {
            resultado = true;
            qtTotal.requestFocus();
        } else if (isCampoVazio(Ano)) {
            resultado = true;
            ano.requestFocus();
        } else if (isCampoVazio(NumEdicao)) {
            resultado = true;
            numEdicao.requestFocus();
        }
        if (resultado) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isEmailValido(String email) {
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    public boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }
}
