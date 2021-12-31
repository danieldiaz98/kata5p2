/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p2.view;

import java.util.List;
import kata5p2.model.Histogram;
import kata5p2.model.Mail;

public class MailHistogramBuilder {

    public MailHistogramBuilder() {
    }
    public static Histogram<String> build(List<Mail> lista){
        Histogram<String> histogram = new Histogram<String>();
        for (Mail mail : lista) {
            histogram.increment(mail.getDomain());
        }
        return histogram;
    }
}
