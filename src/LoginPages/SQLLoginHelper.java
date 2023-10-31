package LoginPages;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 *
 * @author Jamie-Lee
 *
 * This class is used to authorize logins by fetching the data from the MySQL database and
 * comparing it to the values entered by the users by comparing their user ID and password, ensuring that they match.
 */
// Class used to read data from the database to confirm a login
public class SQLLoginHelper {

    private String name;
    private String staffIDStr;

    private String email;

    public SQLLoginHelper() {}

    /**
     * attemptLogin method will pass through the entered username and password of the user and compare with data in the database.
     */
    public boolean attemptLogin(String role, int id, String pwd) {

        boolean b = false; // this will change to true if the login is successful

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Encryptor e = new Encryptor();
            pwd = e.encryptPassword(pwd);

            Statement stm = con.createStatement();
            // executing the sql query to find the user in the database with the user-entered id and password
            ResultSet result = stm.executeQuery("SELECT * FROM AirViaUser WHERE Role = " + "'" + role + "'" + "AND ID = "
                    + id + " AND Password = " + "'" + pwd + "'");


            if (result.next()) {
                b = true; // if the id AND password match, then the login is successful
                // fetching the name and ID of the found staff member so that it can be displayed in their dashboard
                name = result.getString("FirstName") + " " + result.getString("LastName");
                int staffID = result.getInt("ID");
                staffIDStr = Integer.toString(staffID);
                email = result.getString("Email");
            }
            con.close(); // close the connection
        } catch (SQLException | NoSuchAlgorithmException s) {
            s.printStackTrace();
        }

        return b; // the boolean is returned to indicate a successful or failed login
    }

    public String getName() {
        return name;
    }
    public String getStaffID() {
        return staffIDStr;
    }

    public String getEmail() {
        return email;
    }
}
