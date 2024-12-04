package br.com.gerenciador.entidades;

import br.com.gerenciador.servicos.Horario;
import br.com.gerenciador.servicos.Mensagem;

import java.util.ArrayList;

public class Professor extends Usuario {
    private String departamento;
    private String email;
    private Horario horarioDisponivel;
    private boolean isLogado;

    private ArrayList<Mensagem> mensagensEnviadas = new ArrayList<>();
    private ArrayList<Mensagem> mensagensRecebidas = new ArrayList<>();
    private ArrayList<Mensagem> mensagensNaoLidas = new ArrayList<>();

    public Professor(){

    }

    public Professor(int id, String nome, String cpf, String login, String senha, String perfil,String departamento, String email, Horario horarioDisponivel) {
        super(id, nome, cpf, login, senha, perfil);
        this.departamento = departamento;
        this.email = email;
        this.horarioDisponivel = horarioDisponivel;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Horario getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(Horario horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public ArrayList<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public ArrayList<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    public ArrayList<Mensagem> getMensagensNaoLidas() {
        return mensagensNaoLidas;
    }

    public boolean isLogado() {
        return isLogado;
    }

    public void setLogado(boolean logado) {
        isLogado = logado;
    }

    @Override
    public void mostraDados() {
        System.out.println("ID: " + id +
                "\nNome: " + nome +
                "\nDepartamento: " + departamento +
                "\nEmail: " + email +
                "\nHorário: " + horarioDisponivel);
    }

    public void mostraEnviadas() {
        for (Mensagem msg : mensagensEnviadas) {
            System.out.println("Mensagem enviada: " + msg.getConteudo() +
                    "\nData: " + msg.getDataHora() +
                    "\nDestinatário (Aluno): " + msg.getRemetente().getNome());
        }
    }

    public void mostraRecebidas() {
        for (Mensagem msg : mensagensRecebidas) {
            System.out.println("Mensagem recebida: " + msg.getConteudo() +
                    "\nData: " + msg.getDataHora() +
                    "\nEnviada por (Aluno): " + msg.getRemetente().getNome());
        }
    }

}
