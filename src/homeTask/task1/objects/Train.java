package homeTask.task1.objects;

public class Train {

    private String from;
    private String to;
    private String date;
    private String departure;

    public Train() {

    }

    public Train(String from, String to, String date, String departure) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
