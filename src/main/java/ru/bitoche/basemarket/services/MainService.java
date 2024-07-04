package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.models.AnObject;
import ru.bitoche.basemarket.models.AnTag;
import ru.bitoche.basemarket.repositories.IObjectsRepository;
import ru.bitoche.basemarket.repositories.ITagsRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    IObjectsRepository objectsRepository;
    ITagsRepository tagsRepository;

    //mainopt

    //objects
    public List<AnObject> getAllObjects(){
        return objectsRepository.findAll().stream().toList();
    }
    public AnObject getObjectById(long id){
        return objectsRepository.existsById(id) ? objectsRepository.findById(id).get() : null;
    }
    public void save(AnObject anObject){
        objectsRepository.save(anObject);
    }
    public void deleteObjectById(long id){
        objectsRepository.deleteById(id);
    }

    //tags
    public List<AnTag> getAllTags(){
        return tagsRepository.findAll().stream().toList();
    }
    public AnTag getTagById(long id){
        return tagsRepository.existsById(id) ? tagsRepository.findById(id).get() : null;
    }
    public void save(AnTag anTag){
        tagsRepository.save(anTag);
    }
    public void deleteTagById(long id){
        tagsRepository.deleteById(id);
    }
}
