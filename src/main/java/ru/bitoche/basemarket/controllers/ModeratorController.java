package ru.bitoche.basemarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bitoche.basemarket.models.AnTag;
import ru.bitoche.basemarket.models.TagGroup;
import ru.bitoche.basemarket.services.AppUserService;
import ru.bitoche.basemarket.services.ObjectService;
import ru.bitoche.basemarket.services.TagService;

import java.io.File;
import java.security.Principal;
import java.sql.Blob;
import java.util.ArrayList;

@Controller
@AllArgsConstructor
@RequestMapping("/mod")
public class ModeratorController {
    private AppUserService userService;
    private TagService tagService;
    private ObjectService objectService;
    @GetMapping("/")
    public String returnModHome(Model model, Principal principal){
        model.addAttribute("princ", userService.getUserByUsername(principal.getName()));
        model.addAttribute("entryLink", "modPanel");
        model.addAttribute("tagsList", tagService.findAllTagsWithGroups());
        return "mod/home";
    }
    @PostMapping("/addToTagGroup")
    public String addToTagGroup(
            @RequestParam String name,
            @Nullable @RequestParam Boolean isGroup,
            @RequestParam String inTagGroupId
    ){
        var inGroup = tagService.findTagGroupById(Long.parseLong(inTagGroupId));
        if(isGroup!=null && isGroup){
            var newTagGroup = new TagGroup(
                    null,
                    name,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    false
            );
            tagService.saveGroup(newTagGroup);
            inGroup.addUnderGroup(newTagGroup);
        }
        if (isGroup==null || !isGroup) {
            var newTag = new AnTag(
                    null,
                    name,
                    inGroup
            );
            tagService.save(newTag);
            inGroup.addTag(newTag);
        }
        tagService.saveGroup(inGroup);
        return "redirect:/mod/";
    }

    @GetMapping("/deleteTagGroup/{tagGroupId}")
    public String deleteTagGroup(@PathVariable Long tagGroupId){
        var currTagGroup = tagService.findTagGroupById(tagGroupId);
        assert currTagGroup.getUnderGroups() != null;
        if(currTagGroup.getUnderGroups().isEmpty()){
            tagService.deleteTagGroupById(tagGroupId);
        }
        return "redirect:/mod/";
    }

    @GetMapping("/deleteTag/{tagId}") //todo не работает удаление из-за неработающего каскадного удаления, починить. Костыль - пофиксить через выставление типа данных каскейд в бд
    public String deleteTag(@PathVariable Long tagId, Model model, Principal principal){
        var currTag = tagService.findTagById(tagId);
        var objectsWithThisTag = objectService.getObjectsByTagId(tagId);
        if(!objectsWithThisTag.isEmpty()){
            model.addAttribute("princ", userService.getUserByUsername(principal.getName()));
            model.addAttribute("objectsToChangeTag", objectsWithThisTag);
            model.addAttribute("allTagGroups", tagService.findAllTagsWithGroups());
            return "mod/conflictResolution";
        }
        else{
            tagService.deleteTagById(tagId);
        }
        return "redirect:/mod/";
    }
    @PostMapping("/addObject")
    public String addObject(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam MultipartFile ICard
    ){
        System.out.println("FILE LOADED: "+ICard.getName()+"///"+ICard.getOriginalFilename()+"\nfiletype: "+ICard.getContentType());
        //todo Допилить сохранение в папку на пк, так же создать метод который их будет с пк подгружать. Нужен FileService
        return "redirect:/mod/";
    }
}
