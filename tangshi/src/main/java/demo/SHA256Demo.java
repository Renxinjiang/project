package demo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Demo {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //MD5
        //SHA-256
        MessageDigest messageDigest =MessageDigest.getInstance("SHA-256");//SHA-256长度是64
        String s = "你好世界";
        byte[] bytes = s.getBytes("UTF-8");//先传进去
        messageDigest.update(bytes);//得到结果
        byte[] result = messageDigest.digest();//存结果
        System.out.println(result.length);
        for(byte b: result){
            System.out.printf("%02x",b);
        }
        System.out.println();
    }
}
