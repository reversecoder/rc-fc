package com.rc.facecase.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String id = "";
    private String category_name = "";
    private List<SubCategory> sub_categories = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<SubCategory> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(List<SubCategory> sub_categories) {
        this.sub_categories = sub_categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", category_name='" + category_name + '\'' +
                ", sub_categories=" + sub_categories +
                '}';
    }
}
