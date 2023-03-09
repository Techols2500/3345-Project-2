/*
Tyler Echols

CS 3345.002 - Data Structures and Introduction to Algorithmic Analysis


Project #2

Due Dates:  Monday, March 8 at 11:59pm

Submit:    eLearning

Late Policy:  -10 points per hour late

Instructions: This is an individual assignment.  Answers should be your own work.
              You are expected to code this yourself from scratch by thinking
              through the requirements and design, then writing the code.  However,
              you may use code from the author's BinarySearchTree.


Introduction:

   In this project you will create a binary search tree.

*/
class Node<T> {
    T Data;
    Node<T> left, right;

    public Node(T Data) {
        this.Data = Data;  // stands for data in line 27

    }

    public Node(T Data, Node<T> left, Node<T> right) {
        this.Data = Data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return Data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setData(T data) {
        Data = data;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}

public class MySearchTree<T extends Comparable<T>> {

    private Node<T> Root;

    public Node<T> add(Node<T> Root, T stuff) {
        if (Root == null) {
            return new Node<T>(stuff);
        } else if (stuff.compareTo(Root.Data) < 0) {
            Root.left = add(Root.left, stuff);
        } else if (stuff.compareTo(Root.Data) > 0) {
            Root.right = add(Root.right, stuff);
        }
        return Root;
    }

    public void toadd(T stuff) {
        Root = add(Root, stuff);
    }

    public void inOrder(Node<T> Root) {
        if (Root == null) {
            return;
        }
        inOrder(Root.left);
        System.out.print(" " + Root.Data);
        inOrder(Root.right);
    }

    public void inOrderPrint() {
        System.out.print("inOrderPrint");
        inOrder(Root);
    }

    public void preOrder(Node<T> Root) {
        if (Root == null) {
            return;
        }
        System.out.print(" " + Root.Data);
        preOrder(Root.left);
        preOrder(Root.right);
    }

    public void preOrderPrint() {
        System.out.print("preOrderPrint");
        preOrder(Root);
    }

    public boolean findItem(Node<T> Root, T stuff) {
        if (Root == null) {
            return false;
        } else if (stuff.compareTo(Root.Data) < 0) {
            return findItem(Root.left, stuff);
        } else if (stuff.compareTo(Root.Data) > 0) {
            return findItem(Root.right, stuff);
        } else {
            return true;
        }
    }

    public boolean find(T stuff) {
        return findItem(Root, stuff);
    }

    public int leafCount(Node<T> Root) {
        if (Root == null) {
            return 0;
        }
        if (Root.left == null && Root.right == null) {
            return 1;
        } else {
            return leafCount(Root.left) + leafCount(Root.right);
        }

    }

    public int leafCount() {
        return leafCount(Root);
    }

    public int height(Node<T> Root) {
        if (Root == null) {
            return -1;
        }
        return 1 + Math.max(height(Root.left), height(Root.right));

    }

    public int height() {
        return height(Root);
    }

    public int parentCount(Node<T> Root) {
        if (Root == null) {
            return 0;
        } else if (Root.left == null && Root.right == null) {
            return 0;
        } else {
            return parentCount(Root.left) + parentCount(Root.right) + 1;
        }
    }

    public int parentCount() {
        return parentCount(Root);
    }

    public boolean isPerfect() {
        return (Math.pow(2,height(Root))-1) == ( leafCount()+parentCount(Root));
//2^(h) - 1 = total leaves and parent count
    }


    public boolean ancestor(Node<T> Root, T value){
        if(Root == null)
            return false;
        if(Root.Data == value)
        return true;
        boolean left = ancestor(Root.left, value);
        boolean right = ancestor(Root.right, value);
        if (left == true || right == true) { // if left or right is true then we gotta print the value
            System.out.println(Root.Data);

            return true;
        }
        return false;

    }



    public static void main(String[] args) {
        MySearchTree<Integer> TheObj = new MySearchTree<>();

        TheObj.toadd(1);
        TheObj.toadd(5);
        TheObj.toadd(2);
        TheObj.toadd(7);
        TheObj.toadd(4);

        TheObj.inOrderPrint();
        System.out.println("");
        TheObj.preOrderPrint();
        System.out.println("");
        System.out.println(TheObj.find(2));
        System.out.println(TheObj.find(6));
        System.out.println("");
        System.out.println("The leaf count is: " + TheObj.leafCount());
        System.out.println("");
        System.out.println("The height count is: " + TheObj.height());
        System.out.println("");
        System.out.println("The parent count is: " + TheObj.parentCount());
        System.out.println("");
        System.out.println("Ancestors of node 4 is: ");
        TheObj.ancestor(TheObj.Root, 4);
        System.out.println("");
        if (TheObj.isPerfect()){
            System.out.println("Tree is Perfect");

        }
        else {
            System.out.println("Tree is not perfect");
        }
    }

}
