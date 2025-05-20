package SIGEV.PlanTrabajo.Rol;

import SIGEV.PlanTrabajo.Unidad.Unidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "https://plan-trabajo-itst.vercel.app")
@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<Iterable<Rol>> findAll() {
        return ResponseEntity.ok(rolRepository.findAll());
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<Rol> findById(@PathVariable Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isPresent()) {
            return ResponseEntity.ok(rolOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol, UriComponentsBuilder uriBuilder) {
        Rol created = rolRepository.save(rol);
        URI uri = uriBuilder.path("rol/{idRol}").buildAndExpand(created.getIdRol()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Void> update(@PathVariable Long idRol, @RequestBody Rol rol) {
        Rol rolAnterior = rolRepository.findById(idRol).get();
        if (rolAnterior != null) {
            rol.setIdRol(rolAnterior.getIdRol());
            rolRepository.save(rol);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Void> delete(@PathVariable Long idRol) {
        if (rolRepository.findById(idRol).isPresent()) {
            rolRepository.deleteById(idRol);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*
    //Obtener usuarios por rol
    @GetMapping("/unidades/{idRol}")
    public ResponseEntity<Iterable<Unidad>>RolUsuario(@PathVariable Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isPresent()) {
            return ResponseEntity.ok(rolOptional.get().getUnidades());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     */
}
