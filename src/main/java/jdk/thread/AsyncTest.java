package jdk.thread;

import java.util.concurrent.*;

/**
 * @author Hu Jianbo
 * @date: 2018/5/23
 */
public class AsyncTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future submit = service.submit(new DemoService());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private static class DemoService implements Callable {
        @Override
        public Object call() throws Exception {
            return "calculate...";
        }
    }
}
