package jdk.bitset;

import java.util.*;

/**
 * BitSet进行与、或、异或运算
 *
 * @author Hu Jianbo
 * @date: 2018/5/23
 */
public class BitSetTest {

    public static void main(String args[]) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        bits1.set(1);
        bits1.set(2);

        bits2.set(2);
        bits2.set(3);

        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        // 注意：与，或，异或操作后，bits2的值都会改变
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        // 这里bits2变成了与后的{2}了！
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);

    }


    /**
     * 情景：在1-20中，随机产生10个随机数，求不在这1-20中的数
     */
    public void getExclusive() {
        Random random = new Random();

        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int randomResult = random.nextInt(20);
            list.add(randomResult);
        }
        System.out.println("产生的随机数有:");
        Collections.sort(list);
        System.out.print(list);

        BitSet bitSet = new BitSet(20);
        for (int i = 0; i < list.size(); i++) {
            bitSet.set(list.get(i));
        }

        System.out.println();
        System.out.println("bitSet:");
        System.out.println(bitSet);
        for (int i = 0; i < 20; i++) {
            // 在存的index设置为true，默认是false，例如set(io1)，则index为1的设置为true
            System.out.print(bitSet.get(i) + ",");
        }
        System.out.println();
        System.out.println("0~20不在上述随机数中有:");
        for (int i = 0; i < 20; i++) {
            if (!bitSet.get(i)) {
                System.out.print(i + ",");
            }
        }
    }
}
