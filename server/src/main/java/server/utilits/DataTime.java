package server.utilits;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTime {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();

    public String setDate() {
        return dateFormat.format(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null ");
        }
        this.date = date;
    }
}
