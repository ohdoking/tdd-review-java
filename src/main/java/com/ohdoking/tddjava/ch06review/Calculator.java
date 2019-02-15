package com.ohdoking.tddjava.ch06review;

import com.mongodb.DB;

public class Calculator {
    CustomDB db;

    public Calculator(CustomDB db){
        this.db = db;
    }

    public Calculator(){
        this(new CustomDB());
    }
    public Calculator add(int i, int j) {
        int result = i + j;
        if(!db.save(result)){
            throw new RuntimeException();
        }
        return this;
    }

    public Calculator add(int i){
        int result = getSaveData() + i;
        db.save(result);
        return this;
    }

    public int getSaveData(){
        return db.getResult();
    }

    public Calculator subtract(int i, int j) {
        int result = i - j;
        if(!db.save(result)){
            throw new RuntimeException();
        }
        return this;
    }
}
