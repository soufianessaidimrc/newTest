package servlet;

import dao.TodoListManager;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {

    @Resource(name = "jdbc/toDoDb")
    private DataSource dataSource;

    private TodoListManager todoListManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        todoListManager.deleteTask(id);
        response.sendRedirect("/TodolistServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        todoListManager = new TodoListManager(dataSource);
    }
}
