package jdk.tree.binarytree;

/**
 * 二叉树
 *
 * @author Hu Jianbo
 * @date: 2018/5/18
 */
public class Node {

    public int iData;
    public double dData;
    public Node leftNode;
    public Node rightNode;

    private Node root;

    @Override
    public String toString() {
        return "Node{" +
                "iData=" + iData +
                ", dData=" + dData +
                '}';
    }

    public void insert(int iData, double dData) {
        Node newNode = new Node();
        newNode.iData = iData;
        newNode.dData = dData;

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (iData < current.iData) {
                    current = current.leftNode;
                    if (current == null) {
                        parent.leftNode = newNode;
                        return;
                    }
                } else {
                    current = current.rightNode;
                    if (current == null) {
                        parent.rightNode = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node find(int key) {
        Node current = root;
        if (current == null) {
            return null;
        }
        while (current.iData != key) {
            if (current.iData > key) {
                current = current.leftNode;
            } else {
                current = current.rightNode;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * 查找最大值和最小值
     *
     * @return
     */
    public Node[] mVal() {

        Node minNode = null;
        Node maxNode = null;
        Node[] nodes = new Node[2];
        Node current = root;
        while (current != null) {
            minNode = current;
            current = current.leftNode;
        }
        nodes[0] = minNode;
        current = root;
        while (current != null) {
            maxNode = current;
            current = current.rightNode;
        }
        nodes[1] = maxNode;
        return nodes;
    }

    public static void main(String[] args) {
        Node tree = new Node();
        tree.insert(3, 3.1);
        tree.insert(1, 1.2);
        tree.insert(2, 5.3);
        tree.insert(4, 3.2);
        tree.insert(5, 8.8);

        Node node = tree.find(2);
        System.out.println(node);

        Node[] nodes = tree.mVal();
        System.out.println(nodes[0]);
        System.out.println(nodes[1]);
    }
}
