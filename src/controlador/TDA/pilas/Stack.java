
package controlador.TDA.pilas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;

class Stack<E> extends DynamicList<E>{
    //revisar como hacer una pila infinita
    private Integer tope;

    public Stack(Integer tope) {
        this.tope = tope;
    }
    
    public Boolean isFull(){
        return getLength().intValue() >= tope.intValue();
    }
    
    public void push(E info)throws EmptyException, FullStackException{
        if (isFull()) {
            throw new FullStackException("Stack Full");
        }else{
            add(info, 0);
        }
    }
    
    public E pop() throws EmptyException{
        E info = extractFirst();
        return info;
    }
}
