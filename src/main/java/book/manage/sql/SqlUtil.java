package book.manage.sql;

import book.manage.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 11:49
 * @Description: book.manage.sql
 * @Version: 1.0
 */
public class SqlUtil {

    private SqlUtil(){};
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void doSqlWork(Consumer<BookMapper> consumer){
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            BookMapper mapper = session.getMapper(BookMapper.class);
            consumer.accept(mapper);
        }
    }
}
