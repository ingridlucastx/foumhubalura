package com.forohub.forohub.domain.topicos.validaciones;

import com.forohub.forohub.domain.autores.AutorRepository;
import com.forohub.forohub.domain.topicos.Topico;
import com.forohub.forohub.domain.topicos.TopicoRepository;
import com.forohub.forohub.domain.topicos.dto.DatosRegistroTopico;
import com.forohub.forohub.domain.topicos.validaciones.Validadores;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidadorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<Validadores> validadores;

    public Topico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        var autorId = datosRegistroTopico.autorId();
        if (autorId != null && !autorRepository.existsById(autorId)) {
            throw new ValidationException("No existe ningun autor con ese id");
        }

        validadores.forEach(v -> v.validarDatos(datosRegistroTopico));

        var autor = autorRepository.getReferenceById(autorId);
        var topico = new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                LocalDateTime.now(),
                datosRegistroTopico.status(),
                autor,
                datosRegistroTopico.nombreCurso()
        );
        return topicoRepository.save(topico);
    }

    public Topico validarExistencia(Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return topico;
        }
        throw new EntityNotFoundException();
    }
}
