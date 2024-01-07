package controlador.DAO;

import controlador.TDA.listas.DynamicList;

public interface DaoInterface<T> {
    public Integer persist(T data)throws Exception;
    public void merge(T data)throws Exception;
    public DynamicList<T> all();
    public T get(Integer id);
}