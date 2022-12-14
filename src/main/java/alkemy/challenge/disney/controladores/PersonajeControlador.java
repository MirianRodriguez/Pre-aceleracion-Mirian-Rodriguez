package alkemy.challenge.disney.controladores;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.disney.dto.PersonajeBasicoDto;
import alkemy.challenge.disney.dto.PersonajeDto;
import alkemy.challenge.disney.servicios.PersonajeServicio;

@RestController
@RequestMapping("personajes")
public class PersonajeControlador {
    
    @Autowired
    private PersonajeServicio personajeServicio;

    @PostMapping
    public ResponseEntity<PersonajeDto> crear(@RequestBody PersonajeDto personajeDto){
        PersonajeDto personajeGuardado = personajeServicio.guardar(personajeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @GetMapping("/{personajeId}")
    public ResponseEntity<PersonajeDto> detallePersonaje(@PathVariable Long personajeId) throws Exception{
        PersonajeDto personaje = personajeServicio.buscarPorId(personajeId);
        return ResponseEntity.status(HttpStatus.OK).body(personaje);
    }

    @PutMapping("/{personajeId}")
    public ResponseEntity<PersonajeDto> actualizar(
        @PathVariable Long personajeId, 
        @RequestBody PersonajeDto personajeDto
        ) throws Exception{
        PersonajeDto personajeActualizado = personajeServicio.actualizar(personajeId, personajeDto);
        return ResponseEntity.ok().body(personajeActualizado);
    }

    @DeleteMapping("/{personajeId}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable Long personajeId) throws Exception{
        personajeServicio.eliminarPorId(personajeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonajeBasicoDto>> buscarPorFiltros(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) Integer edad,
        @RequestParam(required = false) Set<Long> peliculasId
    ){
        List<PersonajeBasicoDto> personajes = personajeServicio.buscarPorFiltros(nombre, edad, peliculasId);
        return ResponseEntity.ok(personajes);
    }

}
