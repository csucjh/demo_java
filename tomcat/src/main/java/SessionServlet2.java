import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet2 extends HttpServlet {

    /**
     * 没有使用request.getSession(true)，不会创建session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SessionServlet2 -- service");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>SessionServlet2</title></head>");
        out.println("<body>");
        String value = request.getParameter("value");
        out.println("<br>the current value is " + value);
        out.println("<br><hr>");
        out.println("</body>");
        out.println("</html>");
    }
}
