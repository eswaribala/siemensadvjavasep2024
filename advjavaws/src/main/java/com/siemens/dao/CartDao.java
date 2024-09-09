package com.siemens.dao;

import com.github.javafaker.Faker;

public interface CartDao {

    static int generateOTP(){
        return new Faker().random().nextInt(1000,9999);
    }
}
