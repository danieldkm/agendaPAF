package com.unifil.agendapaf.converter;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author danielmorita
 */
@Converter(autoApply = true)
public class ConverterLocalDate implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        Date dt = null;
        if (date != null) {
            dt = Date.valueOf(date);
        }
        return dt;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
//        System.out.println("TESTE DATE2222 " + value);
//        System.out.println("value.toLocalDate() " + value.toLocalDate());
        LocalDate ld = null;
        if (value != null) {
            ld = value.toLocalDate();
        }
        return ld;
    }
}
