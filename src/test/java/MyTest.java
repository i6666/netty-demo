import org.junit.Test;
import serializable.pojo.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author zhuang.ma
 * @date 2021/8/19
 */
public class MyTest {

    @Test
    public void test1() throws IOException {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setName("li");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();

        byte[] bytes = bos.toByteArray();

        System.out.println("the jdk serializable length is :"+ bytes.length);
        bos.close();
        System.out.println("===============================");
        System.out.println("the byte array length is :"+userInfo.codeC().length);


    }


}
