<%--
  Created by IntelliJ IDEA.
  User: Christian Mada-Mbari
  Date: 10/22/2020
  Time: 11:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%--<link rel="stylesheet" href="../css/todolist_card.css" />--%>
<head>

    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>
    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <link href="https://cdn.materialdesignicons.com/5.4.55/css/materialdesignicons.min.css" media="all" rel="stylesheet"
          type="text/css"/>


    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>

    <link type="text/css" rel="stylesheet" href="../css/todolist_card.css">
    <script src="../../js/todolist.js" type="text/javascript"></script>
    <title>Todolist | Tasks</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css">

</head>
<% List<Task> theStudents = (List<Task>) request.getAttribute("TASKS_LIST"); %>
<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<body>
<!-- ${TASKS_LIST}-->
<nav class="navbar">
    <a class="nav_todolist" href="#">Todolist</a>
    <ul class="nav">
        <li class="nav_item"><a class="nav_link" href="/LogoutServlet"><%=session.getAttribute("email")%> Log out</a>
        </li>
    </ul>
</nav>

<div class="wrapper">
    <div class="sidebar">
        <h2></h2>
        <ul>
            <li><a href="/TodolistServlet"><i class="fas fa-tasks"></i>Tasks</a></li>
            <li><a href="/Login"><i class="fas fa-users"></i>Users</a></li>
        </ul>
    </div>
    <div class="page-content page-container" id="page-content">
        <div class="padding">
            <div class="row container d-flex justify-content-center">
                <div class="col-md-auto">
                    <div class="card px-3">
                        <div class="card-body">
                            <h4 class="card-title">Awesome Todo list</h4>
                            <c:choose>

                                <c:when test="${sessionScope.isAdmin == true}">
                                    <form method="post" action="AddTaskServlet">
                                        <div class="add-items d-flex"><input name="comment" type="text"
                                                                             class="form-control todo-list-input"
                                                                             placeholder="What do you need to do today?">
                                            <button type="submit" class="btn btn-primary font-weight-bold">Add</button>
                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <div class="add-items d-flex"><input disabled name="comment" type="text"
                                                                         class="form-control todo-list-input"
                                                                         placeholder="What do you need to do today?">
                                        <button disabled type="submit" class="btn btn-primary font-weight-bold">Add
                                        </button>
                                    </div>

                                </c:otherwise>
                            </c:choose>

                            <div class="list-wrapper">
                                <ul class="d-flex flex-column-reverse todo-list">
                                    <c:forEach var="tempTask" items="${TASKS_LIST }">
                                    <c:choose>
                                    <c:when test="${tempTask.status == false}">
                                    <li>

                                        <div class="form-check"><label class="form-check-label">

                                            <div>
                                                <form method="post" action="/EditTaskStatusServlet">
                                                    <input type="hidden" name="checked" value="${tempTask.id}">

                                                    <input class="checkbox" type="checkbox"
                                                           onchange="this.form.submit()">
                                                        ${tempTask.comment} <i class="input-helper"></i>
                                        </label>
                                            </form>

                                        </div>
                                        <div>

                                        </div>


                            </div>

                            <c:if test="${sessionScope.isAdmin == true}">

                                <div style="margin-top: 7px;  margin-left: auto " class="edit">

                                    <form method="post" action="EditTaskServlet">
                                        <input type="hidden" name="taskID" value="${tempTask.id}">
                                        <input type="text" placeholder="Update task" onchange="this.form.submit()"
                                               name="comment"> <i class="input-helper"></i>

                                    </form>
                                </div>

                            </c:if>

                            <c:choose>

                                <c:when test="${sessionScope.isAdmin == true}">

                                    <div class="remove">
                                        <form method="post" action="DeleteTaskServlet">
                                            <input type="hidden" name="delete" value="${tempTask.id}"
                                                   class="remove">
                                            <button style="border: none" type="submit"
                                                    class="remove btn-success"><i
                                                    class="remove far fa-times-circle"></i></button>
                                        </form>
                                    </div>

                                </c:when>
                                <c:otherwise>
                                    <i class="remove far fa-times-circle"></i>
                                </c:otherwise>
                            </c:choose>


                            </li>
                            </c:when>
                            <c:otherwise>
                                <li class="completed">


                                    <div class="form-check"><label class="form-check-label">
                                        <form method="post" action="/EditTaskStatusServlet">
                                            <input type="hidden" name="checked" value="${tempTask.id}">

                                            <input class="checkbox" type="checkbox" checked=""
                                                   onclick="this.form.submit()">

                                                ${tempTask.comment} <i class="input-helper"></i>
                                    </label>
                                        </form>
                                    </div>

                                    <i class="remove far fa-times-circle"></i>
                                </li>
                            </c:otherwise>
                            </c:choose>

                            </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>