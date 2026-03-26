package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    //Metodo que devuelve un listado de los clientes
    List<Usuario> listarUsuarios();

    //Metodo que devuelve un listado de los clientes activos
    List<Usuario> listarActivos();

    //Metodo que crea/agrega nuevo usuario
    Usuario guardar(Usuario usuario);

    //Metodo que busca usuario por id
    Optional<Usuario> buscarPorId(Long id);

    //Metodo que permite actualizar un usuario
    Usuario actualizar(Long id, Usuario usuario);

    //Metodo que elimina usuarios por medio del id
    void eliminar(Long id);

    //Metodo que verifica si existe un usuario por id
    boolean existePorId(Long id);
}
