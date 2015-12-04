package com.unifil.agendapaf.adapter;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author danielmorita
 * 
 * Adapter (for JAXB) to convert between the LocalDateTime and the ISO 8601 String
 * representation of the date such as '2012-12-03'.
 *
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }

}
