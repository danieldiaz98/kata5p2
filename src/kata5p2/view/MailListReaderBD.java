/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p2.view;

import kata5p2.model.Mail;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailListReaderBD {
    public MailListReaderBD() {
        
    }
    public static List<Mail> read(){
        String url = "jdbc:sqlite:KATA5.db";
        String sql = "SELECT Mail FROM EMAIL";
        List<Mail> lista = new ArrayList<Mail>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                lista.add(new Mail(rs.getString("Mail")));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return lista;
    }
}
