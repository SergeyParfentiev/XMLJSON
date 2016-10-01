package classExample.xml.example3;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlType(propOrder = {"title", "price", "author", "publishDate"})
public class Book {

    private String author;
    private String title;
    private double price;
    private Date publishDate;

    public Book() {
    }

    public Book(String author, String title, double price, Date publishDate) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "[" + author + ", " + title + ", " + price + ", " + publishDate + "]";
    }

    public Date getPublishDate() {
        return publishDate;
    }

    @XmlElement(name = "publish_date")
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    //    @XmlPath
    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }
}
