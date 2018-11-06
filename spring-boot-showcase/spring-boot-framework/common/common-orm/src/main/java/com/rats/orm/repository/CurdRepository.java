package com.rats.orm.repository;

import org.springframework.data.domain.Example;

import java.util.Optional;

public interface CurdRepository<T, ID> extends Repository<T, ID> {


    public long count();

    public long count(Example example);

    public boolean existsById(ID id);

    public <S extends T> S insert(S entity);

    public <S extends T> S insertSelective(S entity);

    public <S extends T> Iterable<S> insertBatch(Iterable<S> entities);

    public <S extends T> Iterable<S> insertBatchSelective(Iterable<S> entities);

    public Optional<T> selectById(ID id);

    public Iterable<T> selectAll();

    public Iterable<T> selectAllById(Iterable<ID> id);

    public <S extends T> S updateById(S entity, ID id);

    public <S extends T> S updateByIdSelective(S entity, ID id);

    public <S extends T> S update(S entity, Example example);

    public <S extends T> S updateSelective(S entity, S example);

    public <S extends T> Iterable<S> updateBatch(Iterable<S> entities, S example);

    public <S extends T> Iterable<S> updateBatchSelective(Iterable<S> entities, S example);

    public void deleteById(ID id);

    public void delete(T entity);

    public void deleteBatch(Iterable<? extends T> entities);

    public void deleteBatch();
}
