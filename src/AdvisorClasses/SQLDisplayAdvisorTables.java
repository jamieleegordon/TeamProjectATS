package AdvisorClasses;

import jdk.jfr.Percentage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * @author Jamie-Lee
 *
 * This class is used to read the data from the MySQL database regarding the advisor
 * such as their own blank stock and sales and will display the fetched data inside the tables
 * within the Travel Advisor dashboard.
 */
public class SQLDisplayAdvisorTables {

    public SQLDisplayAdvisorTables() {}

    /**
     * method used to fetch the data and display the blanks of the advisor in the appropriate table.
     */
    public void DisplayAdvisorBlankTable(JTable blankTable, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            String d = "Date";

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, " + d + "  FROM BlankStock WHERE AdvisorID = " + staffID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the blank table
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type","Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = result.getString("BlankType");
                String btBlankStatus = result.getString("Status");
                String Date = String.valueOf(result.getDate("Date"));

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, Date};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * method used to fetch the data and display the sales of the advisor in the appropriate table.
     */
    public void DisplaySalesTable(JTable table, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + ", Date, Status FROM Sales Where AdvisorID =" + staffID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * method used to search for a particular sale in the sales table.
     */
    public void searchSalesTable(JTable table, String staffID, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND ID = " + saleID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt("ID"));
                String BlankID = Integer.toString(result.getInt("BlankStockID"));
                String CustomerID = Integer.toString(result.getInt("CustomerID"));
                String Commission = Float.toString(result.getFloat("CommisionRate")) + "%";
                String Payment = result.getString("PaymentType");
                String SaleType = result.getString("SaleType");
                String exchangeRate =  Float.toString(result.getFloat("ExchangeRate"));
                String localCurrency = Float.toString(result.getFloat("LocalCurrency"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplaySalesTable(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * method used to set the status of a ticket to 'Refunded'.
     */
    public void refundTicket(JTable table, String staffId, String saleID) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            // user is deleted, as their id is passed through
            stm.executeUpdate("UPDATE Sales SET Status = " + "'Refunded'" + " WHERE AdvisorID = " + staffId + " AND ID = " + saleID);

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "Ticket has been successfully refunded, " +
                    "customer has been notified and will receive their money back within 5 working days");
            ClearTable(table); // clears the table
            DisplaySalesTable(table, staffId); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * method used to fetch the data of a particular blank.
     */
    public void searchBlank(JTable table, String staffID, String blank) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            String d = "Date";

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, " + d +
                    " FROM BlankStock WHERE ID = " + blank + " AND AdvisorID = " + staffID);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = result.getString("BlankType");
                String btBlankStatus = result.getString("Status");
                String Date = String.valueOf(result.getDate("Date"));

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, Date};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayAdvisorBlankTable(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Blank does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * method used to make a blank 'void'.
     */
    public void voidBlank(JTable table, String staffId, String blankID) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            // user is deleted, as their id is passed through
            stm.executeUpdate("UPDATE BlankStock SET Status = " + "'Void'" + " WHERE AdvisorID = " + staffId + " AND ID = " + blankID);

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "Blank has been successfully made void");
            ClearTable(table); // clears the table
            DisplayAdvisorBlankTable(table, staffId); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
}
