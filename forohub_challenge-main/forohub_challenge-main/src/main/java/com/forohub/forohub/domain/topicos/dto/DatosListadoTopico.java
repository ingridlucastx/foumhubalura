package com.forohub.forohub.domain.topicos.dto;

import com.forohub.forohub.domain.topicos.Estado;
import com.forohub.forohub.domain.topicos.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado status,
        String autor,
        String nombreCurso,
        LocalDateTime fecha
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getLogin(),
                topico.getNombreCurso(),
                topico.getFecha()
        );
    }
}
