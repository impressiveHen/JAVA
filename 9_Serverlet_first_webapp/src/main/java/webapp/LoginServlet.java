package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // http://localhost:8080/login.do?name=henry
        httpServletRequest.setAttribute("name", httpServletRequest.getParameter("name"));

        httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(httpServletRequest, httpServletResponse);

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute("credential", httpServletRequest.getParameter("credential"));

        httpServletRequest.getRequestDispatcher("/WEB-INF/views/user.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}
