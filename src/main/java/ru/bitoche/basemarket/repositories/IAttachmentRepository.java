package ru.bitoche.basemarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitoche.basemarket.models.Attachment;
@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {
}
