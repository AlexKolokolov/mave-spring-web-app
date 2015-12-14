package ua.djhans.dao;

/**
 * Created by Administrator on 09.12.2015.
 */
public class Writer {
    private int id;
    private String nameEn;
    private String nameRus;

    public Writer(int id, String nameEn, String nameRus) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameRus = nameRus;
    }

    public int getId() {
        return id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameRus() {
        return nameRus;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameRus='" + nameRus + '\'' +
                '}';
    }
}
