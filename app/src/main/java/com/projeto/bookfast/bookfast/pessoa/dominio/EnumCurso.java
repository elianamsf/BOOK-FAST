package com.projeto.bookfast.bookfast.pessoa.dominio;

/**
 * Método EnumCurso tem as constates dos cursos disponíveis na hora de se cadastrar e alterar cadastro.
 */

public enum EnumCurso {
    SISTEMAS_DE_INFORMACAO("Sistemas de Informação"), MATEMATICA("Matemática"), FISICA("Física"),
    CIENCIA_DA_COMPUTACAO("Ciência da Computação"), GEOGRAFIA("Geografia"), BIOLOGIA("Biologia"), INFORMATICA("Informatica"), OUTRO("Outro(s)");
    private String descricao;

    EnumCurso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String[] listaEnumCurso() {
        EnumCurso[] listaCurso = EnumCurso.values();
        String[] lista = new String[listaCurso.length];
        for (int i = 0; i < listaCurso.length; i++) {
            lista[i] = listaCurso[i].getDescricao();
        }
        return lista;
    }
}



