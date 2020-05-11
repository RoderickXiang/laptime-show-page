package com.roderick.pojo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    private int id;
    private String model_name;
    private Date lap_time;
    private String lap_time_str;
    private Date insert_time;
    private String introduction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public Date getLap_time() {
        return lap_time;
    }

    public void setLap_time(Date lap_time) {
        this.lap_time = lap_time;
    }

    public String getLap_time_str() {
        if (lap_time != null) {
            lap_time_str = new SimpleDateFormat("mm:ss.SSS").format(lap_time);
        }
        return lap_time_str;
    }

    public void setLap_time_str(String lap_time_str) {
        this.lap_time_str = lap_time_str;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model_name='" + model_name + '\'' +
                ", lap_time=" + lap_time +
                ", lap_time_str='" + lap_time_str + '\'' +
                ", insert_time=" + insert_time +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
