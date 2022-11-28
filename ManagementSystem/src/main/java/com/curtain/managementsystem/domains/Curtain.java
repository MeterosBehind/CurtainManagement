package com.curtain.managementsystem.domains;

import java.util.Date;
import java.util.List;

public class Curtain {
    private int id;
    private String name,type,texture,introduction;
    private double price;
    private Date market_time,created_time,last_updated_time;
    private List<Resource> resList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getMarket_time() {
        return market_time;
    }

    public void setMarket_time(Date market_time) {
        this.market_time = market_time;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getLast_updated_time() {
        return last_updated_time;
    }

    public void setLast_updated_time(Date last_updated_time) {
        this.last_updated_time = last_updated_time;
    }

    public List<Resource> getResList() {
        return resList;
    }

    public void setResList(List<Resource> resList) {
        this.resList = resList;
    }

    public Curtain() {
    }

    public Curtain(int id, String name, String type, String texture, String introduction, double price, Date market_time, Date created_time, Date last_updated_time, List<Resource> resList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.texture = texture;
        this.introduction = introduction;
        this.price = price;
        this.market_time = market_time;
        this.created_time = created_time;
        this.last_updated_time = last_updated_time;
        this.resList = resList;
    }

    @Override
    public String toString() {
        return "Curtain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", texture='" + texture + '\'' +
                ", introduction='" + introduction + '\'' +
                ", price=" + price +
                ", market_time=" + market_time +
                ", created_time=" + created_time +
                ", last_updated_time=" + last_updated_time +
                ", resList=" + resList +
                '}';
    }
}
