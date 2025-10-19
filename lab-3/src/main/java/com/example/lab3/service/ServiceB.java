package com.example.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceB {

    @Autowired
    private ServiceA serviceA; // Field injection

    public ServiceB() {
        System.out.println("2️⃣ ServiceB: constructor called");
    }

    public void print() {
        System.out.println("   ServiceB: using " + serviceA.getName());
    }
}
