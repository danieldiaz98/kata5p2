/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p2.model;

/**
 *
 * @author danie
 */

public class Mail {
    private String mail;

    public Mail(String mail) {
        this.mail = mail;
    }

    public String getDomain() {
        for (int i = 0; i < mail.length(); i++) {
            if(mail.charAt(i) == '@'){
                mail = mail.substring(i+1, mail.length());
            }
        }
        return mail;
    }
    
}
