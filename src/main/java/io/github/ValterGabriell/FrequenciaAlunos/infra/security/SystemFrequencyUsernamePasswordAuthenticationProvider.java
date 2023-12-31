package io.github.ValterGabriell.FrequenciaAlunos.infra.security;

import io.github.ValterGabriell.FrequenciaAlunos.domain.Admin;
import io.github.ValterGabriell.FrequenciaAlunos.exceptions.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.AdminRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SystemFrequencyUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemFrequencyUsernamePasswordAuthenticationProvider(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Optional<Admin> admin = adminRepository.findByCnpj(username);
        String password = authentication.getCredentials().toString();

        if (admin.isEmpty()) throw new RequestExceptions("Admin não encontrado!");
        if (!passwordEncoder.matches(password, admin.get().getPassword()))
            throw new RequestExceptions("Senha inválida");

        List<GrantedAuthority> authorities = new ArrayList<>();
        admin.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getValue())));
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}