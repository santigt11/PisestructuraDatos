package controlador.TDA.pilas;

import controlador.TDA.listas.Exception.EmptyException;

public class StackUltimate<E> {
    private Stack<E> stack;

    public StackUltimate(Integer length) {
        this.stack = new Stack(length);
    }
    
    public void push(E info) throws EmptyException, FullStackException{
        stack.push(info);
    }
    
    public E pop() throws EmptyException{
        return stack.pop();
    } 
    
    public Integer length(){
        return stack.getLength();
    }
    
    public Boolean isFull(){
        return stack.isFull();
    }
    
    public void print(){
        System.out.println("STACK");
        System.out.println(stack.toString());
        System.out.println("");
    }
}