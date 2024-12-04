package br.com.gerenciador.dal;

import br.com.gerenciador.entidades.Usuario;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GerenciadorDeArquivo {

    public GerenciadorDeArquivo() {
    }

    // Método principal para executar os exemplos
    public static void main(String[] args) {
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        // 1. Criar ou escrever no arquivo
        escreverNoArquivo(caminhoArquivo, "Primeira linha do arquivo.\nSegunda linha do arquivo.");

        // 2. Ler o conteúdo do arquivo
        List<String> linhas = lerArquivo(caminhoArquivo);
        if (linhas != null) {
            System.out.println("Conteúdo do arquivo:");
            linhas.forEach(System.out::println);
        }

        // 3. Adicionar mais conteúdo ao arquivo
        adicionarNoArquivo(caminhoArquivo, "\nUsuario adicionado.");
        System.out.println("\nConteúdo atualizado:");
        lerArquivo(caminhoArquivo).forEach(System.out::println);
    }

    // Método para escrever conteúdo em um arquivo (substitui o conteúdo existente)
    public static void escreverNoArquivo(String caminhoArquivo, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write(conteudo);
            System.out.println("Arquivo criado/escrito com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Método para adicionar um usuário ao arquivo na interface
    public static void adicionarUsuario(String caminhoArquivo, String usuario) {
        String dadosUsuario = usuario.toString();
        adicionarNoArquivo(caminhoArquivo, dadosUsuario + "\n");
    }
    // Método para adicionar um usuario ao arquivo no main
    public static void adicionarUsuario(String caminhoArquivo, Usuario usuario) {
        String dadosUsuario = usuario.toString();
        adicionarNoArquivo(caminhoArquivo, dadosUsuario + "\n");
    }

    // Método para ler o conteúdo de um arquivo
    public static List<String> lerArquivo(String caminhoArquivo) {
        try {
            return Files.readAllLines(Paths.get(caminhoArquivo));
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }

    // Método para adicionar conteúdo ao final de um arquivo existente
    public static void adicionarNoArquivo(String caminhoArquivo, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(conteudo);
            System.out.println("Conteúdo adicionado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao adicionar conteúdo no arquivo: " + e.getMessage());
        }
    }

    public static void apagarConteudoArquivo(String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Não escreve nada, limpando o arquivo
            System.out.println("Conteúdo do arquivo apagado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao apagar o conteúdo do arquivo: " + e.getMessage());
        }
    }

    public void lerUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Divide a linha em campos com base no delimitador '|'
                String[] dados = linha.split("\\|");
                System.out.println("ID: " + dados[0] + ", Nome: " + dados[1] + ", CPF: " + dados[2]
                        + ", Login: " + dados[3] + ", Senha: " + dados[4] + ", Perfil: " + dados[5]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alterarUsuario(int id, String novoNome, String novoCpf, String novoLogin, String novaSenha, String novoPerfil) {
        String caminhoArquivo = "C:/Users/Admin/Downloads/GerenciadorAcademico.txt";

        // Ler as linhas do arquivo
        List<String> linhas = GerenciadorDeArquivo.lerArquivo(caminhoArquivo);
        if (linhas != null) {
            boolean usuarioAlterado = false;
            StringBuilder arquivoModificado = new StringBuilder();

            // Percorrer todas as linhas do arquivo
            for (String linha : linhas) {
                String[] dados = linha.split("\\|");

                // Verificar se o id do usuário corresponde
                if (Integer.parseInt(dados[0]) == id) {
                    // Alterar os dados do usuário
                    dados[1] = novoNome;  // Nome
                    dados[2] = novoCpf;   // CPF
                    dados[3] = novoLogin; // Login
                    dados[4] = novaSenha; // Senha
                    dados[5] = novoPerfil; // Perfil

                    // Criar a linha alterada e adicionar ao conteúdo
                    String linhaAlterada = String.join("|", dados);
                    arquivoModificado.append(linhaAlterada).append("\n");

                    usuarioAlterado = true;
                } else {
                    // Se não for o usuário a ser alterado, apenas adicionar a linha original
                    arquivoModificado.append(linha).append("\n");
                }
            }

            // Se o usuário foi alterado, escrever as modificações de volta no arquivo
            if (usuarioAlterado) {
                GerenciadorDeArquivo.escreverNoArquivo(caminhoArquivo, arquivoModificado.toString());
                JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de usuários.");
        }
    }

}
