package com.example.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceC {

    private ServiceB serviceB;

    public ServiceC() {
        System.out.println("3️⃣ ServiceC: constructor called");
    }

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        System.out.println("➡️ ServiceC: setter injection called");
        this.serviceB = serviceB;
    }

    @Autowired
    public void anotherMethod(ServiceA serviceA) {
        System.out.println("➡️ ServiceC: method injection called with " + serviceA.getName());
    }

    public void action() {
        System.out.println("   ServiceC: action() running...");
        serviceB.print();
    }
}

