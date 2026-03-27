package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Usuario;
import com.yubiniperez.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try {
            if (!usuarioService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            if (!usuarioService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }

            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> activos(){
        List<Usuario> usuariosActivos = usuarioService.listarActivos();
        return ResponseEntity.ok(usuariosActivos);
    }

}
