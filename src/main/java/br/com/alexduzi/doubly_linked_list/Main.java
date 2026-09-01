package br.com.alexduzi.doubly_linked_list;

import com.sun.jdi.IntegerValue;

public class Main {
    public static void main(String[] args) {
        MyDoublyLinkedList doublyLinkedList = new MyDoublyLinkedList(null);
        doublyLinkedList.addAtEnd(1);
        doublyLinkedList.addAtEnd(2);
        doublyLinkedList.addAtEnd(3);
        doublyLinkedList.addAtEnd(4);
        doublyLinkedList.addAtEnd(5);

        doublyLinkedList.printList();

        doublyLinkedList.reverse();

        doublyLinkedList.printList();
    }
}

class Node {
    public Integer value;
    public Node next;
    public Node prev;
    public Node(Integer value) {
        this.value = value;
    }
}

class MyDoublyLinkedList {
    public Node head;
    public Node tail;
    public Integer size;
    public MyDoublyLinkedList(Integer size) {
        if (size != null) {
            this.size = size;
        } else {
            this.size = 15;
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public Integer getSize() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void printList() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    public void addAtStart(Integer value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            size++;
            return;
        }

        Node aux = this.head;
        this.head = newNode;
        newNode.next = aux;
        this.size++;
    }

    public void addAtEnd(Integer value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            size++;
            return;
        }

        newNode.prev = this.tail;
        this.tail.next = newNode;
        this.tail = newNode;
        size++;
    }

    public int[] toArray() {
        int[] arr = new int[this.size];
        Node curr = this.head;
        int i = 0;
        while (curr  != null) {
            arr[i] = curr.value;
            curr = curr.next;
            i++;
        }
        return arr;
    }

    public Node getNode(int index) {
        if (index < 0 && index > this.size) {
            return null;
        }

        Node curr = this.head;
        int i = 0;
        while (curr != null && i != index) {
            curr = curr.next;
            i++;
        }

        return curr;
    }

    public void addAtPosition(Integer value, int index) {
        if (index == 0) {
            this.addAtStart(value);
            return;
        }

        if (this.getNode(index) == null && index == this.size) {
            this.addAtStart(value);
            return;
        }

        Node newNode = new Node(value);
        Node curr = this.getNode(index - 1);

        curr.next.prev = newNode;
        newNode.next = curr.next;
        newNode.prev = curr;
        curr.next = newNode;
        size++;
    }

    public int indexOf(Integer value) {
        Node curr = this.head;
        int i = 0;
        while (curr != null) {
            if (curr.value.compareTo(value) == 0) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return  -1;
    }

    public Node removeFirst() {
        Node aux = this.head;
        this.tail = this.tail.prev;

        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.prev = null;
        }
        this.size--;
        return aux;
    }

    public Node removeLast() {
        Node aux = this.head;
        this.tail = this.tail.prev;

        if (this.tail == null) {
            this.head = null;
        } else {
            this.tail.next = null;
        }
        this.size--;
        return aux;
    }

    public Integer removeAtPosition(int index) {
        if (isEmpty() || getNode(index) == null) {
            return -1;
        }

        if (index == 0) {
            return removeFirst().value;
        }

        if (index == getSize() - 1) {
            return removeLast().value;
        }

        Node curr = getNode(index);
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        return curr.value;
    }

    public boolean remove(Integer value) {
        int index = indexOf(value);
        if (isEmpty() || index == -1) {
            return false;
        }
        removeAtPosition(index);
        return true;
    }

    public void reverse() {
        Node hCopy = head;
        Node tCopy = tail;

        head = tCopy;
        tail = hCopy;

        Node curr = hCopy;
        Node aux;

        while (curr != null) {
            aux = curr.next;
            curr.next = curr.prev;
            curr.prev = aux;
            curr = aux;
        }
    }
}