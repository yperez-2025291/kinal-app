package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Usuario;
import com.yubiniperez.kinalapp.repository.ClienteRepository;
import com.yubiniperez.kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarActivos() {
        return usuarioRepository.findByEstado(1);
    }

    // Metodo que crea un usuario nuevo
    @Override
    public Usuario guardar(Usuario usuario) {
        validarUsuario(usuario);
        if (usuario.getEstado() == 0){
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)){
            throw new RuntimeException("El usuario no se encontro por el id: " + id);
        }
        usuario.setCodigoUsuario(id);
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)){
            throw new RuntimeException("El usuario de id "+ id + "no se encontro");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return false;
    }

    public void validarUsuario (Usuario usuario){
        if (usuario.getUsername() == null  || usuario.getUsername().trim().isEmpty()){
            throw new IllegalArgumentException(("El username es un dato obligatorio"));
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException(("El email es un dato obligatorio"));
        }

        if(usuario.getPassword() == null  || usuario.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException(("El passwor es un dato obligatorio"));
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()){
            throw new IllegalArgumentException(("El rol es un dato obligatorio"));
        }
    }
}
