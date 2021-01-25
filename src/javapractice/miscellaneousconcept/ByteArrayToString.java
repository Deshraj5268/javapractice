package javapractice.miscellaneousconcept;


import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ByteArrayToString {

    public static void main(String[] args) {
        System.out.println("platform encoding :"+System.getProperty("file.encoding"));
        try {
            FileInputStream fis = new FileInputStream("G:\\project\\javaproject\\encodingPractice.xml");
            // Using Apache Commons IOUtils to read file into byte array
            byte[] fileData = IOUtils.toByteArray(fis);
            String str = new String(fileData, StandardCharsets.UTF_8);
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
