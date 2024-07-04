<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, choosed-theme=light">
    <title>Главная</title>
</head>

<link rel="stylesheet" href="css/elements.css"/>
<link rel="stylesheet" href="css/navbar.css">
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon">
<style>
    body div{
        margin: 0;
        font-family: 'Montserrat';
    }
</style>
<body>
<div class="navbar">
    <a class="r-block-h-g text-a nav-logo" href="/">Главная</a>
    <div class="nav-center-actions">
        <#if princ?? && princ?has_content>
            <#if princ.haveAccessToChangeTags()>
                <a href="/adm/changeTags" class="r-block-h-g text-a">Изменить теги</a>
            </#if>
        </#if>
        <a href="#2" class="r-block-h-g text-a">Действие</a>
        <a href="#3" class="r-block-h-g text-a">Действие</a>
    </div>
    <div class="r-block-h-g hamburger-menu" id="hamburger-menu">
        <div class="profile-img-container">
            <img class="profile-img-container" src="res/img/hamb-ico.png" alt="Menu">
        </div>
    </div>
    <div class="user-act-div">
        <#if princ?? && princ?has_content>
            <div>
                <a href="#notif" class="profile-img-container r-block-h-g sm-padding">
                    <img class="nav-image notif-img" src="res/img/bell-ico-t2.png"/>
                </a>

            </div>
        </#if>
        <a href="/profile" class="r-block-h-g profile-button">
            <#if princ?? && princ?has_content>
                <p class="profile-name dr-profile-name">
                    ${princ.getEmail()}
                </p>
                <div class="profile-img-container">
                    <img class="nav-image" src="res/img/inv-avatar.png"/>
                </div>
            <#else>
                <p class="profile-name dr-profile-name">
                    Вход
                </p>
            </#if>
        </a>
    </div>

</div>
<div id="dropdown-menu" class="dropdown-menu">
    <a href="/profile" class="dr-block dr-profile text-a">
        <#if princ?? && princ?has_content>
            <p class="profile-name dr-profile-name">
                ${princ.getEmail()}
            </p>
            <div class="profile-img-container">
                <img class="nav-image" src="res/img/inv-avatar.png"/>
            </div>
        <#else>
            <p class="profile-name dr-profile-name">
                Вход
            </p>
        </#if>
    </a>
    <#if princ?? && princ?has_content>
        <#if princ.haveAccessToChangeTags()>
            <a href="/mod/changeTags" class="dr-block text-a">Изменить теги</a>
        </#if>
    </#if>
    <a href="#2" class="dr-block text-a">Действие</a>
    <a href="#3" class="dr-block text-a">Действие</a>
</div>


<script src="js/navbar.js" href="js/navbar.js"></script>
</body>
</html>