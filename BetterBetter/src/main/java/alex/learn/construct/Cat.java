package alex.learn.construct;

import java.util.HashMap;

/**
 * Created by zhiguangai on 2017/9/7.
 */
public class Cat {
    //执行顺序
    //静态代码块 -->构造代码块 --->构造方法 --->类的方法run
    private String color;

    {
        System.out.println("构造代码块, 每次创建对象, 都会调用, 但实际用的很少");
    }

    static{
        System.out.println("静态代码块");
    }

    public Cat(){
        System.out.println("无参");
    }

    public Cat(String color){
        String a = "";
      this.color = color;
    }

    public void run(){
        System.out.println("run");
    }

    public static void main(String[] args){
        //Cat cat = new Cat();
       // cat.run();
        HashMap<String, Dog> map = new HashMap<String, Dog>();
        Dog dog = new Dog();
        dog.setColor("black");
        dog.setLeg(4);
        map.put(dog.getColor(),dog);
        System.out.print(map.get(dog.getColor()));
    }
}
