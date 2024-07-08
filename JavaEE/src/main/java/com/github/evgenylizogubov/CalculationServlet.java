package com.github.evgenylizogubov;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalcServlet", urlPatterns = "/calculate")
public class CalculationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        
        int firstNumber = Integer.parseInt(req.getParameter("a"));
        int secondNumber = Integer.parseInt(req.getParameter("b"));
        String operation = req.getParameter("o");
        
        String result;
        
        switch (operation) {
            case "sum" -> result = String.format("%d + %d = %d", firstNumber, secondNumber, firstNumber + secondNumber);
            case "sub" -> result = String.format("%d - %d = %d", firstNumber, secondNumber, firstNumber - secondNumber);
            case "mul" -> result = String.format("%d * %d = %d", firstNumber, secondNumber, firstNumber * secondNumber);
            case "div" ->
                    result = String.format("%d / %d = %d", firstNumber, secondNumber, (float) firstNumber / secondNumber);
            default -> result = "Incorrect operation (\"sum\"/\"sub\"/\"mul\"/\"div\" needs)";
        }
        
        out.println("<html><body><h1>" + result + "</h1></body></html>");
        out.close();
    }
}
