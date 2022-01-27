package serializable.pojo;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author zhuang.ma
 * @date 2021/8/19
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 206245307816188274L;

    private String name;

    private int id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public byte[] codeC(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = this.name.getBytes();
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.putInt(this.id);
        buffer.flip();
        bytes = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
    public byte[] codeC(ByteBuffer buffer){

        buffer.clear();
        byte[] value = this.name.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.id);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

}
