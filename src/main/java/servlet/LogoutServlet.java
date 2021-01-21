package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("email", session.getAttribute("email"));
        session.removeAttribute("email");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("email", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
