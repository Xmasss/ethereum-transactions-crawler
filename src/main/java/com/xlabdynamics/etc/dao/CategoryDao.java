package com.xlabdynamics.etc.dao;

public class CategoryDao {
    private String typeFilter;
    private String type;
    public String getTypeFilter() {
        return typeFilter;
    }
    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public CategoryDao(String typeFilter, String type){
        this.typeFilter = typeFilter;
        this.type = type;
    }

}
