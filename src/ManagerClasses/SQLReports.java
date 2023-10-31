package ManagerClasses;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Jamie-Lee & Abdul Rehman
 *
 * This class is used to fetch data from the MySQL regarding sales and display them in the necessary reports forms and tables.
 */
public class SQLReports {

    private String individualInterlineAdvisor;
    private String individualDomesticAdvisor;
    public SQLReports() {}

    public String getIndividualDomesticAdvisor() {
        return individualDomesticAdvisor;
    }

    public String getIndividualInterlineAdvisor() {
        return individualInterlineAdvisor;
    }

    /**
     * The text from the text boxes are passed through as a parameter and then used to insert the new data into the database.
     */


    public void recordSale(String saleID, String blankID, String customerID,
                                 String advisorID, String paymentDetails, String saleType,
                           String country, String localTax, String otherTax, String status, String exchangeRate,
                           String commissionRate, String localCurr, String date) {

        String email = null;
        String lateMessage = """
                You have 30 Days to pay the Outstanding Amount.
                """;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            String insertStm = ("'" + saleID + "'") + ", " + ("'" + blankID + "'") + ", " + ("'" + customerID + "'") + ", "
                + ("NULL") + ", " + ("'" + advisorID + "'") + ", " + ("'" + paymentDetails + "'") + ", " + ("'" + saleType + "'")
                    + ", " + ("'" + country + "'") + ", " + ("NULL")+ ", " + ("NULL") + ", " + ("'" + status + "'")
                    + ", " + ("'" + date + "'") + ", " + ("'" + localTax + "'") + ", " + ("'" + otherTax + "'") + ", "
                    + ("'" + exchangeRate + "'") + ", " + ("'" + commissionRate + "'")
                    + ", " + ("'" + localCurr + "'");
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // data to be inserted into the database

            // insert statement is run
            stm.executeUpdate("INSERT INTO Sales VALUES ( " + insertStm + ")");

            // message box, showing that the customer has been successfully added to the database
            JOptionPane.showMessageDialog(null, "Ticket sale has been successfully recorded");
            // close the connection to db
            con.close();

            // connecting to the database server
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement satement = connection.createStatement();
            ResultSet rs = satement.executeQuery("SELECT Email FROM Customer WHERE ID = " +customerID);

            while(rs.next()) {
                email = rs.getString("Email");
            }

            connection.close();

            // Load mail properties from config file
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);

            // Get mail credentials from config file
            final String username = props.getProperty("mail.username");
            final String password = props.getProperty("mail.password");
            final String fromAddress = props.getProperty("mail.fromAddress");

            // create the SMTP client
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(p, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // create the message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            //email subject
            message.setSubject("Receipt of Ticket Purchase");

            //checks if its pay later or paid. different message respectively
            if(status.compareTo("can pay later") == 0){
                //email message
                        message.setText("""
                        Hi """ + customerID +
                        """
                        \nThank you for your purchase.
                        Here is your Receipt: 
                        Receipt ID: """ + saleID + """
                        \nBlank ID: """ + blankID + """
                        \nCustomer ID: """ + customerID + """
                        \nAdvisor ID: """ + advisorID + """
                        \nPayment Type: """ + paymentDetails + """
                        \nSales Type: """ + saleType + """
                        \nCountry: """ + country + """
                        \nLocal Tax: """ + localTax + """
                        \nOther Tax: """ + otherTax + """
                        \nTotal: """ + localCurr + """
                        \nDate: """ + date + '\n' +'\n'+
                        lateMessage +
                        """
                                    
                        \nKind Regards
                        AirVia ATS
                        """);
            }
            else {
                //email message
                message.setText("""
                        Hi """ + customerID +
                        """
                        \nThank you for your purchase.
                        Here is your Receipt: 
                        Receipt ID: """ + saleID + """
                        \nBlank ID: """ + blankID + """
                        \nCustomer ID: """ + customerID + """
                        \nAdvisor ID: """ + advisorID + """
                        \nPayment Type: """ + paymentDetails + """
                        \nSales Type: """ + saleType + """
                        \nCountry: """ + country + """
                        \nLocal Tax: """ + localTax + """
                        \nOther Tax: """ + otherTax + """
                        \nTotal: """ + localCurr + """
                        \nDate: """ + date + """
                                            
                        \nKind Regards
                        AirVia ATS
                        """);
            }

            // send the message
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Receipt has been sent to Customer");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a particular advisor interline report to be generated.
     */
    public void searchAdvisorInterline(String user, JFrame frame) {
        individualInterlineAdvisor = user;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor' AND ID = " + user);

            //inserts all the data in the respective columns
            if(result.next()){
                new IndividualInterline(user).setVisible(true);
                frame.dispose();
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Searches for a particular advisor domestic report to be generated.
     */
    public void searchAdvisorDomestic(String user, JFrame frame) {
        individualDomesticAdvisor = user;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor' AND ID = " + user);

            //inserts all the data in the respective columns
            if(result.next()){
                new IndividualDomestic(user).setVisible(true);
                frame.dispose();
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public ResultSet ResultSetForIIR(String staffID){
        ResultSet result = null;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline'");

            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Displays the individual interline report in a table.
     */
    public void DisplayIndividualInterline(JTable table, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public ResultSet ResultSetForIDR(String staffID){
        ResultSet result = null;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic'");

            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Displays the individual domestic report in a table.
     */
    public void DisplayIndividualDomestic(JTable table, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Returns the global domestic report in a Result Set.
     */
    public ResultSet ResultSetForGDR(){
        ResultSet result = null;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Domestic'");

            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;

    }
    /**
     * Displays the global domestic report in a table.
     */
    public void DisplayGlobalDomestic(JTable table){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Domestic'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Returns the global interline report in a Result Set.
     */
    public ResultSet ResultSetForGIR(){
        ResultSet result = null;
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Interline'");

            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Displays the global interline report in a table.
     */
    public void DisplayGlobalInterline(JTable table){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales WHERE SaleType = 'Interline'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Searches for and displays a particular sale in the individual interline report.
     */
    public void searchIndividualInterline(JTable table, String staffID, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline' AND ID = " + saleID);

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayIndividualInterline(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Searches for and displays a particular sale in the individual domestic report.
     */
    public void searchIndividualDomestic(JTable table, String staffID, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic' AND ID = " + saleID);

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayIndividualDomestic(table, staffID); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Searches for and displays a particular sale in the global domestic report.
     */
    public void searchGlobalDomestic(JTable table, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Domestic' AND ID = " + saleID);

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayGlobalDomestic(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Searches for and displays a particular sale in the global interline report.
     */
    public void searchGlobalInterline(JTable table, String saleID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Interline' AND ID = " + saleID);

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayGlobalInterline(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Filters the table by a particular date.
     */
    public void filterIndividInterlineByDate(JTable table, String staffID, String startDate, String endDate){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            // SELECT * FROM `Sales` WHERE date BETWEEN '2023-01-01' AND '2023-12-31';
            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Interline' AND Date BETWEEN " +
                    "'" + startDate + "'" + "AND" + "'" + endDate + "'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Searches table for specific ID and updates the exchange rate.
     */
    public void searchSaleIndivInterline(String id, String newRate, JTable table) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM Sales WHERE ID = " + id + " AND SaleType = 'Interline'");

            if(result.next()){
                changeExchangeRate(id, newRate, table);
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Searches table for specific ID and updates the exchange rate.
     */
    public void changeExchangeRate(String id, String newRate, JTable table) {
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            String ex = "ExchangeRate";

            Statement stm = con.createStatement();
            // user is deleted, as their id is passed through
            stm.executeUpdate("UPDATE Sales SET " + ex + " = " + newRate + " WHERE ID = " + id);

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "Exchange rate has been updated to " + newRate);
            ClearTable(table); // clears the table
            DisplayIndividualInterline(table, id); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Filters the table by a particular date.
     */
    public void filterIndividDomesticByDate(JTable table, String staffID, String startDate, String endDate){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            // SELECT * FROM `Sales` WHERE date BETWEEN '2023-01-01' AND '2023-12-31';
            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where AdvisorID =" + staffID + " AND SaleType = 'Domestic' AND Date BETWEEN " +
                    "'" + startDate + "'" + "AND" + "'" + endDate + "'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Searches table for specific ID and updates the exchange rate.
     */
    public void searchSaleIndivDomestic(String id, String newRate, JTable table) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM Sales WHERE ID = " + id + " AND SaleType = 'Domestic'");

            if(result.next()){
                changeExchangeRate(id, newRate, table);
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Filters the table by a particular date.
     */
    public void filterGlobalDomesticByDate(JTable table, String startDate, String endDate){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            // SELECT * FROM `Sales` WHERE date BETWEEN '2023-01-01' AND '2023-12-31';
            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Domestic' AND Date BETWEEN " +
                    "'" + startDate + "'" + "AND" + "'" + endDate + "'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Searches table for specific ID and updates the exchange rate.
     */
    public void searchSaleGlobalDomestic(String id, String newRate, JTable table) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM Sales WHERE SaleType = 'Domestic' AND ID = " + id);

            if(result.next()){
                changeExchangeRate(id, newRate, table);
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * Filters the table by a particular date.
     */
    public void filterGlobalInterlineByDate(JTable table, String startDate, String endDate){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();

            String com = "CommisionRate";
            String ex = "ExchangeRate";
            String loc = "LocalCurrency";
            String loTax = "LocalTax";
            String oTax = "OtherTax";

            // SELECT * FROM `Sales` WHERE date BETWEEN '2023-01-01' AND '2023-12-31';
            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, " + com + ", PaymentType, SaleType" +
                    ", " + ex + ", " + loc + "," + loTax + "," + oTax + ", Date, Status FROM Sales Where SaleType = 'Interline' AND Date BETWEEN " +
                    "'" + startDate + "'" + "AND" + "'" + endDate + "'");

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
                String localTax = Float.toString(result.getFloat("LocalTax"));
                String otherTax = Float.toString(result.getFloat("OtherTax"));
                String Date = String.valueOf(result.getDate("Date"));
                String Status = result.getString("Status");

                String[] row = {ID, BlankID, CustomerID, Commission, Payment, SaleType, exchangeRate,localCurrency, localTax, otherTax, Date, Status};
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
     * Searches table for specific ID and updates the exchange rate.
     */
    public void searchSaleGlobalInterline(String id, String newRate, JTable table) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM Sales WHERE SaleType = 'Interline' AND ID = " + id);

            if(result.next()){
                changeExchangeRate(id, newRate, table);
            } else { // user has not been found
                JOptionPane.showMessageDialog(null, "Sale does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // Clears the table of data
    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

}
