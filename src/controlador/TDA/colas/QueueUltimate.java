package controlador.TDA.colas;

import controlador.TDA.listas.Exception.EmptyException;
import controlador.TDA.pilas.FullStackException;

public class QueueUltimate<E> {
    private Queue<E> tail;

    public QueueUltimate(Integer length) {
        this.tail = new Queue(length);
    }
    
    public void queue(E info) throws EmptyException, FullStackException{
        tail.queue(info);
    }
    
    public E dequeue() throws EmptyException{
        return tail.dequue();
    } 
    
    public Integer length(){
        return tail.getLength();
    }
    
    public Boolean isFull(){
        return tail.isFull();
    }
    
    public void print(){
        System.out.println("QUEUE");
        System.out.println(tail.toString());
        System.out.println("");
    }
}