package services;

import exceptions.NoMorePlaceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public abstract class Service<T> {
    public abstract void add(T t) throws IOException, ParseException, NoMorePlaceException;
    public abstract void update(T t) throws IOException;
    public abstract void delete(int id)throws IOException;
    public abstract List<T> findAll() throws IOException, ParseException;
    public abstract T findByID(int id)throws IOException, ParseException;


}
