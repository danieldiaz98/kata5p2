/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import java.util.List;
import kata5p2.model.Histogram;
import kata5p2.model.Mail;
import kata5p2.view.HistogramDisplay;
import kata5p2.view.MailHistogramBuilder;
import kata5p2.view.MailListReaderBD;

public class Kata5P2 {

    private static Connection connect() {
        String url = "jdbc:sqlite:kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
            rs.getString("Name") + "\t" +
            rs.getString("Apellidos") + "\t" +
            rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createNewTable() {
        String url = "jdbc:sqlite:kata5.db";
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";
        
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insert(String email) {
        String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
       List<Mail> newMailArray = new ArrayList<Mail>();
       Histogram<String> histogram = new Histogram<String>();
       for (Mail mail : MailListReaderBD.read()) {
           newMailArray.add(mail);
       }
       histogram = MailHistogramBuilder.build(newMailArray);
       HistogramDisplay histoDisplay = new HistogramDisplay(histogram);
       histoDisplay.execute();
    }
    
}