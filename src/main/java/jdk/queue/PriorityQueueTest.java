package jdk.queue;

import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * PriorityQueue demo 优先队列
 *
 * @Author: Hu Jianbo
 * @Date: 2018/5/13 0013 下午 14:28
 */
public class PriorityQueueTest {

    @Data
    private static class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }


    public static void main(String[] args) {
        Queue queue = new PriorityQueue<>(7);
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 7; i++) {
            // 自然排序
            System.out.println(queue.poll());
        }

        Queue priorityQueue = new PriorityQueue<>(7, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getId() - o2.getId();
            }
        });
        for (int i = 0; i < 7; i++) {
            int id = random.nextInt(100);
            priorityQueue.offer(new Customer(id, i + ""));
        }
        for (int i = 0; i < 7; i++) {
            // 优先队列排序
            System.out.println(priorityQueue.poll());
        }

    }

}
