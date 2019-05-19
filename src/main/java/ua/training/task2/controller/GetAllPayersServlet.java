package ua.training.task2.controller;

import ua.training.task2.model.JdbcDao;
import ua.training.task2.model.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.AllTaxPayersService;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetAllPayersServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
       /* long income;
        double taxes;*/
        Locale locale;
        String language;
/*        int taxId;
        TaxDeclaration declare = new TaxDeclarationService();
        TaxPayer payer = new TaxPayer();
        JdbcDao jdbc = TaxJdbcDao.getInstance();*/
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
