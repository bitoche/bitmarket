package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.models.Attachment;
import ru.bitoche.basemarket.repositories.IAttachmentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentService {
    IAttachmentRepository attachmentRepository;
    public List<Attachment> getAll(){
        return attachmentRepository.findAll().stream().toList();
    }
    public void save(Attachment attachment){
        attachmentRepository.save(attachment);
    }
    public void deleteById(long id){
        attachmentRepository.deleteById(id);
    }
}
