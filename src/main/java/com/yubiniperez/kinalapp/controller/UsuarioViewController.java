package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Usuario;
import com.yubiniperez.kinalapp.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping ("/vistas/usuarios")
public class UsuarioViewController {
    
    private final UsuarioService usuarioService;
    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "pages/usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Usuario usuario = new Usuario();
        usuario.setEstado(1);
        model.addAttribute("usuario",usuario);
        model.addAttribute("titulo", "nuevo usuario");
        return "pages/usuario-form";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        usuarioService.buscarPorId(id).ifPresent(u -> model.addAttribute("usuario",u));
        model.addAttribute("titulo",  "editar Usuario");
        return "pages/usuario-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") Usuario usuario){
        if(usuario.getEstado() == null) {
            usuario.setEstado(1);
        }
        usuarioService.guardar(usuario);
        return "redirect:/vistas/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        try {
            usuarioService.eliminar(id);
        } catch (Exception e) {
            return "redirect:/vistas/usuarios?error=fk";
        }
        return "redirect:/vistas/usuarios";
    }


}
