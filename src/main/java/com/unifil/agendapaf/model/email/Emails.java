package com.unifil.agendapaf.model.email;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "Emails")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Emails", propOrder = {
    "emails"
})
public class Emails {

    @XmlElement(name = "Emails", required = true)
    private List<Email> emails;

    public List<Email> getEmails() {
        if (emails == null) {
            emails = new ArrayList<Email>();
        }
        return this.emails;
    }

}
