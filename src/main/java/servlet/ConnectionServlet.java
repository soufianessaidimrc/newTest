package servlet;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

import dao.TodoListManager;
import dao.UserdbManager;
import entity.Task;

public class ConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserdbManager UserdbManager;
    private TodoListManager todoListManager;

    @Resource(name = "jdbc/toDoDb")
    private DataSource dataSource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean exist = false;
        try{
            exist = UserdbManager.login(email, password);
        } catch (Exception e){
            request.setAttribute("connectStatus", "Connexion to the database failed");
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
        if(exist){
            System.out.println("true");
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            request.setAttribute("connectStatus", "Connexion Success");
            List<Task> taskList = todoListManager.getAll();
            Boolean isAdmin = UserdbManager.userIsAdmin(email);
            request.setAttribute("TASKS_LIST", taskList);
            session.setAttribute("isAdmin",isAdmin);
            Cookie cookie = new Cookie("email", email);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/todolist_menu.jsp").forward(request, response);
        } else{
            System.out.println("false");
            request.setAttribute("connectStatus", "Password or email are wrong");
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean cookie_exist = false;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("email")){
                    HttpSession session = request.getSession();
                    session.setAttribute("email", cookie.getValue());
                    cookie_exist = true;
                }
            }
        }
        if(cookie_exist){
            List<Task> taskList = todoListManager.getAll();
            request.setAttribute("TASKS_LIST", taskList);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/todolist_menu.jsp").forward(request, response);
        } else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        UserdbManager = new UserdbManager(dataSource);
        todoListManager = new TodoListManager(dataSource);
    }
}
