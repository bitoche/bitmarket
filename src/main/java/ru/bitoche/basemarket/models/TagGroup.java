package ru.bitoche.basemarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Nullable
    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<TagGroup> underGroups;
    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true)
    @Nullable
    private List<AnTag> tags;
    private boolean isRoot;
    public void addUnderGroup(TagGroup tagGroup){
        if(underGroups!=null){
            underGroups.add(tagGroup);
        }
        else{
            underGroups = new ArrayList<>();
            underGroups.add(tagGroup);
        }

    }
    public void addTag(AnTag anTag){
        if(tags!=null){
            tags.add(anTag);
        }
        else{
            tags = new ArrayList<>();
            tags.add(anTag);
        }
    }
}

//Односвязный список
