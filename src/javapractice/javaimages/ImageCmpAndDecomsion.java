package javapractice.javaimages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class ImageCmpAndDecomsion {

    public static void compression(){
        try {
            FileInputStream fis = new FileInputStream("G:\\image\\in\\"+"IMG_20190930_151639.jpg");

            //Assign compressed file:file2 to FileOutputStream
            FileOutputStream fos = new FileOutputStream("G:\\file2");

            //Assign FileOutputStream to DeflaterOutputStream
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);

            //read data from FileInputStream and write it into DeflaterOutputStream
            int data;
            while ((data = fis.read()) != -1) {
                dos.write(data);
            }

            System.out.println("com");
            //close the file
            fis.close();
            dos.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void decompression(){
        try {
            FileInputStream fis = new FileInputStream("G:\\file2");

            //assign output file: file3 to FileOutputStream for reading the data
            FileOutputStream fos = new FileOutputStream("G:\\file3");

            //assign inflaterInputStream to FileInputStream for uncompressing the data
            InflaterInputStream iis = new InflaterInputStream(fis);

            //read data from inflaterInputStream and write it into FileOutputStream
            int data;
            while ((data = iis.read()) != -1) {
                fos.write(data);
            }
            System.out.println("size");

            //close the files
            fos.close();
            iis.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        compression();
        decompression();
    }
}
