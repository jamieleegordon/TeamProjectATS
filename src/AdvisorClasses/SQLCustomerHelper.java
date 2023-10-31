package AdvisorClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Jamie-Lee
 *
 * This class is used to manipulate data for the customer table in the MySQL database such as
 * registering a new customer, deleting them, and updating their details.
 */
public class SQLCustomerHelper {

    public SQLCustomerHelper() {}

    /**
     * DisplayCustomers() will display the data of the customers onto the table by retrieving
     * the data from the database
     */
    public void DisplayCustomers(JTable table){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Email, Type, DiscountPlan, Percentage FROM Customer");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the blank table
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
                String custID = result.getString("ID");
                String custFirstName = result.getString("FirstName");
                String custLastName = result.getString("LastName");
                String custEmail = result.getString("Email");
                String custType = result.getString("Type");
                String DiscountPlan = result.getString("DiscountPlan");
                String Percentage = Float.toString(result.getFloat("Percentage"));

                String[] row = {custID, custFirstName, custLastName, custEmail, custType, DiscountPlan, Percentage};
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
     * searchCustomer() will display the customer record of the customer that the advisor has searched for
     */
    public void searchCustomer(JTable table, String id) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_d", "CQYeV2J6");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Email, Type, DiscountPlan, Percentage FROM Customer WHERE ID = " + id);

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
                String custID = result.getString("ID");
                String custFirstName = result.getString("FirstName");
                String custLastName = result.getString("LastName");
                String custEmail = result.getString("Email");
                String custType = result.getString("Type");
                String DiscountPlan = result.getString("DiscountPlan");
                String Percentage = Float.toString(result.getFloat("Percentage"));

                String[] row = {custID, custFirstName, custLastName, custEmail, custType, DiscountPlan, Percentage};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayCustomers(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Customer does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * changeType() will allow the advisor to change the customer type (regular or valued)
     */
    public void changeType(JTable table, String id, String type) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            // user is deleted, as their id is passed through
            stm.executeUpdate("UPDATE Customer SET Type = " + "'" + type + "'" + " WHERE ID = " + id);

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "Customer has been successfully changed to " + type);
            ClearTable(table); // clears the table
            DisplayCustomers(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * updateCustomer() will allow the user to update customer details.
     * The parameters are the text that is passed through from the text boxes and will be inserted
     * into the database in their appropriate columns, replacing the old details of the customer.
     */
    public void updateCustomer(JTable table, String id, String fName, String lName, String emailAd, String type) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE Customer SET FirstName = " + ("'" + fName + "',") + (" LastName = '" + lName + "',") +
                    ("Email = '" + emailAd + "',") + ("Type = '" + type + "' ") + "WHERE ID = " + id);

            // message box, showing that the customer's details have been successfully updated
            JOptionPane.showMessageDialog(null, "Customer details has been successfully updated");
            ClearTable(table); // clears the table
            DisplayCustomers(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * deleteCustomer() will be used to delete a particular existing customer.
     * The customer ID is passed through as a parameter to find the customer in
     * the database and delete their record.
     */
    public void deleteCustomer(JTable table, String id) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // Ticket type record is updated to delete the commission rate
            stm.executeUpdate("DELETE FROM Customer WHERE ID = " + id);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // message box, showing that the customer has been successfully deleted
            JOptionPane.showMessageDialog(null, "Customer has been successfully deleted");
            ClearTable(table); // clears the table
            DisplayCustomers(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * registerCustomer() will allow the user to register a customer alongside their details.
     * The parameters are the text that is passed through from the text boxes and will be inserted
     * into the database in their appropriate columns.
     */
    public void registerCustomer(String idText, String firstNameText, String lastNameText,
                              String emailText, String typeText) {
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            // data to be inserted into the database
            String insertStm = ("'" + idText + "'") + ", " + ("'" + firstNameText + "'") + ", " + ("'" + lastNameText + "'") + ", "
                    + ("'" + emailText + "'") + ", " + ("'" + typeText + "' ,") + " NULL, NULL, NULL";

            // insert statement is run
            stm.executeUpdate("INSERT INTO Customer VALUES ( " + insertStm + ")");

            // message box, showing that the customer has been successfully added to the database
            JOptionPane.showMessageDialog(null, "Customer has been successfully registered with AirVia");
            // close the connection to db
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setDiscount() will be used to set the discounts of customers
     * param - id is being used to search for the customer in the database and find their matching record.
     * param - disPlan is being used to set the type of discount plan (fixed/flexible).
     * param - perc is being used to set the percentage of the discount plan.
     */
    public void setDiscount(JTable table, String id, String disPlan, String perc) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE Customer SET DiscountPlan = " + "'" + disPlan + "', " + " Percentage = " + "'" + perc + "' WHERE ID = " + id);

            // message box, showing that the customer's details have been successfully updated
            JOptionPane.showMessageDialog(null, "Discount plan has been successfully added");
            ClearTable(table); // clears the table
            DisplayCustomers(table); // refreshes the table by re-displaying the data

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
