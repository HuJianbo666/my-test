package jdk.thread;

/**
 * 同步回调demo
 *
 * @author Hu Jianbo
 * @date: 2018/5/24
 */
public class SyncCallBackTest {

    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("msssssssg");
    }

    private static class Client {

        private Server server;

        Client(Server server) {
            this.server = server;
        }

        public void sendMsg(String msg) {
            System.out.println("客户端正在发送消息: " + msg);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    server.getMsg(new MyCallBack(), msg);
                }
            }).start();

        }
    }

    private static class MyCallBack implements CallBack {

        @Override
        public void process(int status) {
            System.out.println("处理完成，返回状态：" + status);
        }
    }

    private interface CallBack {
        void process(int status);
    }

    private static class Server {

        public void getMsg(CallBack callBack, String msg) {

            System.out.println("服务器获取到消息：" + msg);
            try {
                System.out.println("处理消息中...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callBack.process(200);
        }
    }
}
