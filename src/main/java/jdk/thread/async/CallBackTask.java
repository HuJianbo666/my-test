package jdk.thread.async;

/**
 * @author Hu Jianbo
 * @date: 2018/5/24
 */
public class CallBackTask {
    private CallBackBody body;

    public CallBackTask(CallBackBody body) {
        this.body = body;
    }

    protected void start(final Object context) {
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    body.execute(context);
                } catch (Exception e) {
                    e.printStackTrace();
                    body.onFailure(context);
                }
                body.onSuccess(context);
            }
        });
        t.start();
    }
}
