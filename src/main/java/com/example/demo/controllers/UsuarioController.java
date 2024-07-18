package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

    @Autowired(required = false)
    private UsuarioService usuarioService;

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestParam String nombre, @RequestParam String correo, @RequestParam String pass) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPass(passwordEncoder.encode(pass));
        usuarioRepository.save(usuario);

        return "redirect:/login";
    }


    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long rut) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByRut(rut);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearOActualizarUsuario(usuario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long rut, @RequestBody Usuario usuario) {
        if (usuarioService.getUsuarioByRut(rut).isPresent()) {
            usuario.setRut(rut);
            return ResponseEntity.ok(usuarioService.crearOActualizarUsuario(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long rut) {
        if (usuarioService.getUsuarioByRut(rut).isPresent()) {
            usuarioService.eliminarUsuario(rut);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
