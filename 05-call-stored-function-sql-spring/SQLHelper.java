package com.example;

import com.fis.ib.dto.helper.StoredProcedureInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SQLHelper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public <E, T> E callFunction(String fnName, Map<String, ?> params, Class<T> type) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName(fnName);
        SqlParameterSource paramMap = new MapSqlParameterSource().addValues(params);
        return (E) jdbcCall.executeFunction(type, paramMap);
    }

    @SuppressWarnings("unchecked")
    public <E> E callStoredProcedureWithSingleOutput(String spName, List<StoredProcedureInput<?>> inParams, String outParam, Class<?> outType) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spName);
        for (StoredProcedureInput<?> input : inParams) {
            storedProcedure.registerStoredProcedureParameter(input.getParamName(), input.getParamType(), ParameterMode.IN);
            storedProcedure.setParameter(input.getParamName(), input.getParamValue());
        }
        storedProcedure.registerStoredProcedureParameter(outParam, outType, ParameterMode.OUT);
        storedProcedure.execute();
        return (E) storedProcedure.getOutputParameterValue(outParam);
    }

    @SuppressWarnings("unchecked")
    public <E, T> E callStoredProcedureWithMultipleOutput(String spName, List<StoredProcedureInput<T>> inParams,
                                                        Map<String, Class<T>> outParams) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(spName);
        for (StoredProcedureInput<T> input : inParams) {
            storedProcedure.registerStoredProcedureParameter(input.getParamName(), input.getParamType(), ParameterMode.IN);
            storedProcedure.setParameter(input.getParamName(), input.getParamValue());
        }
        for (Map.Entry<String, Class<T>> entry : outParams.entrySet()) {
            storedProcedure.registerStoredProcedureParameter(entry.getKey(), entry.getValue(), ParameterMode.OUT);
        }
        storedProcedure.execute();
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Class<T>> entry : outParams.entrySet()) {
            result.put(entry.getKey(), storedProcedure.getOutputParameterValue(entry.getKey()));
        }
        return (E)result;
    }

}
