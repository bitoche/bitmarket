<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Список "${listTitle}"</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">

</head>
<body>
<#include "schemes/navbar.ftl">
<div class="page-body">
    <div class="body-content">
        <#if itemList?? && itemList?has_content>
            <#list itemList as item>
                <div class="r-block-h-g list-item">
                    <a class="text-a" href="${}">
                        Ссылка
                    </a>
                </div>
            </#list>
            <#else>
            Здесь пока пусто. Сюда вы можете добавить объекты.
        </#if>

    </div>
</div>
</body>
</html>