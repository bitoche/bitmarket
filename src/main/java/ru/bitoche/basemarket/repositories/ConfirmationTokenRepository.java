package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bitoche.basemarket.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
