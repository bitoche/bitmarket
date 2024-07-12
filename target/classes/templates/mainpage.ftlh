<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BitMarket</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">

</head>
<body>
<#include "schemes/navbar.ftl">
<div class="page-body">
    <div class="body-content">
        <div class="r-block-h-g">
            <div class="obj-card-line">
                <#list allObjects as obj>
                    <div class="obj-card">
                        <img loading="lazy" class="obj-card-i-card" src="${imgFilePath}${obj.ICard}">
                        <a href="/obj/${obj.getId()}">${obj.getTitle()}</a>
                        <p>${obj.getDescription()}</p>
                        <#if obj.getTags()?? && obj.getTags()?has_content>
                            <#list obj.getTags() as objTag>
                                <a href="/tag/${objTag.getId()}">${objTag.getName()}</a>
                            </#list>
                        </#if>
                    </div>
                </#list>
            </div>
            <a class="text-a" href="#">Ссылка</a>
        </div>
    </div>
</div>
</body>
</html>