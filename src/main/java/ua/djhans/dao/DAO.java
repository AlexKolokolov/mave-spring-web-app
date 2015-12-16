package ua.djhans.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Administrator on 09.12.2015.
 */
public class DAO {
    private static DAO instance;
    private final ApplicationContext context;
    private final JdbcTemplate jdbc;
    private final Logger log;

    private DAO() {
        context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});
        jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
        log = LoggerFactory.getLogger(DAO.class);
    }

    public static DAO getDAO() {
        if (instance == null) instance = new DAO();
        return instance;
    }

    public List<Book> getBooks(int authorId) {
        String sqlQuery = "SELECT b.book_id, w.name_en, w.name_rus, b.title_en, b.title_rus " +
                "FROM books b INNER JOIN writers w ON b.author_id = w.writer_id";
        if(authorId != 0) sqlQuery += " AND b.author_id = " + authorId;
        List<Book> books = jdbc.query(sqlQuery, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                log.debug("book: {}", book);
                return book;
            }
        });
        return books;
    }

    public List<Writer> getWriters() {
        String sqlQuery = "SELECT writer_id, name_en, name_rus FROM writers";
        List<Writer> writers = jdbc.query(sqlQuery, new RowMapper<Writer>() {
            @Override
            public Writer mapRow(ResultSet rs, int i) throws SQLException {
                Writer writer = new Writer(rs.getInt(1), rs.getString(2), rs.getString(3));
                log.debug("writer: {}", writer);
                return writer;
            }
        });
        return writers;
    }

    public static void main(String[] args) {
        DAO.getDAO().log.debug("Первая в списке книга Бальзака: {}", DAO.getDAO().getBooks(4).get(0));
    }
}
