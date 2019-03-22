package juc.collections;

import com.google.common.collect.Lists;
import juc.ThreadPoolFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CopyOnWriteArrayList并发安全
 *
 * @Author: Hu Jianbo
 * @Date: 2019/3/21 0021 下午 20:16
 */
public class CopyOnWriteArrayListTest {

    //public static void main(String[] args) {
    //    ArrayList<Object> list = Lists.newArrayList();
    //    ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
    //    for (int i = 0; i < 10; i++) {
    //        pool.execute(new Runnable() {
    //            @Override
    //            public void run() {
    //                write(list);
    //            }
    //        });
    //    }
    //    pool.execute(new Runnable() {
    //        @Override
    //        public void run() {
    //            read(list);
    //        }
    //    });
    //    for (int i = 0; i < 10; i++) {
    //        pool.execute(new Runnable() {
    //            @Override
    //            public void run() {
    //                write(list);
    //            }
    //        });
    //    }
    //}

    public static void main(String[] args) {
        ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>(Lists.newArrayList(1, 2));
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    write(list);
                }
            });
        }

        pool.execute(new Runnable() {
            @Override
            public void run() {
                read(list);
            }
        });
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    write(list);
                }
            });
        }

        pool.shutdown();
    }

    private static void read(List list) {
        for (Object obj : list) {
            // iterator循环，并发不安全下会报ConcurrentModificationException
            System.out.println(Thread.currentThread().getName() + "read:" + obj);
        }
    }

    private static void write(List list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.println(Thread.currentThread().getName() + "write:" + i);
        }
    }
}
