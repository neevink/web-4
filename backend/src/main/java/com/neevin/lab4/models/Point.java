package com.neevin.lab4.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double x;
    private double y;
    private double r;
    private String income;
    private String userName;


    public Point() {}

    public Point(double x, double y, double r, String income, String userName) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.income = income;
        this.userName = userName;
    }

    @Override
    public String toString(){
        return "point{" + "x = " + x + ", y = " + y + ", r = " + r + "}";
    }

    static boolean checkHit(double x, double y, double r){
        if(x >= 0 && y >= 0){ // 1 четверть
            return 2*y + x <= r;
        }
        else if(x < 0 && y >= 0){ // 2 четверть
            return false;
        }
        else if(x < 0 && y < 0){ // 3 четверть
            return -x <= r && -y <= r/2;
        }
        else{ // 4 четверть
            return x*x + y*y <= r*r/4;
        }
    }
}
