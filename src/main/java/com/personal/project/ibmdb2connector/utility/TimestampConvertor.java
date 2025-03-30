package com.personal.project.ibmdb2connector.utility;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConvertor {

    public static Timestamp convertStringToTimestamp(String timestampString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        LocalDateTime localDateTime = LocalDateTime.parse(timestampString, formatter);

        return Timestamp.valueOf(localDateTime);
        
    }

}
