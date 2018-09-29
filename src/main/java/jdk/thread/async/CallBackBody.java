package jdk.thread.async;

/**
 * @author Hu Jianbo
 * @date: 2018/5/24
 */
public abstract class CallBackBody {

    void onSuccess(Object context) {
        System.out.println("onSuccess");
    }

    void onFailure(Object context) {
        System.out.println("onFailure");
    }

    abstract void execute(Object context) throws Exception;

}