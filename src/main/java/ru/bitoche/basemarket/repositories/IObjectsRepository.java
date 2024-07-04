package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.AnObject;
@Repository
public interface IObjectsRepository extends JpaRepository<AnObject, Long> {
}
