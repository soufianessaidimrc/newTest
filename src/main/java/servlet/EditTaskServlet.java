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
import java.time.LocalDate;

@WebServlet(name = "EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
    @Resource(name = "jdbc/toDoDb")
    private DataSource dataSource;

    private TodoListManager todoListManager;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("taskID"));
        System.out.println("Je suisd ans la joie : " +id);
        String newComment =request.getParameter("comment");
        System.out.println("Je suisd ans la joie : " +newComment);
        Task t = todoListManager.getTaskById(id);
        if(!t.getComment().equals(newComment)){
            t.setComment(newComment);
            t.setEditedAt(LocalDate.now());
            todoListManager.updateTask(t);
        }
        else {
            System.out.println("Both Strings are identical");
        }

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
