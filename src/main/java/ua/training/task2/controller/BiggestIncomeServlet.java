package ua.training.task2.controller;

import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.AllTaxPayersService;
import ua.training.task2.model.service.BiggestRegularIncomeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BiggestIncomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        BiggestRegularIncomeService biggestIncomeService = new BiggestRegularIncomeService();
        TaxPayer taxPayer;
        taxPayer = biggestIncomeService.getPayer();

        httpServletRequest.setAttribute("payer", taxPayer);
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletRequest.getRequestDispatcher("/biggest_income_payer.jsp").forward(httpServletRequest, httpServletResponse);


    }
}
