package org.jboss.ochaloup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;


@WebServlet(name="UserTransactionServlet", urlPatterns={"/txn"})
public class UserTransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            InitialContext ic = new InitialContext();
            UserTransaction utx = (UserTransaction) ic.lookup("java:comp/UserTransaction");
            out.printf("<p>User transaction found at 'java:comp/UserTransaction': %s, status: %s</p>", utx, utx != null ? utx.getStatus() : "UNKNOWN");
            utx.begin();
            utx.commit();

            UserTransaction utx2 = (UserTransaction) ic.lookup("java:jboss/UserTransaction");
            out.printf("<p>User transaction found at 'java:jboss/UserTransaction': %s, status: %s</p>", utx2, utx != null ? utx.getStatus() : "UNKNOWN");
            utx2.begin();
            utx2.commit();

        } catch (Throwable t) {
            out.println("<pre>");
            t.printStackTrace(out);
            out.println("</pre>");
        }
    }
}
