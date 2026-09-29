package br.com.alexduzi.binary_tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> collection = List.of(52, 17, 67, 11, 33, 55, 83, 14, 31, 46, 23, 26);
        MyCustomBinaryTree<Integer> myTree = new MyCustomBinaryTree<>(collection);
        System.out.println("myTree isEmpty: " + myTree.isEmpty());
        System.out.println("myTree getSize: " + myTree.getSize());
    }
}

class MyCustomBinaryTree<T extends Comparable<T>> {
    public Node<T> root;
    public int size;

    public MyCustomBinaryTree() {
        // nó sentinela
        this.root = new Node<>(null);
    }

    public MyCustomBinaryTree(Collection<T> collection) {
        Node<T> sentinel = new Node<>(null);
        root = sentinel;
        addAll(collection);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key value not provided");
        }

        if (this.isEmpty()) {
            root = new Node<>(key, null);
            root.setLeft(new Node<>(root));
            root.setRight(new Node<>(root));
            size++;
            return;
        }
        
        Node<T> node = findKeyLocation(root, key);
        if (node.isSentinel()) {
            Node<T> parent = node.getParent();
            Node<T> newNode = new Node<>(key, parent);
            newNode.setLeft(newNode);
            newNode.setRight(newNode);
            if (node == parent.getLeft()) {
                parent.setLeft(newNode);
            } else if (node == parent.getRight()) {
                parent.setRight(newNode);
            }
            size++;
        }
    }

    private Node<T> findKeyLocation(Node<T> node, T key) {
        while (!node.isSentinel()) {
            if (key == node.getKey()) {
                return node;
            } else if (node.isGreaterThan(key)) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
    }

    public void addAll(Collection<T> collection) {
        for (T value : collection) {
            add(value);
        }
    }

    // Keys retorna todas as chaves da árvore em ordem crescente (percurso em-ordem).
    public Collection<T> keys() {
        return collectKeys(root, new ArrayList<>());
    }

    // Contains retorna verdadeiro se a chave fornecida estiver presente na árvore.
    public boolean contains(T key) {
        Node<T> node = findKeyLocation(root, key);
        return !node.isSentinel();
    }

    public String stringFormat() {
        return stringFormatHelper(root, 0, new StringBuilder());
    }

    // Remove exclui a chave da árvore. Retorna verdadeiro se encontrada e removida, falso se não existir.
    public boolean remove(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key value not provided");
        }

        Node<T> nodeToRemove = findKeyLocation(root, key);

        if (nodeToRemove.isSentinel()) {
            return false;
        }

        if (!nodeToRemove.getLeft().isSentinel() && !nodeToRemove.getRight().isSentinel()) {
            Node<T> sucessor = findMin(nodeToRemove.getRight());
            nodeToRemove.setKey(sucessor.getKey());
            nodeToRemove = sucessor;
        }

        Node<T> child = null;
        if (nodeToRemove.getParent() == null) {
            root = child;
        } else if (nodeToRemove == nodeToRemove.getParent().getLeft()) {
            nodeToRemove.getParent().setLeft(child);
        } else {
            nodeToRemove.getParent().setRight(child);
        }
        size++;
        return true;
    }

    // union retorna uma nova árvore contendo todos os elementos de ambas as árvores (sem duplicatas).
    public MyCustomBinaryTree<T> union(MyCustomBinaryTree<T> other) {
        MyCustomBinaryTree<T> result = new MyCustomBinaryTree<>();
        for (T value : other.keys()) {
            result.add(value);
        }
        for (T value : this.keys()) {
            result.add(value);
        }
        return result;
    }

    // intersection retorna uma nova árvore contendo apenas os elementos presentes em ambas as árvores.
    public MyCustomBinaryTree<T> intersection(MyCustomBinaryTree<T> other) {
        MyCustomBinaryTree<T> result = new MyCustomBinaryTree<>();
        for (T value : this.keys()) {
            if (other.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    // difference retorna uma nova árvore com os elementos presentes em tree mas ausentes em other.
    public MyCustomBinaryTree<T> difference(MyCustomBinaryTree<T> other) {
        MyCustomBinaryTree<T> result = new MyCustomBinaryTree<>();
        for (T value : this.keys()) {
            if (!other.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    private Node<T> findMin(Node<T> node) {
        while (!node.isSentinel()) {
            node = node.getLeft(); // menor valor sempre a esquerda
        }
        return node;
    }

    // stringFormatHelper constrói recursivamente a representação textual da árvore com indentação por profundidade.
    private String stringFormatHelper(Node<T> node, int depth, StringBuilder sb) {
        if (!node.isSentinel()) {
            stringFormatHelper(node.getRight(), depth+1, sb);
            String spaces = "   ".repeat(depth);
            String parent = "";
            if (depth > 0) {
                parent = String.valueOf(node.getParent().getKey());
            }
            sb.append(String.format("%s(%s)%s\n", spaces, node.getKey(), parent));
            stringFormatHelper(node.getLeft(), depth+1, sb);
        }
        return sb.toString();
    }

    // collectKeys percorre a árvore em ordem (esquerda, raiz, direita) e acumula as chaves ordenadas.
    private Collection<T> collectKeys(Node<T> node, Collection<T> keys) {
        if (!node.isSentinel()) {
            keys = collectKeys(node.getLeft(), keys);
            keys.add(node.getKey());
            keys = collectKeys(node.getRight(), keys);
        }
        return keys;
    }
}

class Node<T extends Comparable<T>> {
    private T key;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private boolean sentinel = Boolean.FALSE;

    public Node(T key, Node<T> parent) {
        this.key = key;
        this.parent = parent;
    }

    public Node(Node<T> parent) {
        this.sentinel = true;
        this.parent = parent;
    }

    public T getKey() {
        return this.key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public boolean isSentinel() {
        return this.sentinel;
    }

    public boolean isGreaterThan(T other) {
        if (this.key == null || other == null) {
            throw new NullPointerException("Cannot compare null values");
        }
        return this.key.compareTo(other) > 0;
    }

    public boolean isLessThan(T other) {
        if (this.key == null || other == null) {
            throw new NullPointerException("Cannot compare null values");
        }
        return this.key.compareTo(other) < 0;
    }
}