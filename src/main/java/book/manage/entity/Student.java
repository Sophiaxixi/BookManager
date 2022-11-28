package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 10:56
 * @Description: book.manage.entity
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Student {
    int sid;
    String name;
    String sex;

    public Student(String name, String sex){
        this.name = name;
        this.sex = sex;
    }
}
