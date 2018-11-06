package com.rats.orm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;

public class SimpleRepository<T, ID> implements CurdRepository<T, ID> {

    private NamedParameterJdbcTemplate nameJdbcTemplate;
    private Class <T> clazz;

    @Autowired
    protected SimpleRepository(NamedParameterJdbcTemplate nameJdbcTemplate){
        Assert.notNull(nameJdbcTemplate, "nameJdbcTemplateentities not be null!");
        this.nameJdbcTemplate = nameJdbcTemplate;
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class)pt.getActualTypeArguments()[0];
    }

    @Override
    public long count() {


        return 0;
    }

    @Override
    public long count(Example example) {
        Assert.notNull(example, "The entity must not be null!");
        return 0;
    }

    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return false;
    }

    @Override
    public <S extends T> S insert(S entity) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> S insertSelective(S entity) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> Iterable<S> insertBatch(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return null;
    }

    @Override
    public <S extends T> Iterable<S> insertBatchSelective(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of not be null!");
        return null;
    }
    @Override
    public Optional<T> selectById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        return Optional.empty();
    }

    @Override
    public Iterable<T> selectAll() {

  /*      String sql = buildSql(clazz,Map<String, Object> params,Map<String, Object> conditions );
        nameJdbcTemplate.queryForList((String var1, SqlParameterSource var2,  clazz);*/
        return null;
    }

    @Override
    public Iterable<T> selectAllById(Iterable<ID> id) {
        return null;
    }

    @Override
    public <S extends T> S updateById(S entity,ID id) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> S updateByIdSelective(S entity,ID id) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> S update(S entity, Example example) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> S updateSelective(S entity, S example) {
        Assert.notNull(entity, "The entity must not be null!");
        return null;
    }

    @Override
    public <S extends T> Iterable<S> updateBatch(Iterable<S> entities, S example) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return null;
    }

    @Override
    public <S extends T> Iterable<S> updateBatchSelective(Iterable<S> entities, S example) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return null;
    }


    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
    }

    @Override
    public void deleteBatch(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
    }

    @Override
    public void deleteBatch() {

    }


}
