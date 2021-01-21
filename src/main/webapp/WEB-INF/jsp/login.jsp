<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <link type="text/css" rel="stylesheet" href="../css/login.css">
    <title>Todolist | Login</title>
</head>
<body>
<div class="login-box">
    <form class="form-signin" method="post" action="login">
        <div class="textbox">
            <input type="email" id="input_email" class="input_email" name="email" placeholder="Email"
                   value="<c:out value="${email}"/> " required autofocus>
        </div>

        <div class="textbox">
            <input type="password" id="input_mdp" class="input_mdp" name="password" placeholder="Password" required>
        </div>
        <button class="btn" type="submit">Sign in</button>
    </form>
    <c:out value="${connectStatus}"/>
</div>
</body>
</html>
