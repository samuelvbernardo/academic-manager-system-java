package br.com.gerenciador.telas;

import br.com.gerenciador.dal.GerenciadorDeArquivo;
import br.com.gerenciador.entidades.Usuario;

public class Main {
    public static void main(String[] args) {

        GerenciadorDeArquivo arquivo = new GerenciadorDeArquivo();
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        Usuario user = new Usuario(2, "Antonio Angêlo",  "00000000000","antonio", "12345", "aluno") {
            @Override
            public void mostraDados() {
            }
        };
        // a linha abaixo adiciona um usuario ao arquivo
        //arquivo.adicionarUsuario(caminhoArquivo, user);
    }
}