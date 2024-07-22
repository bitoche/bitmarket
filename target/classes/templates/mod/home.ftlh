<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Модерирование</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/ns.css">
</head>
<body>
<#include "../schemes/navbar.ftl"/>
<div class="page-body">
    <div class="body-content">
        <div class="ns-actions-container">
            <div class="ns-action-c">
                <div class="ns-act-title-c">
                    <p>Управление тегами</p>
                </div>
                <div class="ns-act-body-c">
                    <a class="ns-confirm-button" href="#tagsTable" onclick="openTagsTable()">Просмотреть все теги</a>
                    <#include "schemes/abs-tags-table.ftl">
                </div>
                <div class="ns-act-bottom-c">
                    <a class="ns-confirm-button" href="#confirmTagAdding" onclick="confirmTagAdding()">Подтвердить</a>
                </div>
            </div>
            <div class="ns-action-c">
                <div class="ns-act-title-c">
                    <p>Управление продуктами</p>
                </div>
                <div class="ns-act-body-c">

                </div>
                <div class="ns-act-bottom-c">
                    <a class="ns-confirm-button" href="#productMgmt" onclick="productMgmt()">Подтвердить</a>
                </div>
            </div>
            <div class="ns-action-c">
                <div class="ns-act-title-c">
                    <p>Добавление продукта</p>
                </div>
                <div class="ns-act-body-c">
                    <form id="obj-adding-form" class="obj-adding-form" >
                        <input id="tit-input" required class="obj-adding-input-title" type="text" name="name" placeholder="Наименование"/>
                        <textarea id="desc-input" required class="obj-adding-desc-textarea" name="description">Описание</textarea>
                        <div class="obj-image-adding-div">
                            <label class="obj-image-adding-label">
                                Выберите картинку-постер для объекта в формате .jpeg (370x460)
                            </label>
                            <div class="image-selector+preview">
                                <input id="img-c-input" required oninput="previewImage(this.files[0])" class="obj-adding-img-select" name="imageCard" type="file" accept="image/*">
                                <div class="image-preview">
                                    <p class="preview-title">Превью</p>
                                    <img class="img-select-preview" id="preview">
                                </div>

                            </div>
                            <script>
                                function previewImage(file){
                                    const reader = new FileReader();
                                    reader.onload = () => document.getElementById("preview").src = reader.result;
                                    reader.readAsDataURL(file);
                                }
                                function confirmProductAdding(){
                                    let currForm = document.getElementById("obj-adding-form");
                                    if(currForm.checkValidity()){
                                        var formData = new FormData();
                                        formData.append("name", document.getElementById("tit-input").value)
                                        formData.append("description", document.getElementById("desc-input").value)
                                        formData.append("ICard", document.getElementById("img-c-input").files[0])
                                        var request = new XMLHttpRequest();
                                        request.open("POST", "/mod/addObject");
                                        request.send(formData);
                                    }
                                }
                            </script>
                        </div>
                    </form>
                </div>
                <div class="ns-act-bottom-c">
                    <a class="ns-confirm-button" href="#confirmProductAdding" onclick="confirmProductAdding()">Подтвердить</a>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>