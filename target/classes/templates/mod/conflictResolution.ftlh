<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Решение конфликта</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/conflict-resolution.css">
    <link rel="stylesheet" href="/css/object-cards.css">
</head>
<body>
<#include "../schemes/navbar.ftl"/>
<div class="page-body">
    <div class="body-content">
        <form method="get" action="/mod/conflictResolution">
        <#list objectsToChangeTag as obj>
            <div class="obj-card">
                <a class="obj-card-title" href="/obj/${obj.getId()}">${obj.getTitle()}</a>
                <img class="obj.card" src="${obj.ICard}">
                <p class="obj-card-desc">${obj.getDescription()}</p>
                <select name="toTagId">
                    <#list allTags as tag>
                        <option value="${tag.getId()}">${tag.getName()}</option>
                    </#list>
                </select>
            </div>
        </#list>
        </form>
    </div>
</div>
</body>
</html>