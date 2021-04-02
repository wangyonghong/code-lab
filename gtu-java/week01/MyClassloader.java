import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yonghongwang#163.com
 */
public class MyClassloader extends ClassLoader {
    public static void main(String[] args) {
        Class<?> helloClass = new MyClassloader().findClass("Hello");
        Method helloMethod = null;
        try {
            helloMethod = helloClass.getMethod("hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            helloMethod.invoke(helloClass.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        String path = this.getClass().getResource("Hello.xlass").getPath();
        File file;
        try {
            file = new File(URLDecoder.decode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("failed to find path: " + path);
        }
        byte[] bytes;
        if (file.isFile() && file.exists()) {
            try (FileChannel channel = new FileInputStream(file).getChannel()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
                channel.read(byteBuffer);
                bytes = byteBuffer.array();
            } catch (IOException e) {
                throw new RuntimeException("failed to find path: " + path);
            }
        } else {
            throw new RuntimeException("failed to find path: " + path);
        }
        return defineClass(name, decode(bytes), 0, bytes.length);
    }

    /**
     * replace each byte with x->255-x
     */
    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = (byte) (255 - bytes[i]);
            bytes[i] = (byte) ~bytes[i];
        }
        return bytes;
    }
}
