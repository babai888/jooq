package com.example.jooq;

import com.example.jooq.tables.Article;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.jooq.Tables.ARTICLE;
import static com.example.jooq.Tables.AUTHOR;


public class TestDSL {
    public static void main(String[] args) {

        String userName = "postgres";
        String password = "admin";
        String url = "jdbc:postgresql://localhost:5432/jooq";
        try(Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext context = DSL.using(conn, SQLDialect.POSTGRES);

            Result<Record> records = context.fetch("select id, title, description from article");
            System.out.println(records);
            for(Record record: records) {
                Integer id = record.get(ARTICLE.ID);
                String title = record.get(ARTICLE.TITLE);
                System.out.println("Вывожу");
                System.out.println("record" + record);
            }

            Result<Record3<Integer, String, String>> sel = context.select(AUTHOR.ID, AUTHOR.FIRST_NAME,AUTHOR.LAST_NAME).from(AUTHOR).fetch();
            System.out.println(sel);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
