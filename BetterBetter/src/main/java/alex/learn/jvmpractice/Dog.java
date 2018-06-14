package alex.learn.jvmpractice;

/**
 * zhiguang
 */
public class Dog implements FastRunnable{
    @Override
    public void longrun() {
        System.out.println("I can run a long way ... ");
    }

    @Override
    public void fastrun() {
        System.out.println("I can run with high speed ...  ");
    }
}

class DogDemo {
    public static void main(String [] args){
        Dog dog = new Dog();
        //dog.longrun();
        dog.fastrun();
        int[] aa = new int[5];
        System.out.println(aa.getClass().getName());
        System.out.println(aa.getClass().getSuperclass());
    }
}