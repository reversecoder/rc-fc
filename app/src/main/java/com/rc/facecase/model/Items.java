package com.rc.facecase.model;


public class Items {
    private String id = "";
    private String sub_category_id = "";
    private String source = "";
    private String title = "";
    private String prio = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(String sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id='" + id + '\'' +
                ", sub_category_id='" + sub_category_id + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", prio='" + prio + '\'' +
                '}';
    }
}
