package org.bridgeLabz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.Scanner;

/**
 * @author Govardhan Reddy
 */

public class AddressBook {
    /**
     * In this class we perform on address book service database
     */
    static String jdbc = "jdbc:mysql://localhost:3306/addressbookservice";
    static String userName = "root";
    static String password = "Muni@5612";
    static ContactDetails details = new ContactDetails();
    static Scanner input = new Scanner(System.in);

    public static void read() {
        /**
         * In this method read the address book table
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM addressBook");
            System.out.println("firstName   lastName    address     city    state    zip     phoneNumber     email");
            System.out.println("-----------------------------------------------------------------------------------");
            while (result.next()) {
                details.setFirstName(result.getString("firstName"));
                details.setLastName(result.getString("lastName"));
                details.setAddress(result.getString("address"));
                details.setCity(result.getString("city"));
                details.setState(result.getString("state"));
                details.setZip(result.getInt("zip"));
                details.setPhoneNumber(result.getInt("phoneNumber"));
                details.setEmail(result.getString("email"));
                System.out.println(details.getFirstName() + "     " + details.getLastName() + "     " + details.getAddress() + "     " + details.getCity() + "     " + details.getState() + "     " + details.getZip() + "     " + details.getPhoneNumber() + "     " + details.getEmail());
            }
        } catch (Exception e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void edit() {
        /**
         * In this method edit the contact details from address book table
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            PreparedStatement prepare = connection.prepareStatement("update addressBook " + "set firstName = ?,address = ?,lastName=?,city  =?,state = ?,zip=?,PhoneNumber  =?,email=? " + "where firstName = ?");
            System.out.print("Enter Exiting name : ");
            String name = input.next();
            System.out.print("Enter first name : ");
            details.setFirstName(input.next());
            System.out.print("Enter last name : ");
            details.setLastName(input.next());
            System.out.print("Enter address : ");
            details.setAddress(input.next());
            System.out.print("Enter city : ");
            details.setCity(input.next());
            System.out.print("Enter State : ");
            details.setState(input.next());
            System.out.print("Enter zip : ");
            details.setZip(input.nextInt());
            System.out.print("Enter phone number : ");
            details.setPhoneNumber(input.nextInt());
            System.out.print("Enter email : ");
            details.setEmail(input.next());
            prepare.setString(1, details.getFirstName());
            prepare.setString(2, details.getLastName());
            prepare.setString(3, details.getAddress());
            prepare.setString(4, details.getCity());
            prepare.setString(5, details.getState());
            prepare.setInt(6, details.getZip());
            prepare.setInt(7, details.getPhoneNumber());
            prepare.setString(8, details.getEmail());
            prepare.setString(9, name);
            prepare.executeLargeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void dateInRange() {
        /**
         * In this method read the joining date of employee payroll service database
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            String query = "SELECT * FROM addressbook WHERE startDate BETWEEN '2022-01-01' AND '2022-12-31'";
            PreparedStatement prepare = connection.prepareStatement(query);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                System.out.print("first name : ");
                System.out.print(result.getString("firstName"));
                System.out.print(" , last name : ");
                System.out.print(result.getString("lastName"));
                System.out.print(" , joining date : ");
                System.out.print(result.getDate("startDate"));
                System.out.println();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void countOfCity() {
        /**
         * In this method read the of employee payroll service database print the salary details
         */
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            String query = "select count(city) from addressbook where city = 'tirupati'";
            PreparedStatement prepare = connection.prepareStatement(query);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                count = result.getInt(1);
            }
            System.out.println("count of city = " + count);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void add() {
        /**
         * In this method edit the contact details from address book table
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            PreparedStatement prepare = connection.prepareStatement("insert into addressBook (firstName,lastName,address,city,state,zip,PhoneNumber,email)values(?,?,?,?,?,?,?,?)");
            System.out.print("Enter first name : ");
            details.setFirstName(input.next());
            System.out.print("Enter last name : ");
            details.setLastName(input.next());
            System.out.print("Enter address : ");
            details.setAddress(input.next());
            System.out.print("Enter city : ");
            details.setCity(input.next());
            System.out.print("Enter State : ");
            details.setState(input.next());
            System.out.print("Enter zip : ");
            details.setZip(input.nextInt());
            System.out.print("Enter phone number : ");
            details.setPhoneNumber(input.nextInt());
            System.out.print("Enter email : ");
            details.setEmail(input.next());
            prepare.setString(1, details.getFirstName());
            prepare.setString(2, details.getLastName());
            prepare.setString(3, details.getAddress());
            prepare.setString(4, details.getCity());
            prepare.setString(5, details.getState());
            prepare.setInt(6, details.getZip());
            prepare.setInt(7, details.getPhoneNumber());
            prepare.setString(8, details.getEmail());
            prepare.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void jsonWriter() {
        /**
         * Use json dependencies to write json files
         */
        JSONObject contactDetails = new JSONObject();
        contactDetails.put("firstName", "Govardhan");
        contactDetails.put("lastName", "Reddy");
        contactDetails.put("address", "tirupati");
        contactDetails.put("city","Trupati");
        contactDetails.put("State","AndhraPradesh");
        contactDetails.put("zip","123456");
        contactDetails.put("phoneNumber","8555850406");
        contactDetails.put("email","lokesh@gmail.com");
        JSONObject contactObject = new JSONObject();
        contactObject.put("contact details", contactDetails);
        JSONObject contactDetails1 = new JSONObject();
        contactDetails1.put("firstName", "Lokesh");
        contactDetails1.put("lastName", "Gupta");
        contactDetails1.put("address", "tirupati");
        contactDetails1.put("city","Trupati");
        contactDetails1.put("State","AndhraPradesh");
        contactDetails1.put("zip","123456");
        contactDetails1.put("phoneNumber","8555850406");
        contactDetails1.put("email","govardhan@gmail.com");
        JSONObject contactObject1 = new JSONObject();
        contactObject.put("contact details", contactDetails1);
        JSONArray contactList = new JSONArray();
        contactList.add(contactObject);
        contactList.add(contactObject1);
        try (FileWriter file = new FileWriter("C:\\Users\\govar\\Desktop\\BridgeLabz Daily Assignments\\AddressBook_JDBC\\src\\main\\java\\org\\bridgeLabz\\AddressBook.json")) {
            file.write(contactList.toJSONString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void jsonFileRead() {
        /**
         * Use json dependencies to read json files
         */
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\govar\\Desktop\\BridgeLabz Daily Assignments\\AddressBook_JDBC\\src\\main\\java\\org\\bridgeLabz\\AddressBook.json")) {
            Object object = jsonParser.parse(reader);
            JSONArray contactList = (JSONArray) object;
            System.out.println(contactList);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        jsonWriter();
        jsonFileRead();
    }

}
