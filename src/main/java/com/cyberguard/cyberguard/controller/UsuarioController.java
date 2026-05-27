package com.cyberguard.cyberguard.controller;

import com.cyberguard.cyberguard.entity.Usuario;
import com.cyberguard.cyberguard.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") 
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        return service.cadastrar(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return service.login(usuario.getEmail(), usuario.getSenha());
    }

    @GetMapping("/login-teste")
    public String loginTeste() {
        service.login("vinicius@email.com", "123");
        return "logado";
    }
}