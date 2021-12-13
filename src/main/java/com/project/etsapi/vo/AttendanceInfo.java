package com.project.etsapi.vo;

public class AttendanceInfo {
    private String start_time;
    private String end_time;
    private Integer number;
    private Integer total;

    public AttendanceInfo(){

    }

    public String getEnd_time() {
        return end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getTotal() {
        return total;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
