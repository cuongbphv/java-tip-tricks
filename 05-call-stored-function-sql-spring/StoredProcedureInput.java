package com.example;

public class StoredProcedureInput<T> {

    private String paramName;
    private T paramValue;
    private Class<T> paramType;

    public StoredProcedureInput() {
    }

    public StoredProcedureInput(String paramName, T paramValue, Class<T> paramType) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public T getParamValue() {
        return paramValue;
    }

    public void setParamValue(T paramValue) {
        this.paramValue = paramValue;
    }

    public Class<T> getParamType() {
        return paramType;
    }

    public void setParamType(Class<T> paramType) {
        this.paramType = paramType;
    }
}
