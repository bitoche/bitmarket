<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, choosed-theme=light">
    <title>Главная</title>
</head>

<link rel="stylesheet" href="/css/elements.css"/>
<link rel="stylesheet" href="/css/navbar.css">
<link type="image/x-icon" href="/static/favicon.ico" rel="shortcut icon">
<style>
    body div{
        margin: 0;
        font-family: 'Montserrat';
    }
</style>
<body>
<div class="navbar">
    <a class="r-block-h-g text-a nav-logo" href="/">
        <img style="width: 7em" src="BITOCHE-title.png"/>
    </a>
    <div class="nav-center-actions">
        <#if princ?? && princ?has_content>
            <#if princ.haveAccessToChangeTags()>
                <a href="/mod/"
                   class="r-block-h-g text-a"
                    <#if entryLink?? && entryLink?has_content && entryLink=="modPanel">
                        style="background-color: rgba(0,0,0,0.1);
                           box-shadow: inset 0 0 3px 3px rgba(0, 0, 0, 0.2)"
                    </#if>
                >Панель модератора</a>
            </#if>
        </#if>
        <a href="#2" class="r-block-h-g text-a">Действие</a>
        <a href="#3" class="r-block-h-g text-a">Действие</a>
    </div>
    <div class="r-block-h-g hamburger-menu" id="hamburger-menu">
        <div class="profile-img-container hamb-btn">
            <img loading="eager" class="profile-img-container hamb-btn" src="/res/img/hamb-ico.png" alt="Menu">
        </div>
    </div>
    <div class="user-act-div">
        <#if princ?? && princ?has_content>
            <div>
                <a href="#notif" class="profile-img-container r-block-h-g sm-padding">
                    <img loading="eager" class="nav-image notif-img" src="/res/img/bell-ico-t2.png"/>
                </a>

            </div>
        </#if>
        <a href="/profile" class="r-block-h-g profile-button text-a"
            <#if entryLink?? && entryLink?has_content && entryLink=="profile">
                style="background-color: rgba(0,0,0,0.1);
                box-shadow: inset 0 0 3px 3px rgba(0, 0, 0, 0.2)"
            </#if>>
            <#if princ?? && princ?has_content>
                <p class="profile-name">
                    ${princ.getEmail()}
                </p>
                <div class="profile-img-container">
                    <img loading="eager" class="nav-image" src="/res/img/inv-avatar.png"/>
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
                <img loading="lazy" class="nav-image" src="/res/img/inv-avatar.png"/>
            </div>
        <#else>
            <p class="profile-name dr-profile-name">
                Вход
            </p>
        </#if>
    </a>
    <#if princ?? && princ?has_content>
        <#if princ.haveAccessToChangeTags()>
            <a href="/mod/"
               class="dr-block text-a"
                    <#if entryLink?? && entryLink?has_content && entryLink=="modPanel">
                        style="background-color: rgba(0,0,0,0.3);
                               box-shadow: inset 0 0 3px 3px rgba(0, 0, 0, 0.2)"
                    </#if>
            >Панель модератора</a>
        </#if>
    </#if>
    <a href="#2" class="dr-block text-a">Действие</a>
    <a href="#3" class="dr-block text-a">Действие</a>
</div>


<script src="/js/navbar.js" href="/js/navbar.js"></script>
</body>
</html>