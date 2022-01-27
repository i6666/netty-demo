package hello;

import lombok.Data;

/**
 * @author zhuang.ma
 * @date 2022/1/5
 */
@Data
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
