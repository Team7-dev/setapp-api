package br.com.uniplan.pim.setappapi.util;

import br.com.uniplan.pim.setappapi.exception.DateParseException;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static Date getDateFromString(String dateString, String fieldName) {
        Date date = null;
        if (StringUtils.isNotBlank(dateString)) {
            try {
                date = new SimpleDateFormat(DATE_PATTERN_YYYY_MM_DD).parse(dateString);
            } catch (ParseException e) {
                throw new DateParseException(dateString, fieldName);
            }
        }
        return date;
    }

    public static String getStringFromDate(Date date) {
        String dateString = null;
        if (date != null) {
            dateString = new SimpleDateFormat(DATE_PATTERN_YYYY_MM_DD).format(date);
        }
        return dateString;
    }

}
