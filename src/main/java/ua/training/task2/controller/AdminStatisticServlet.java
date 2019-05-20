package ua.training.task2.controller;

import ua.training.task2.model.dao.JdbcDao;
import ua.training.task2.model.dao.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminStatisticServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        int taxId;
        TaxDeclaration declare = new TaxDeclarationService();
        TaxPayer payer = new TaxPayer();
        JdbcDao jdbc = TaxJdbcDao.getInstance();

        taxId = Integer.parseInt(httpServletRequest.getParameter("ti"));
        jdbc.getUserDataFromDb(payer, taxId);

}}
