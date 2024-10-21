package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.PasswordRecoveryToken;
@Repository
public interface IPasswordRecoveryTokenRepository extends JpaRepository<PasswordRecoveryToken, Long> {
    PasswordRecoveryToken findPasswordRecoveryTokenByRecoveryToken(String recoveryToken);
}
