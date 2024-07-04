package ru.bitoche.basemarket.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnObject {
    @Id
    private Long id;
    private String title;
    private String description;
    @Nullable
    private String objectVersion;
    @ManyToMany
    private List<AnTag> tags;

    //images
    @Nullable
    private String ISmall;
    @Nullable
    private String IPreview;
    @Nullable
    private String ILarge;
    @Nullable
    private String IMedium;
    @Nullable
    private String ICard;
}
