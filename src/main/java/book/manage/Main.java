package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import book.manage.mapper.BookMapper;
import book.manage.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 10:53
 * @Description: book.manage
 * @Version: 1.0
 */
@Log
public class Main {
    public static void main(String[] args) throws IOException {
        try(Scanner scanner = new Scanner(System.in)) {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("---------------图书管理系统开始----------");
                System.out.println("1.添加学生信息");
                System.out.println("2.添加书籍信息");
                System.out.println("3.查看学生信息");
                System.out.println("4.查看书籍信息");
                System.out.println("5.添加订阅信息");
                System.out.println("6.查看所有订阅信息");

                System.out.print("请输入你想要的操作（输入其他任意数字建退出）:");
                int input=0;
                try{
                    input = scanner.nextInt();
                }catch (Exception e){
                    e.printStackTrace();
                }
                scanner.nextLine();//吸收换行符
                switch (input){
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        studentList(scanner);
                        break;
                    case 4:
                        bookList(scanner);
                        break;
                    case 5:
                        addBorrow(scanner);
                        break;
                    case 6:
                        borrowList(scanner);
                        break;
                    default:
                        return;
                }


            }
        }


    }
    public static void addStudent(Scanner scanner){
        System.out.print("请输入学生姓名：");//用print就没有换行
        String name = scanner.nextLine();
        System.out.print("请输入学生性别(男/女)：");
        String sex = scanner.nextLine();
        Student student = new Student(name,sex);
        SqlUtil.doSqlWork(mapper->{
            int i = mapper.addStudent(student);
            if(i>0){
                System.out.println("学生信息录入成功！");
                log.info("新添加了一条学生信息："+student);
            }
            else System.out.println("学生信息录入失败，请重试！");
        });

    }

    public static void addBook(Scanner scanner){
        System.out.print("请输入书籍名称：");//用print就没有换行
        String name = scanner.nextLine();
        System.out.print("请输入书籍描述：");
        String des = scanner.nextLine();
        Book book = new Book(name,des);
        SqlUtil.doSqlWork(mapper->{
            int i = mapper.addBook(book);
            if(i>0){
                System.out.println("书籍信息录入成功！");
                log.info("新增加了一本书："+book);
            }
            else System.out.println("书籍信息录入失败，请重试！");
        });

    }

    public static void addBorrow(Scanner scanner){
        System.out.print("请输入订阅学生号：");//用print就没有换行
        int sid = scanner.nextInt();
        System.out.print("请输入被订阅书籍号：");
        int tid = scanner.nextInt();
        SqlUtil.doSqlWork(mapper->{
            int i = mapper.addBorrow(sid,tid);
            if(i>0) System.out.println("订阅信息录入成功！");
            else System.out.println("订阅信息录入失败，请重试！");
        });

    }

    public static void studentList(Scanner scanner){
        SqlUtil.doSqlWork(mapper->{
            mapper.studentList().forEach(student -> {
                System.out.println(student.getSid()+": "+ student.getName()+", "+student.getSex());
            });
        });

    }

    public static void bookList(Scanner scanner){
        SqlUtil.doSqlWork(mapper->{
            mapper.bookList().forEach(book -> {
                System.out.println(book.getBid()+": <<"+ book.getName()+">>, : "+book.getDescrip());
            });
        });

    }

    public static void borrowList(Scanner scanner){
        SqlUtil.doSqlWork(mapper->{
            mapper.borrowList().forEach(borrow -> {
                System.out.println(borrow.getStudent().getName()+ "借了<<"+borrow.getBook().getName()+">>这本书");
            });
        });

    }
}
