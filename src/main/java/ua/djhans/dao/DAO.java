package ua.djhans.dao;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 09.12.2015.
 */
public class DAO {
    private static DAO instance;
    ApplicationContext context;
    JdbcTemplate jdbc;

    private DAO() {
        context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});
        jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
    }

    public static DAO getDAO() {
        if (instance == null) instance = new DAO();
        return instance;
    }

    public List<Book> getBooks(int authorId) {

        List<Writer> writers = getWriters(authorId);
        Map<Integer, Writer> writerMap = new HashMap<>();
        for (Writer writer : writers) writerMap.put(writer.getId(),writer);

        String sqlQuery = "select book_id, title_en, title_rus, author_id from books";
        if(authorId != 0) sqlQuery += " where author_id = " + authorId;
        List<Book> books = jdbc.query(sqlQuery, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book(rs.getInt(1), writerMap.get(rs.getInt(4)).getNameEn(), writerMap.get(rs.getInt(4)).getNameRus(), rs.getString(2), rs.getString(3));
                return book;
            }
        });
        return books;
    }

    public List<Writer> getWriters(int authorId) {
        String sqlQuery = "select writer_id, name_en, name_rus from writers";
        if (authorId != 0) sqlQuery += " where writer_id = " + authorId;
        List<Writer> writers = jdbc.query(sqlQuery, new RowMapper<Writer>() {
            @Override
            public Writer mapRow(ResultSet rs, int i) throws SQLException {
                Writer writer = new Writer(rs.getInt(1), rs.getString(2), rs.getString(3));
                return writer;
            }
        });
        return writers;
    }

    public List<Writer> getWriters() {
        return getWriters(0);
    }
}
