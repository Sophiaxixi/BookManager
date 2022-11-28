package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 10:56
 * @Description: mapper
 * @Version: 1.0
 */
public interface BookMapper {
    @Results({
            @Result(column = "sname", property = "name"),
            @Result(column = "sex", property = "sex")
    })
    @Insert("insert into student(sname, sex) values(#{name}, #{sex})")
    int addStudent(Student student);

    @Results({
            @Result(column = "bname", property = "name"),
    })
    @Insert("insert into book(bname, descrip) values(#{name}, #{descrip})")
    int addBook(Book book);

    @Results({
            @Result(column = "sname", property = "name"),
            @Result(column = "sex", property = "sex")
    })
    @Select("select * from student")
    List<Student> studentList();


    @Results({
            @Result(column = "bname", property = "name"),
    })
    @Select("select * from book")
    List<Book> bookList();

    @Insert("insert into borrow(sid, bid) values(#{sid}, #{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(column = "sid", property = "student", one = @One(select = "getStudentBySid")),
            @Result(column = "bid", property = "book", one = @One(select = "getBookByBid")),
    })
    @Select("select * from borrow")
    List<Borrow> borrowList(); //要在borrow类中加入无参构造，否则无法映射成功

    @Results({
            @Result(column = "sname", property = "name"),
            @Result(column = "sex", property = "sex")
    })
    @Select("select * from student where sid = #{sid}")
    Student getStudentBySid(int sid);

    @Results({
            @Result(column = "bname", property = "name"),
    })
    @Select("select * from book where bid = #{bid}")
    Book getBookByBid(int bid);


}
