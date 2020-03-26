package demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaConfig {
    public static void main(String[] args)throws IOException {
        InputStream is= JavaConfig.class.getClassLoader().getResourceAsStream("some.properties");
        Properties properties = new Properties();
        properties.load(is);
        String v=properties.getProperty("mysql.host");
        System.out.println(v);
    }
}
