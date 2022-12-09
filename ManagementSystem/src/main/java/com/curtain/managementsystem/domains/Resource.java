package com.curtain.managementsystem.domains;

import java.util.Date;

public class Resource {
    private String name,path;
    //type 1:图片 2:视频
    private int id,curtainId,size,type,deleted;
    private Date update_time;

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurtainId() {
        return curtainId;
    }

    public void setCurtainId(int curtainId) {
        this.curtainId = curtainId;
    }

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

    public Resource(String name, String path, int id, int curtainId, int size, int type, int deleted, Date update_time) {
        this.name = name;
        this.path = path;
        this.id = id;
        this.curtainId = curtainId;
        this.size = size;
        this.type = type;
        this.deleted = deleted;
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", id=" + id +
                ", curtainId=" + curtainId +
                ", size=" + size +
                ", type=" + type +
                ", deleted=" + deleted +
                ", update_time=" + update_time +
                '}';
    }
}
