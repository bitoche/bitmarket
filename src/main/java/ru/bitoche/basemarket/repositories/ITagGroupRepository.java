package ru.bitoche.basemarket.repositories;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.TagGroup;

import java.util.Optional;

@Repository
public interface ITagGroupRepository extends JpaRepository<TagGroup, Long> {
    Optional<TagGroup> findTagGroupByName(String name);
}
