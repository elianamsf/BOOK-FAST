package com.projeto.bookfast.bookfast.aluguel.persistecia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

/**
 * Created by oi on 17/08/2017.
 */

public class aluguelDAO {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public aluguelDAO(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public boolean deleteAluguel(Aluguel aluguel) {
        String deletarAluguel = "id = '" + aluguel.getId() + "'";
        db.delete(CreatBancoDados.getTabelaAluguel(), deletarAluguel, null);
        db.close();
        return true;
    }

    public Aluguel getAluguel(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CreatBancoDados.getTabelaAluguel(), new String[]{CreatBancoDados.getColunaIdAluguel(),
                        CreatBancoDados.getColunaPessoaAluguel(), CreatBancoDados.getColunaLivroAluguel(),
                        CreatBancoDados.getColunaData(), CreatBancoDados.getColunaDataEntrega(),
                        CreatBancoDados.getColunaMultaEntrega()},
                CreatBancoDados.getColunaIdAluguel() + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Aluguel aluguel = new Aluguel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            cursor.close();
            db.close();
            return aluguel;
        } else {
            db.close();
            return null;
        }
    }

    public boolean insertAluguel(Aluguel aluguel) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoaAluguel(), aluguel.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAluguel(), aluguel.getIdLivro());
        valores.put(CreatBancoDados.getColunaData(), aluguel.getDate());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaMultaEntrega(), aluguel.getMulta());
        db.insert(CreatBancoDados.getTabelaAluguel(), null, valores);
        db.close();
        return true;
    }

    public boolean updateAluguel(Aluguel aluguel) {
        db = dbHelper.getWritableDatabase();
        String where = "ID_PESSOA_ALUGUEL = '" + Long.toString(aluguel.getIdPessoa()) + "'";
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoaAluguel(), aluguel.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAluguel(), aluguel.getIdLivro());
        valores.put(CreatBancoDados.getColunaData(), aluguel.getDate());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaMultaEntrega(), aluguel.getMulta());
        db.update(CreatBancoDados.getTabelaAluguel(), valores, where, null);
        db.close();
        return true;
    }


}
