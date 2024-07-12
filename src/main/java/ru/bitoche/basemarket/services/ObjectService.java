package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.models.AnObject;
import ru.bitoche.basemarket.repositories.IObjectRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ObjectService {
    IObjectRepository objectRepository;
    public List<AnObject> findAll(){
        return objectRepository.findAll().stream().toList();
    }
    public AnObject findById(Long id){
        return objectRepository.existsById(id) ? objectRepository.findById(id).get() : null;
    }
    public void save(AnObject object){
        objectRepository.save(object);
    }
    public void deleteById(Long id){
        objectRepository.deleteById(id);
    }
    public List<AnObject> getObjectsByTagId(Long tagId){
        return findAll().stream().filter(o->o.getTags().stream().anyMatch(t-> Objects.equals(t.getId(), tagId))).toList();
    }
}
