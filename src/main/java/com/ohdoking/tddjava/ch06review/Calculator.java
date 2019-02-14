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
    public int add(int i, int j) {
        int result = i + j;
        if(!db.save(result)){
            throw new RuntimeException();
        }
        return result;
    }

    public int getSaveData(){
        return db.getResult();
    }
}
