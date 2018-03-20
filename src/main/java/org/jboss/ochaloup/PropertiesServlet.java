package org.jboss.ochaloup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="PropertiesServlet", urlPatterns={"/properties"})
public class PropertiesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("EAP QE");

        out.print("<h1>System properties</h1>");
        for(String key : System.getProperties().stringPropertyNames()) {
          String value = System.getProperties().getProperty(key);
          out.printf("%s => %s<br/>", key, value );
        }

        out.print("<h1>Env properties</h1>");
        for(Entry<String, String> entry : System.getenv().entrySet()) {
            out.printf("%s => %s<br/>", entry.getKey(), entry.getValue());
        }
    }
}
