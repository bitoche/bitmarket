<#--abs-tags-table.ftl-->
<div class="tags-table-container">
    <a class="table-close-btn" href="#" onclick="closeTagsTable()">Закрыть</a>
    <div class="tags">
        <#if tagsList?? && tagsList?has_content>
            <#list tagsList as tagGroup> <#--перебираем все группы тегов-->
                <#if tagGroup.isRoot()> <#--если текущая группа - корневая-->
                    <@treeView tagGroup/>
                    <#macro treeView Group>
                        <ul class="ul-group">
                            ${Group.name}
                            <a class="ns-tag-act-btn add-btn" href="#addtag" onclick="addTagMenuToggle(${Group.id})"><small id="plus-btn-${Group.id}">+</small></a>
                            <#if Group.getUnderGroups()?has_content || Group.getTags()?has_content>
                                <#assign countOfUnderTags = Group.getTags()?size>
                                <#assign countOfUnderGroups = Group.getUnderGroups()?size>
                                <#assign myAlertMessage="Для удаления группы тегов необходимо удалить все внутренние подгруппы и теги.">
                                <a class="help-btn" href="#?" title="${myAlertMessage}" onclick="myAlert(1)">
                                    <small>?</small>
                                </a>
                                <script>
                                    function myAlert(num){
                                        if(num===1){
                                            alert("${myAlertMessage}")
                                        }
                                    }
                                </script>
                            </#if>
                            <ul class="hidden-add-form" id="group-${Group.id}">
                                <div class="hidden-add-div">
                                    <form class="add-form" action="/mod/addToTagGroup" method="post">
                                        <input hidden name="inTagGroupId" value="${Group.id}"/>
                                        <input name="name" placeholder="Название" type="text"/>
                                        <label>
                                            Отметьте, если хотите создать группу
                                            <input name="isGroup" type="checkbox" />
                                        </label>
                                        <button type="submit">Подтвердить</button>
                                    </form>
                                </div>
                            </ul>
                            <#if !Group.getUnderGroups()?has_content && !Group.getTags()?has_content>
                                <a href="#deleteTagGroup" onclick="deleteTagGroup(${Group.id}, '${Group.name}')">
                                    <small>Удалить пустую группу</small>
                                </a>
                            </#if>
                            <#if Group.getTags()?? && Group.getTags()?has_content> <#--если у этой группы есть теги-->
                                <ul class="ul-group">
                                    <#list Group.getTags() as tag> <#--перебираем теги этой подгруппы-->
                                        <li>
                                            <a class="tag-href" href="/tag/${tag.getId()}" target="_blank">${tag.getName()}</a>
                                            <a class="remove-btn ns-tag-act-btn" href="#deletetag" onclick="deleteTag(${tag.getId()}, '${tag.getName()}')"><small>-</small></a>
                                        </li>
                                    </#list>
                                </ul>
                            </#if>
                            <#if Group.getUnderGroups()?? && Group.getUnderGroups()?has_content> <#--если у этой группы есть подгруппы-->
                                <#list Group.getUnderGroups() as underGroup>
                                    <@treeView underGroup/>
                                </#list>
                            </#if>
                        </ul>
                    </#macro>
                </#if>
                <#break>
            </#list>
        <#else>

        </#if>
    </div>
</div>
<script>
    function addTagMenuToggle(tagGroupId){
        let currGroup = document.getElementById("group-"+tagGroupId);
        currGroup.classList.toggle("hidden-add-form");
        let currPlusBtn = document.getElementById("plus-btn-"+tagGroupId);
        if(currPlusBtn.textContent === "+"){
            currPlusBtn.textContent = "↓";
        }else{
            currPlusBtn.textContent = "+";
        }

    }
    function deleteTag(tagId, tagName){
        if(confirm('Вы действительно хотите удалить тег "'+tagName+'"?\nЭто действие необратимо.')){
            document.location.href = "/mod/deleteTag/"+tagId; //todo добавить метод в контроллер модера
        }
    }
    function deleteTagGroup(tagGroupId, tagGroupName){
        if(confirm('Вы действительно хотите удалить группу тегов "'+tagGroupName+'"?\nЭто действие необратимо.')){
            document.location.href = "/mod/deleteTagGroup/"+tagGroupId; //todo добавить метод в контроллер модера
        }
    }
</script>
<#--/abs-tags-table.ftl-->