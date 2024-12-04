package br.com.gerenciador.entidades;

import br.com.gerenciador.servicos.Mensagem;

import java.util.ArrayList;

public class Aluno extends Usuario {
    private String matricula;
    private String curso;
    private String email;

    ArrayList<Mensagem> mensagensEnviadas = new ArrayList<>();
    ArrayList<Mensagem> mensagensRecebidas = new ArrayList<>();

    public Aluno(){

    }

    public Aluno(int id, String nome, String cpf, String login, String senha, String perfil,String matricula, String curso, String email) {
        super(id, nome, cpf, login, senha, perfil);
        this.matricula = matricula;
        this.curso = curso;
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public ArrayList<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    @Override
    public void mostraDados() {
        System.out.println("ID: " + id +
                "\nNome: " + nome +
                "\nCurso: " + curso +
                "\nMatricula: " + matricula +
                "\nEmail: " + email);
    }

    public void mostraEnviadas(){
        for (Mensagem msg : mensagensEnviadas){
            System.out.println(msg.getConteudo() + "\nData: " + msg.getDataHora() + "\n Destino: " + msg.getDestinatario());
        }
    }

    public void mostraRecebidas(){
        for (Mensagem msg : mensagensRecebidas){
            System.out.println(msg.getConteudo() + "\nData: " + msg.getDataHora() + "\nDestino: " + msg.getDestinatario());
        }
    }
}
