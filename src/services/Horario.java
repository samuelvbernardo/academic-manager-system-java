package services;

import entities.Usuario;

import java.time.LocalDateTime;

public class Horario {
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    Mensagem mensagem;
    Usuario usuario;

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDateTime horaFim) {
        this.horaFim = horaFim;
    }

    public void validarHorario(){
        if (getHoraInicio().equals(mensagem.dataHora)){

        };
    }
}
