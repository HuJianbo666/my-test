package guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * 添加回调
 * Created by hujianbo on 2018/1/25.
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<String> future = service.submit(() -> {
            System.out.println("call exe..");
            return "task success!";
        });
        future.addListener(() -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, service);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure:" + t.getMessage());
            }
        }, service);

    }
}
