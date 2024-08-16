package mouse.hoi.tools.parser.data;

import lombok.Data;

@Data
public class GameDate {
    private int year;
    private int month;
    private int day;
    private int hour;
    public GameDate(int year, int month) {
        this.year = year;
        this.month = month;
        this.day = 0;
        this.hour = 0;
    }
    public GameDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public GameDate(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
    }

    public String write() {
        if (day == 0) {
            return String.format("%d.%d", year, month);
        }
        if (hour == 0) {
            return String.format("%d.%d.%d", year, month, day);
        }
        return String.format("%d.%d.%d.%d", year, month, day, hour);
    }

    @Override
    public String toString() {
        return write();
    }
}
