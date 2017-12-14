package com.btc.app.dao;

import java.util.List;
import java.util.Map;

public abstract interface BaseMapper<T>
{
  public abstract Integer update(T paramT);

  public abstract Integer save(T paramT);

  public abstract Integer delete(T paramT);

  public abstract T getInfo(T paramT);

  public abstract List<T> getByPage(Map<String, Object> paramMap);
  
  public abstract List<T> getAll();

  public abstract List<T> getAll(T paramT);
}