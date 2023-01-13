package org.bridgeLabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Govardhan Reddy
 */

public class AddressBook {
    /**
     *
     */
    static String jdbc = "jdbc:mysql://localhost:3306/addressbookservice";
    static String userName = "root";
    static String password = "Muni@5612";
    public static void read(){
            try {
                Connection connection = DriverManager.getConnection(jdbc,userName, password);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM addressBook");
                System.out.println("firstName   lastName    address     city    state    zip     phoneNumber     email");
                System.out.println("-----------------------------------------------------------------------------------");
                while (result.next()) {
                    ContactDetails details = new ContactDetails();
                    details.setFirstName(result.getString("firstName"));
                    details.setLastName(result.getString("lastName"));
                    details.setAddress(result.getString("address"));
                    details.setCity(result.getString("city"));
                    details.setState(result.getString("state"));
                    details.setZip(result.getInt("zip"));
                    details.setPhoneNumber(result.getInt("phoneNumber"));
                    details.setEmail(result.getString("email"));
                    System.out.println(details.getFirstName()+"     "+details.getLastName()+"     "+details.getAddress()+"     "+details.getCity()+"     "+details.getState()+"     "+details.getZip()+"     "+details.getPhoneNumber()+"     "+details.getEmail());
                }
            } catch(Exception e) {
                System.out.println("exception occurred" + e);
            }
        }

    public static void main(String[] args) {
        read();
    }
}
