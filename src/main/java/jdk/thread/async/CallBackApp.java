package jdk.thread.async;

/**
 * @author Hu Jianbo
 * @date: 2018/5/24
 */
public class CallBackApp {

    public static void main(String[] args) {

        System.out.println("准备开始执行异步任务...");

        final Object context = "上下文信息";

        new CallBackTask(new CallBackBody() {
            @Override
            void execute(Object context) throws Exception {
                System.out.println("\n正在执行耗时操作...");
                System.out.println(context);
                Thread.sleep(3000);
                System.out.println("\n执行完成！");
            }
            @Override
            void onSuccess(Object context) {
                System.out.println("\n成功后的回调函数...");
                System.out.println(context);
            }
            @Override
            void onFailure(Object context) {
                System.out.println("\n失败后的回调函数...");
                System.out.println(context);
            }
        }).start(context);

        System.out.println("\n异步任务已经开始，请等待完成...");
    }
}
