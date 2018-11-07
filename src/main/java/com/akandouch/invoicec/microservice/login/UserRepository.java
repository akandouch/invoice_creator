package com.akandouch.invoicec.microservice.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserDetailsService {
    @Override
    default UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.findOneByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s + " not found"));
    }

    default User principalToUser(Principal p) {
        return this.findOneByUsername(p.getName()).orElseThrow(() ->
                new RuntimeException(String.format("Principal %s not found", StringUtils.defaultIfEmpty(p.getName(), "USERNAME_NULL")))
        );
    }

    Optional<User> findOneByUsername(String username);
}

