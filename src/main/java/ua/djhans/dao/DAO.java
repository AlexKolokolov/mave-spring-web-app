package ua.djhans.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 09.12.2015.
 */
public class DAO {
    private static DAO instace;
    ApplicationContext context;
    JdbcTemplate jdbc;

    private DAO() {
        context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});
        jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
    }

    public static DAO getDAO() {
        if (instace == null) instace = new DAO();
        return instace;
    }

    public ArrayList<Book> getBooks(int authorId) {
        ArrayList<Writer> writers = getWriters(authorId);
        ArrayList<Book> books = new ArrayList<>();
        String sql = "select book_id, title_en, title_rus, author_id from books";
        if(authorId != 0) sql += " where author_id = " + authorId;
        for (Map<String,Object> row : jdbc.queryForList(sql)) {
            for (Writer writer : writers) {
                if (writer.getId() == (int)(long)row.get("author_id"))
                    books.add(new Book((int)(long)row.get("book_id"), writer.getNameEn(), writer.getNameRus(), (String)row.get("title_en"), (String)row.get("title_rus")));
            }
        }
        return books;
    }

    public ArrayList<Writer> getWriters(int authorId) {
        ArrayList<Writer> writers = new ArrayList<>();
        String sql = "select writer_id, name_en, name_rus from writers";
        if (authorId != 0) sql += " where writer_id = " + authorId;
        for (Map<String,Object> row : jdbc.queryForList(sql)) {
            writers.add(new Writer((int)(long)row.get("writer_id"), (String)row.get("name_en"), (String)row.get("name_rus")));
        }
        return writers;
    }
}
