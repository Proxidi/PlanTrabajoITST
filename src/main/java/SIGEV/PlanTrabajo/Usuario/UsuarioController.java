package SIGEV.PlanTrabajo.Usuario;

import SIGEV.PlanTrabajo.Unidad.Unidad;
import SIGEV.PlanTrabajo.Unidad.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = {"https://plan-trabajo-itst-git-main-proxidis-projects.vercel.app", "*"})
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> findById(@PathVariable String idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(usuario.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setUnidad(unidadOptional.get());
        Usuario created = usuarioRepository.save(usuario);
        URI uri = uriBuilder.path("usuario/{idUsuario}").buildAndExpand(created.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Void> update(@PathVariable String idUsuario, @RequestBody Usuario usuario) {
        Optional<Unidad> unidadOptional = unidadRepository.findById(usuario.getUnidad().getIdUnidad());
        if (!unidadOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioAnterior = usuarioOptional.get();
            usuarioAnterior.setNombre(usuario.getNombre());
            usuarioAnterior.setApPaterno(usuario.getApPaterno());
            usuarioAnterior.setApMaterno(usuario.getApMaterno());
            usuarioAnterior.setContrasena(usuario.getContrasena());
            usuarioAnterior.setUnidad(unidadOptional.get());
            usuarioRepository.save(usuarioAnterior);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable String idUsuario) {
        if (usuarioRepository.findById(idUsuario).isPresent()) {
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
