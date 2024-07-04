package com.forohub.forohub.domain.topicos.dto;

import com.forohub.forohub.domain.topicos.Estado;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado status,
        String autor,
        String nombreCurso,
        LocalDateTime fecha
) {
}
