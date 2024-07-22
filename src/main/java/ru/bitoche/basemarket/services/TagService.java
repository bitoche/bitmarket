package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.models.AnTag;
import ru.bitoche.basemarket.models.TagGroup;
import ru.bitoche.basemarket.repositories.ITagGroupRepository;
import ru.bitoche.basemarket.repositories.ITagsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    ITagsRepository tagsRepository;
    ITagGroupRepository tagGroupRepository;
    public void save(AnTag tag){
        tagsRepository.save(tag);
    }
    public void saveGroup(TagGroup group){
        tagGroupRepository.save(group);
    }
    @Cascade(CascadeType.DELETE_ORPHAN)
    public void deleteTagById(Long id){
        tagsRepository.deleteById(id);
    }
    @Cascade(CascadeType.DELETE_ORPHAN)
    public void deleteTagGroupById(Long id){
        tagGroupRepository.deleteById(id);
    }
    public AnTag findTagById(Long id){
        return tagsRepository.existsById(id)
                ? tagsRepository.findById(id).get()
                : null;
    }
    public TagGroup findTagGroupById(Long id){
        return  tagGroupRepository.existsById(id)
                ? tagGroupRepository.findById(id).get()
                : null;
    }
    public List<AnTag> findAllTags(){
        return tagsRepository.findAll().stream().toList();
    }
    public List<TagGroup> findAllTagsWithGroups(){
        var allTagGroups = new ArrayList<>(tagGroupRepository.findAll().stream().toList());
        if (allTagGroups.isEmpty()){
            var mainTagGroup = new TagGroup(null,
                    "Все",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    true);
            saveGroup(mainTagGroup);
            allTagGroups.add(mainTagGroup);
        }
        return allTagGroups;
    }
}
