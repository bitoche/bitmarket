<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Профиль - BitMarket</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/USERPAGE.css">
</head>
<body>
<#include "schemes/navbar.ftl">
<div class="page-body">
    <div class="body-content">
        <h3 class="page-h3">Ваш профиль</h3>
        <div class="data-container">
            <#if !princ.isEnabled()><h4>Вам необходимо подтвердить почту. Письмо уже отправлено на ваш почтовый ящик</h4></#if>
            <div class="user-data" >
                <div class="user-data-line">
                    <#--фио-->
                    <div class="user-data-line-top">
                        Ваше имя
                    </div>

                    ${princ.getFullName()}
                </div>
                <div class="user-data-line">
                    <#--username-->
                    <div class="user-data-line-top">
                        Ваш логин для входа
                    </div>

                    ${princ.getEmail()}
                </div>
                <div class="user-data-line">
                    <#--decryptedpass-->
                    <div class="user-data-line-top">
                        Ваш пароль для входа
                    </div>

                    <a href="#getPass" onclick="getUserPass()">Посмотреть</a>
                </div>
            </div>
        </div>

        <div class="full-width-links">
            <a class="text-a" href="#">Изменить данные</a>
        </div>
        <div class="full-width-links">
            <a class="text-a" style="color: red;" href="/logout">Выйти из аккаунта</a>
        </div>
    </div>
</div>
</body>
<script>
function getUserPass(){
    /*todo сделать функцию вывода пароля*/
    alert("Пока недоступно! Загляние позже.")
}
</script>
</html>