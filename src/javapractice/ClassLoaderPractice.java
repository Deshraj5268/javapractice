package javapractice;

class String{
    public String(){
        System.out.println("i  in user defined string class ");
    }
}

public class ClassLoaderPractice {
    public static void main(java.lang.String[] args) {
        System.out.println("im in main"+ String.class.getClassLoader());
        try {
            System.out.println("im in main "+Class.forName("javapractice.String").newInstance());
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader.loadClass("javapractice.String"));
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
