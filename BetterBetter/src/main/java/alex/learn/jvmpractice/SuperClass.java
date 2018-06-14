package alex.learn.jvmpractice;

/**
 * zhiguang
 */
public class SuperClass {
    static {
        System.out.println("super class init");
    }
    public static String value ="";
    public static final String cons ="MyConstant";
}

class  SubClass extends SuperClass{
    static {
        System.out.println("sub class init");
    }
}

class MainDemo {
    public static void main(String [] args){
        System.out.println(SuperClass.cons);
        //SuperClass[] arr = new SuperClass[5];
        //SuperClass arr = new SuperClass();
        //SubClass arr = new SubClass();
    }
}

/*
class MainDemo {
    public static void main(String [] args){
        System.out.println(SubClass.value);
    }
}*/
