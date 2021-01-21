package servlet;

import dao.TodoListManager;
import entity.Task;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodolistServlet")
public class TodolistServlet extends HttpServlet {

    @Resource(name = "jdbc/toDoDb")
    private DataSource dataSource;

    private TodoListManager todoListManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> taskList = todoListManager.getAll();
        request.setAttribute("TASKS_LIST", taskList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/todolist_menu.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        todoListManager = new TodoListManager(dataSource);
    }
}