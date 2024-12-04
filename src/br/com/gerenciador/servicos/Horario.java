package br.com.gerenciador.servicos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public Horario(){

    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }
}
