package com.jornadadev.mercadolivre.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UserDetailsMapper userDetailsMapper;
    private final EntityManager manager;

    @Value("${security.username-query}")
    private String query;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        List<?> objects = manager.createQuery(query)
                .setParameter("username", username).getResultList();
        Assert.isTrue(objects.size() <= 1,
                "[BUG] mais de um autenticável tem o mesmo username. "
                + username);

        if (objects.isEmpty()) {
            throw new UsernameNotFoundException(
                    "Não foi possível encontrar usuário com email: "
                    + username);
        }

        return userDetailsMapper.map(objects.get(0));
    }
}
