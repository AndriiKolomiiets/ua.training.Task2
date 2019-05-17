package ua.training.task2.model;

import ua.training.task2.model.pojo.*;
import ua.training.task2.model.pojo.income.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TaxJdbcDao implements JdbcDao {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static volatile TaxJdbcDao taxJdbc = null;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("message",
            new Locale("en", "GB"));

    private TaxJdbcDao() {
    }

    public static TaxJdbcDao getInstance() {
        if (taxJdbc == null) {
            synchronized (TaxJdbcDao.class) {
                if (taxJdbc == null) {
                    taxJdbc = new TaxJdbcDao();
                }
            }
        }
        return new TaxJdbcDao();
    }

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

    //todo: null check
    public TaxPayer getUserDataFromDb(TaxPayer taxPayer, int taxId) {
        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT taxid, tax_id, fname, lname, tcategory," +
                    "regular_job, extra_job, annual_bonus, benefits, financial_assistance, foreign_transaction," +
                    "property_sells, gifted_property, gifted_money, declaration_date, creation_date " +
                    "FROM user_info, declaration WHERE taxid=" + taxId + " AND tax_id=" + taxId);

            while (rs.next()) {
                taxPayer.getTaxIdentification().setTaxId(rs.getInt("taxid"));
                taxPayer.getUser().setFirstName(rs.getString("fname"));
                taxPayer.getUser().setLastName(rs.getString("lname"));
                taxPayer.getTaxIdentification().setTaxCategory(rs.getString("tcategory"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("regular_job"), "Regular job income"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("extra_job"), "Extra job income"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("annual_bonus"), "Annual bonus"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("benefits"), "Benefits"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("financial_assistance"), "Financial assistance"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("foreign_transaction"), "Foreign transaction"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("property_sells"), "Property sales"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("gifted_property"), "Property got as a gift"));
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("gifted_money"), "Money got as a gift"));
                return taxPayer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return taxPayer;
    }

    public List<Integer> getAllTaxIdFromDB() {
        List<Integer> taxIds = new ArrayList<>();
        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT taxid FROM user_info");
            while (rs.next()) {
                taxIds.add(rs.getInt("taxid"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return taxIds;
    }

    public List<TaxPayer> getAllTaxPayersFromDB() {
        List<TaxPayer> payers = new ArrayList<>();
        User user = new UserImpl();
        TaxIdentification taxIdentification = new TaxIdentificationImpl();

        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT fname, lname, taxid, tcategory FROM user_info, declaration");
            getTaxPayerAndAddToList(payers, user, taxIdentification, rs);

        } catch (
                SQLException e)

        {
            System.out.println(e.getMessage());
        }
        return payers;
    }

    public List<TaxPayer> getTaxPayersByCondition(String field, int min, int max) {
        List<TaxPayer> payers = new ArrayList<>();
        User user = new UserImpl();
        TaxIdentification taxIdentification = new TaxIdentificationImpl();

        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT fname, lname, taxid, tcategory " +
                    "FROM user_info, declaration WHERE " + field + " BETWEEN " + min + " AND " + max);
            getTaxPayerAndAddToList(payers, user, taxIdentification, rs);

        } catch (
                SQLException e)

        {
            System.out.println(e.getMessage());
        }
        return payers;
    }

    private void getTaxPayerAndAddToList(List<TaxPayer> payers, User user, TaxIdentification taxIdentification, ResultSet rs) throws SQLException {
        while (rs.next()) {
            user.setFirstName(rs.getString("fname"));
            user.setLastName(rs.getString("lname"));
            taxIdentification.setTaxId(rs.getInt("taxid"));
            taxIdentification.setTaxCategory(rs.getString("tcategory"));

            TaxPayer taxPayer = new TaxPayer(user, taxIdentification);
            payers.add(taxPayer);
        }
    }

    public TaxPayer getPayerWithBiggestRegularIncome() {
        User user = new UserImpl();
        TaxPayer taxPayer = new TaxPayer();

        try {
            connection = connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT fname, lname, regular_job FROM user_info, declaration WHERE MAX(regular_job)");
            while (rs.next()) {
                user.setFirstName(rs.getString("fname"));
                user.setLastName(rs.getString("lname"));
                taxPayer.setUser(user);
                taxPayer.addIncomeToList(new IncomeImpl(rs.getInt("regular_job"), "Regular job income"));
            }

        } catch (
                SQLException e)

        {
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
//            preparedStatement.setInt(11, declarationDate);
            preparedStatement.setDate(11, new java.sql.Date(System.currentTimeMillis()));
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
