package com.apifed.buscador.extra;

import com.apifed.buscador.entitys.Autos;

public class ResultadoBusqueda {
    private Autos auto;
    private String mensajeError;

    public ResultadoBusqueda(Autos auto, String mensajeError) {
        this.auto = auto;
        this.mensajeError = mensajeError;
    }

    public Autos getAuto() {
        return auto;
    }

    public void setAuto(Autos auto) {
        this.auto = auto;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}