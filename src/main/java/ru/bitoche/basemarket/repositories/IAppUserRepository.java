package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.AnObject;
import ru.bitoche.basemarket.models.AppUser;

import java.util.Optional;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);

    //для регистрации по имейл
    AppUser findByEmailIgnoreCase(String emailId);
    Boolean existsByEmail(String email);
}
