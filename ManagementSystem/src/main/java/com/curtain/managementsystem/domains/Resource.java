package com.curtain.managementsystem.domains;

import java.util.Date;

public class Resource {
    private String name,path;
    //type 1:图片 2:视频
    private int size,type;
    private Date update_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Resource() {
    }

    public Resource(String name, String path, int size, int type, Date update_time) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.type = type;
        this.update_time = update_time;
    }
}
