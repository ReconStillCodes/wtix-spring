package com.wtix.wtix.model;

public abstract class ScreeningType {

    protected Integer id;
    protected String name;
    protected Double price;
    protected  Integer seats[][];

    public ScreeningType(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeat(Integer row, Integer col){
        return this.seats[row][col];
    }

    public void setSeat(Integer row, Integer col, Integer seat){
        this.seats[row][col] = seat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[][] getAllSeats(){
        return seats;
    }

    @Override
    public String toString() {
        return "ScreeningType{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
