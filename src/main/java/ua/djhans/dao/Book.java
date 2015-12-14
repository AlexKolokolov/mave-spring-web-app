package ua.djhans.dao;

/**
 * Created by Administrator on 08.12.2015.
 */
public class Book {
    private int id;
    private String authorEn;
    private String authorRus;
    private String titleEn;
    private String titleRus;

    public Book(int id, String authorEn, String authorRus, String titleEn, String titleRus) {
        this.id = id;
        this.authorEn = authorEn;
        this.authorRus = authorRus;
        this.titleEn = titleEn;
        this.titleRus = titleRus;
    }

    public int getId() {
        return id;
    }

    public String getAuthorEn() {
        return authorEn;
    }

    public String getAuthorRus() {
        return authorRus;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleRus() {
        return titleRus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorEn='" + authorEn + '\'' +
                ", authorRus='" + authorRus + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", titleRus='" + titleRus + '\'' +
                '}';
    }
}
