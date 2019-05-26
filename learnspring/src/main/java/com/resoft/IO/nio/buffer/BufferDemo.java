package com.resoft.IO.nio.buffer;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {
    public static void decode(String str) throws UnsupportedEncodingException {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.put(str.getBytes("utf-8"));
        allocate.flip();
        Charset charset = Charset.forName("utf-8");
        CharBuffer decode = charset.decode(allocate);
        char[] chars = Arrays.copyOf(decode.array(), decode.limit());

        System.out.println(chars);
    }
    public static void encode(String str) throws UnsupportedEncodingException {
        CharBuffer buffer = CharBuffer.allocate(1024);

        buffer.append(str);
        buffer.flip();
        Charset charset = Charset.forName("utf-8");
        ByteBuffer encode = charset.encode(buffer);

        byte[] bytes = Arrays.copyOf(encode.array(), encode.limit());

        System.out.println(Arrays.toString(bytes));
    }
    @Test
    public void test() throws UnsupportedEncodingException {
        decode("你好");
        encode("龙汀");
    }
}
