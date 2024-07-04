package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.AnTag;
@Repository
public interface ITagsRepository extends JpaRepository<AnTag, Long> {
}
