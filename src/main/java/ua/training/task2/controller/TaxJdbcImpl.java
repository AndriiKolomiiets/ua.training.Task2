package ua.training.task2.controller;

import ua.training.task2.model.pojo.TaxPayer;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class TaxJdbcImpl implements TaxJdbc{
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("message",
            new Locale("en", "GB"));

    public Connection connectToDb() {
        connection = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(
                    resourceBundle.getString("jdbc.db.connection"),
                    resourceBundle.getString("jdbc.db.user"),
                    resourceBundle.getString("jdbc.db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public TaxPayer getUserDataFromDb(TaxPayer taxPayer, int taxId) {
        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT taxid, tax_id, fname, lname, tcategory," +
                    "regular_job, extra_job, annual_bonus, benefits, financial_assistance, foreign_transaction," +
                    "property_sells, gifted_property, gifted_money, declaration_date, creation_date " +
                    "FROM user_info, declaration WHERE taxid=" + taxId + " AND tax_id=" + taxId);

            while (rs.next()) {
                taxPayer.setTaxId(rs.getInt("taxid"));
                taxPayer.setFirstName(rs.getString("fname"));
                taxPayer.setLastName(rs.getString("lname"));
                taxPayer.setTaxCategory(rs.getString("tcategory"));
                taxPayer.setPrimeJobIncomeAmount(rs.getInt("regular_job"));
                taxPayer.setExtraJobIncomeAmount(rs.getInt("extra_job"));
                taxPayer.setAnnualBonusAmount(rs.getInt("annual_bonus"));
                taxPayer.setBenefitsAmount(rs.getInt("benefits"));
                taxPayer.setFinancialAssistanceAmount(rs.getInt("financial_assistance"));
                taxPayer.setForeignMoneyTransactionsAmount(rs.getInt("foreign_transaction"));
                taxPayer.setPropertySellsAmount(rs.getInt("property_sells"));
                taxPayer.setPropertyGotAsGiftAmount(rs.getInt("gifted_property"));
                taxPayer.setMoneyGotAsGiftAmount(rs.getInt("gifted_money"));
                taxPayer.setDeclarationDate(rs.getInt("declaration_date"));
                return taxPayer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return taxPayer;
    }

    public void buildUserInfoIntoDB(int taxId, String firstName, String lastName, String taxCategory,
                                    int regularJob, int extraJob, int annualBonus, int benefits,
                                    int financialAssistance, int foreignTransaction,
                                    int propertySells, int giftedProperty, int giftedMoney, int declarationDate) {
        connection = connectToDb();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement(
                    resourceBundle.getString("jdbc.db.insertDeclarationIntoDB"));
            preparedStatement.setInt(1, taxId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, taxCategory);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

            preparedStatement = connection.prepareStatement(
                    resourceBundle.getString("jdbc.db.insertUserIntoDB"));
            preparedStatement.setInt(1, taxId);
            preparedStatement.setInt(2, regularJob);
            preparedStatement.setInt(3, extraJob);
            preparedStatement.setInt(4, annualBonus);
            preparedStatement.setInt(5, benefits);
            preparedStatement.setInt(6, financialAssistance);
            preparedStatement.setInt(7, foreignTransaction);
            preparedStatement.setInt(8, propertySells);
            preparedStatement.setInt(9, giftedProperty);
            preparedStatement.setInt(10, giftedMoney);
            preparedStatement.setInt(11, declarationDate);
            preparedStatement.setDate(12, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignored) {
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
