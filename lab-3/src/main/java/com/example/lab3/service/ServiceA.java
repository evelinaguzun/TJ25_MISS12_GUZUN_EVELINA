package com.example.lab3.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {
    public ServiceA() {
        System.out.println("1️⃣ ServiceA: constructor called (created first)");
    }

    public String getName() {
        return "ServiceA";
    }
}
