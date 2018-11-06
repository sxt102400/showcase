package com.rats.orm.repository;

import com.rats.orm.data.DataRow;
import com.rats.orm.data.DataSet;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataRepository<T, ID> extends SimpleRepository<T, ID> {

    private DataRepository(NamedParameterJdbcTemplate nameJdbcTemplate) {
        super(nameJdbcTemplate);
    }
}
