package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.NameList;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 12:34
 * @Description: book.manage.entity
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Book {
    int bid;
    String name;
    String descrip;

    public Book(String name, String descrip){
        this.name = name;
        this.descrip = descrip;
    }
}
