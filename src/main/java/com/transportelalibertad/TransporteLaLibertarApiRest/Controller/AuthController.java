package com.transportelalibertad.TransporteLaLibertarApiRest.Controller;

import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Rol;
import com.transportelalibertad.TransporteLaLibertarApiRest.Entity.Usuario;
import com.transportelalibertad.TransporteLaLibertarApiRest.Security.AuthRequest;
import com.transportelalibertad.TransporteLaLibertarApiRest.Security.JwtUtil;
import com.transportelalibertad.TransporteLaLibertarApiRest.Security.UserDetailsServiceImpl;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.RolService;
import com.transportelalibertad.TransporteLaLibertarApiRest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RolService rolService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Datos incorrectos Username-Password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        Usuario usuario = null;
            Optional<Usuario> usuarioOptional = usuarioService.findByCorreoElectronico(authRequest.getUsername());
            if (usuarioOptional.isPresent()) {
                usuario = usuarioOptional.get();
            } else {
                return new ResponseEntity<>("No se encontró ningún usuario con el correo electrónico proporcionado.", HttpStatus.NOT_FOUND);
            }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("token", jwt);
        response.put("id", usuario.getId());
        response.put("apellido",usuario.getApellido());
        response.put("nombre", usuario.getNombre());
        response.put("roles", usuario.getRoles());
        response.put("correo",usuario.getCorreoElectronico());
        response.put("imagen",usuario.getImagen());
        response.put("idSucursal",usuario.getSucursal().getId());
//mas atributos pueden faltan
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Usuario usuario) {
        if (usuarioService.findByCorreoElectronico(usuario.getCorreoElectronico()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Este correo ya existe No puede crear multicuentas");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Set<Rol> roles = usuario.getRoles();
        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.badRequest().body("Roles no proporcionados");
        }

        for (Rol rol : roles) {
            Optional<Rol> existingRole = rolService.findById(rol.getId());
            if (existingRole.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol con ID " + rol.getId() + " no encontrado");
            }
        }

        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }
}
