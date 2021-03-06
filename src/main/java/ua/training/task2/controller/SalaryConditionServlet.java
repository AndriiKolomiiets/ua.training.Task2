package ua.training.task2.controller;

import ua.training.task2.model.service.AllTaxPayersService;
import ua.training.task2.model.service.BiggestRegularIncomeService;
import ua.training.task2.model.service.TaxPayersByConditionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SalaryConditionServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Locale locale;
        String language;
        ResourceBundle resourceBundle;
        TaxPayersByConditionService taxPayersByConditionService = new TaxPayersByConditionService();
        List payersByConditionsList;
        locale = httpServletRequest.getLocale();
        language = locale.getLanguage();
        int min;
        int max;

//todo: change language choosing
        if (language.equals("uk")) {
            resourceBundle = ResourceBundle.getBundle("message_uk_UA",
                    new Locale("uk", "UA"));
        } else {
            resourceBundle = ResourceBundle.getBundle("message",
                    new Locale("en", "GB"));
        }

        if (httpServletRequest.getParameter("min") == null) {
            httpServletRequest.getRequestDispatcher("/admin.jsp").forward(httpServletRequest, httpServletResponse);
        }
        min = Integer.parseInt(httpServletRequest.getParameter("min"));
        max = Integer.parseInt(httpServletRequest.getParameter("max"));
        payersByConditionsList = taxPayersByConditionService.getTaxPayersByCondition(min, max);
        if (payersByConditionsList.size() == 0) {
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.println("<html>" +
                    "<head><title>" + resourceBundle.getString("check.header") + "</title></head>\n" +
                    "<br>" +
                    "<br>" +
                    "<body> <h2  align=\"center\">" + resourceBundle.getString("conditions.noelements") + " </h2>" +
                    "<div align=\"center\">" + "</body>" +
                    "</html>");
        } else {
            httpServletRequest.setAttribute("payersByConditionsList", payersByConditionsList);
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            httpServletRequest.getRequestDispatcher("/tax_payers_by_salary_conditions.jsp").forward(httpServletRequest, httpServletResponse);
        }

    }
}
