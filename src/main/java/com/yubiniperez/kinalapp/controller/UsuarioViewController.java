package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Usuario;
import com.yubiniperez.kinalapp.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vistas/usuarios")
public class UsuarioViewController {
    
    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "busqueda", required = false) Long busqueda, 
                        @RequestParam(name = "activos", required = false, defaultValue = "false") boolean activos,
                        Model model) {
        List<Usuario> lista;

        if (busqueda != null){
            lista = new ArrayList<>();
            Optional<Usuario> usuarioOpcional = usuarioService.buscarPorId(busqueda);
            if (usuarioOpcional.isPresent()){
                lista.add(usuarioOpcional.get());
            }
            model.addAttribute("mensajeBusqueda", "Resultado del ID: " + busqueda);
        } else if (activos){
            lista = usuarioService.listarActivos();
        } else{
            lista = usuarioService.listarUsuarios();
        }

        model.addAttribute("usuarios", lista);
        model.addAttribute("soloActivos", activos);
        return "pages/usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        Usuario usuario = new Usuario();
        usuario.setEstado(1);
        
        model.addAttribute("entidad", usuario);
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("tipo", "usuario"); 
        return "pages/form/form-entity";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Optional<Usuario> usuarioOpcional = usuarioService.buscarPorId(id);
        
        if (usuarioOpcional.isPresent()){
            model.addAttribute("entidad", usuarioOpcional.get());
        }
        
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("tipo", "usuario");
        return "pages/form/form-entity";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("entidad") Usuario usuario){
        if (usuario.getCodigoUsuario() != null){
            usuarioService.actualizar(usuario.getCodigoUsuario(), usuario);
        } else{
            usuarioService.guardar(usuario);
        }
        return "redirect:/vistas/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        try {
            usuarioService.eliminar(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return "redirect:/vistas/usuarios?error=fk";
        }
        return "redirect:/vistas/usuarios";
    }
}