package com.github.evgenylizogubov;

import java.util.NoSuchElementException;

public class CustomLinkedList {
    private Node head;
    
    private int size;
    
    public CustomLinkedList() {
        this.size = 0;
    }
    
    public void print() {
        if (!isEmpty()) {
            Node currentNode = head;
            System.out.print("[");
            
            while (currentNode.getNextNode() != null) {
                System.out.print(currentNode.getData() + ", ");
                currentNode = currentNode.getNextNode();
            }
            
            System.out.println(currentNode.getData() + "]");
        } else {
            System.out.println("[]");
        }
    }
    
    public void add(int data) {
        Node node = new Node(data);
        
        if (isEmpty()) {
            head = node;
        } else {
            Node currentNode = head;
            
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            
            currentNode.setNextNode(node);
        }
        
        size++;
    }
    
    public void add(int index, int data) {
        throwExceptionIfIndexOutOfListRange(index, size);
        
        Node newNode = new Node(data);
        if (index == 0) {
            newNode.setNextNode(head);
            head = newNode;
        } else {
            Node currentNode = head;
            
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            
            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
        }
        
        size++;
    }
    
    public void removeByIndex(int index) {
        throwExceptionIfIndexOutOfListRange(index, size - 1);
        
        if (index == 0) {
            head = head.getNextNode();
        } else {
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
        }
        
        size--;
    }
    
    public void remove(int data) {
        throwExceptionIfListIsEmpty();
        
        Node currentNode = head;
        if (currentNode.getData() == data) {
            removeFirst();
            return;
        }
        
        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getData() == data) {
                break;
            }
            
            currentNode = currentNode.getNextNode();
        }
        
        currentNode.setNextNode(currentNode.getNextNode().getNextNode());
        size--;
    }
    
    public void removeFirst() {
        throwExceptionIfListIsEmpty();
        
        head = head.getNextNode();
        size--;
    }
    
    public void removeLast() {
        throwExceptionIfListIsEmpty();
        
        if (head.getNextNode() == null) {
            head = null;
        } else {
            Node currentNode = head;
            while (currentNode.getNextNode().getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            
            currentNode.setNextNode(null);
        }
        
        size--;
    }
    
    public int get(int index) {
        throwExceptionIfIndexOutOfListRange(index, size - 1);
        
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        
        return currentNode.getData();
    }
    
    public int getFirst() {
        throwExceptionIfListIsEmpty();
        return head.getData();
    }
    
    public int getLast() {
        throwExceptionIfListIsEmpty();
        
        Node currentNode = head;
        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        
        return currentNode.getData();
    }
    
    public boolean contains(int data) {
        throwExceptionIfListIsEmpty();
        
        Node currentNode = head;
        while (currentNode.getData() != data && currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        
        return currentNode.getData() == data;
    }
    
    public int indexOf(int data) {
        throwExceptionIfListIsEmpty();
        
        int index = 0;
        Node currentNode = head;
        while (currentNode.getData() != data && currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            index++;
        }
        
        return currentNode.getData() == data ? index : -1;
    }
    
    public void set(int index, int data) {
        throwExceptionIfIndexOutOfListRange(index, size - 1);
        
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextNode();
        }
        
        currentNode.setData(data);
    }
    
    public int size() {
        return size;
    }
    
    public int[] toArray() {
        int[] array = new int[size];
        
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            array[i] = currentNode.getData();
            currentNode = currentNode.getNextNode();
        }
        
        return array;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        head = null;
        size = 0;
    }
    
    private void throwExceptionIfListIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
    }
    
    private void throwExceptionIfIndexOutOfListRange(int index, int end) {
        if (index < 0 || index > end) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
