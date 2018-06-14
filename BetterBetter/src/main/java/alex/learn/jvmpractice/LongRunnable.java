package alex.learn.jvmpractice;

public interface LongRunnable {
    void longrun();
}


interface FastRunnable extends LongRunnable {
    void fastrun();
}
