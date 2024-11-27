package services;

import entities.Usuario;

import java.time.LocalDateTime;

public class Mensagem {
    public String conteudo;
    public LocalDateTime dataHora;
    public Usuario destino;

    public Mensagem(String conteudo, LocalDateTime dataHora, Usuario destino) {
        this.conteudo = conteudo;
        this.dataHora = dataHora;
        this.destino = destino;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getData() {
        return dataHora;
    }

    public void setData(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }
}
