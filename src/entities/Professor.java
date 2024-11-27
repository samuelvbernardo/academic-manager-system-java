package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Professor extends Usuario {
    private String departamento;
    private String email;
    private LocalDateTime horarioDisponivel;

    ArrayList<Mensagem> mensagensEnviadas = new ArrayList<>();
    ArrayList<Mensagem> mensagensRecebidas = new ArrayList<>();

    public Professor(int id, String nome, String cpf, String login, String senha, String departamento, String email, LocalDateTime horarioDisponivel) {
        super(id, nome, cpf, login, senha);
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

    public LocalDateTime getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(LocalDateTime horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    @Override
    public void mostraDados() {
        System.out.println("ID: " + id + "\nNome: " + nome + "\nDepartamento: " + departamento + "\nEmail: " + email);
    }

    public void enviarMensagens(Mensagem mensagem){
        mensagensEnviadas.add(mensagem);
    }

    public void mostraEnviadas(){
        for (Mensagem msg : mensagensEnviadas){
            System.out.println(msg.conteudo + "\nData: " + msg.dataHora + "\n Destino: " + msg.destino);
        }
    }

    public void mostraRecebidas(){
        for (Mensagem msg : mensagensRecebidas){
            System.out.println(msg.conteudo + "\nData: " + msg.dataHora + "\n Destino: " + msg.destino);
        }
    }
}
