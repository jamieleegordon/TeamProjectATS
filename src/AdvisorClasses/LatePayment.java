package AdvisorClasses;

import java.sql.*;

/**
 * @author Jamie-Lee
 *
 * This class is used to create instances of late payment alerts for the Travel Advisor to see what customers are required
 * to pay within 30 days of the sale recording.
 * It will fetch the customer IDs of all customers with a sale status of ‘can pay later’ and used to print out a message box.
 */
public class LatePayment {

    String customers = "";

    public LatePayment() {}

    /**
     * Fetches the IDs of all customers with a late late payment and will append to a string 'customers'
     * which will be used to display a message box to the Travel Advisor
     */
    public void latePaymentAlert(int staffID){

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

            //inserts all the data in the respective columns
            while(result.next()){
                // if the status == 'can pay later', then the customer ID will be appended to the string 'customers'
                if (result.getString("Status").equals("can pay later")) {
                    customers += Integer.toString(result.getInt("CustomerID")) + ", ";
                }
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    /**
     * This method will remove the trailing ", " and return it back.
     */
    public String getCustomers() {
        String c = "";
        for (int i = 0; i < customers.length() - 2; ++i) {
            c += customers.charAt(i);
        }
        return c;
    }

}