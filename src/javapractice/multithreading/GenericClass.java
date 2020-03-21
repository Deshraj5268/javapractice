package javapractice.multithreading;


class MyGeneric<T>{
    T luckyNum;

    public T getLuckyNum(){
        return this.luckyNum;
    }

    public void setLuckyNum(T luckyNum){
        this.luckyNum = luckyNum;
    }
}

public class GenericClass {

    public static void main(String[] args) {
        MyGeneric<Integer> generic = new MyGeneric<Integer>();
        generic.setLuckyNum(5);
        System.out.println(generic.getLuckyNum());
    }
}
