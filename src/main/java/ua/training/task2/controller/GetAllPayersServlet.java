package ua.training.task2.controller;

import ua.training.task2.model.service.AllTaxPayersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetAllPayersServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Locale locale;
        String language;
        ResourceBundle resourceBundle;
        AllTaxPayersService allTaxPayersService = new AllTaxPayersService();
        List allPayersList;
        /*taxId = Integer.parseInt(httpServletRequest.getParameter("ti"));
        jdbc.getUserDataFromDb(payer, taxId);
        income = declare.countEntireIncome(payer);
        taxes = declare.getTaxes(payer);
        locale = httpServletRequest.getLocale();
        language = locale.getLanguage();*/

        locale = httpServletRequest.getLocale();
        language = locale.getLanguage();


//todo: change language choosing
        if (language.equals("uk")) {
            resourceBundle = ResourceBundle.getBundle("message_uk_UA",
                    new Locale("uk", "UA"));
        } else {
            resourceBundle = ResourceBundle.getBundle("message",
                    new Locale("en", "GB"));
        }
        allPayersList = allTaxPayersService.getAllPayers();
        httpServletRequest.setAttribute("allPayersList", allPayersList);
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletRequest.getRequestDispatcher("/all_tax_payers.jsp").forward(httpServletRequest, httpServletResponse);


    }
}
