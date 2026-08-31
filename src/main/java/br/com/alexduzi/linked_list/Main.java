package br.com.alexduzi.linked_list;

public class Main {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList(null);
        myLinkedList.addAtStart("1");
        myLinkedList.addAtStart("2");
        myLinkedList.addAtStart("3");
        myLinkedList.addAtStart("4");
        myLinkedList.addAtStart("5");
        myLinkedList.printLinkedList();
    }
}

class Node {
    public String data;
    public Node next;
    public Node(String data) {
        this.data = data;
    }
}

class MyLinkedList {
    public Node head;
    public Integer size;

    public MyLinkedList(Integer size) {
        if (size != null) {
            this.size = size;
        } else {
            this.size = 10;
        }
    }

    public Integer getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void printLinkedList() {
        if (isEmpty()) {
            System.out.println("LinkedList is empty, nothing to print!");
        }

        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public void addAtStart(String data) {
        // cria um novo nó e verifica se a lista está vazia
        // para inserir o novo nó no head
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            size++;
            return;
        }

        // caso não esteja vazia
        // cria uma variável auxiliar
        // o novo nó se torna o head
        // e o next do novo nó vira a auxiliar
        // incrementa o tamanho no final
        Node aux = head;
        head = newNode;
        newNode.next = aux;
        size++;
    }

    public void addAtEnd(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            size++;
            return;
        }

        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
        size++;
    }

    public Node getNode(Integer index) {
        // verifica se o index não está fora do tamanho da lista
        if (index < 0 && index > size) {
            return null;
        }

        Node curr = head;
        int i = 0;

        // faz o loop pela lista até achar a posição
        while (i != index) {
            curr = curr.next;
            i++;
        }

        if (curr != null) {
            return curr;
        }

        return null;
    }

    public void addAtPosition(String data, int index) {
        if (index < 0 && index > size) {
            return;
        }

        // adiciona no início
        if (index == 0) {
            addAtStart(data);
            return;
        }

        // adiciona no final
        if (getNode(index) == null) {
            addAtEnd(data);
        }

        Node newNode = new Node(data);
        Node aux = getNode(index - 1);
        newNode.next = aux.next;
        aux.next = newNode;
        size++;
    }

    public int indexOf(String data) {
        if (isEmpty()) {
            return -1;
        }

        Node curr = head;
        int i = 0;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return -1;
    }

    public boolean contains(String data) {
        return indexOf(data) != -1;
    }

    public String removeAtPosition(Integer index) {
        if (isEmpty() || getNode(index) == null) {
            return "";
        }

        String data;

        // caso esteja removendo no início
        if (index == 0) {
            Node aux = head;
            data = aux.data;
            head = aux.next;
            size--;
            return data;
        }

        // caso esteja removendo no final
        if (index == getSize() - 1) {
            Node aux = getNode(index - 1);
            data = aux.next.data;
            aux.next = null;
            size--;
            return data;
        }

        // removendo no meio da lista
        Node aux = getNode(index - 1);
        data = aux.next.data;
        aux.next = aux.next.next; // o prox do prox será o prox
        size--;
        return data;
    }

    public boolean removeElement(String data) {
        int index = indexOf(data);
        if (isEmpty() || index == -1) {
            return false;
        }
        removeAtPosition(index);
        return true;
    }
}