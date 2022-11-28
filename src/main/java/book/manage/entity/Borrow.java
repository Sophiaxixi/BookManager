package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shlin
 * @Date: 2022/11/28 - 11 - 28 - 15:51
 * @Description: book.manage.entity
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    Student student;
    Book book;
}
