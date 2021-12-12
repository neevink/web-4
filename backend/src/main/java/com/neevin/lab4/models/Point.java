package com.neevin.lab4.models;

import javax.persistence.*;

@Entity
@Table(name="points")
public class Point {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private double x;
    private double y;
    private double r;
    private boolean hit;

    @JoinColumn(name = "users")
    @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    private User owner;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
