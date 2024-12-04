package br.com.gerenciador.servicos;

import br.com.gerenciador.entidades.Aluno;
import br.com.gerenciador.entidades.Professor;

import java.time.LocalDateTime;

public class Mensagem {
    private String conteudo;
    private LocalDateTime dataHora;
    private Aluno remetente;
    private Professor destinatario;

    public Mensagem(){
    }

    public Mensagem(String conteudo, LocalDateTime dataHora, Aluno remetente) {
        this.conteudo = conteudo;
        this.dataHora = dataHora;
        this.remetente = remetente;
    }

    public Mensagem(String conteudo, LocalDateTime dataHora, Professor destinatario) {
        this.conteudo = conteudo;
        this.dataHora = dataHora;
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Aluno getRemetente() {
        return remetente;
    }

    public void setRemetente(Aluno remetente) {
        this.remetente = remetente;
    }

    public Professor getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Professor destinatario) {
        this.destinatario = destinatario;
    }
}
