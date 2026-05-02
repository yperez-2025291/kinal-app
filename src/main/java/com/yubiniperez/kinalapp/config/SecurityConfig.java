package com.yubiniperez.kinalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // rutas publicas para todos los usuarios
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/static/css/**").permitAll()        

            // rutas para el admin todopoderoso
                .requestMatchers( //usuarios
                    "/usuarios",
                    "/usuarios/**",
                    "/usuario/**",
                    "/pages/usuarios.html",
                    "/pages/form/usuario-form.html"
                ).hasRole("ADMIN")

                .requestMatchers( //clientes
                    "/clientes",
                    "/clientes/**",
                    "/cliente/**",
                    "/pages/clientes.html",
                    "/pages/form/cliente-form.html"
                ).hasRole("ADMIN")

                .requestMatchers(  //productos
                    "/productos",
                    "/productos/**",
                    "/producto/**",
                    "/pages/productos.html",
                    "/pages/form/productos-form.html"
                ).hasRole("ADMIN")
                

            //rutas para el admin y los usuarios normales
                .requestMatchers( //ventas
                    "/ventas",
                    "/ventas/**",
                    "/venta/**",
                    "/pages/ventas.html",
                    "/pages/form/venta-form.html"
                ).hasAnyRole("USER", "ADMIN")

                .requestMatchers( //detalle ventas
                    "/detalles",
                    "/detalles/**",
                    "/detalle/**",
                    "/pages/detalles.html",
                    "/pages/form/detalle-form.html"
                ).hasAnyRole("USER", "ADMIN")
                
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        // usuario normal 
        UserDetails user = User.builder()
            .username("user")
            .password("user123")
            .roles("USER")
            .build();

        // usuario admin
        UserDetails admin = User.builder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
