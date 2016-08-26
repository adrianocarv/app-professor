package br.unifesp.ict.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.googlecode.objectify.Key;

public class BaseDAO<T> {
  
  private final Class<T> persistentClass;
  
  @SuppressWarnings("unchecked")
  public BaseDAO() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }
  
  public T save(T t) {
    ofy().save().entity(t).now();
    return t;
  }

  public List<T> getAll() {
    return ofy().load().type(persistentClass).list();
  }

  public Boolean remove(T t) {
    ofy().delete().entity(t).now();
    return true;
  }

  public T findById(Long id) {
    Key<T> k = Key.create(persistentClass, id);
    return ofy().load().key(k).get();
  }
}
