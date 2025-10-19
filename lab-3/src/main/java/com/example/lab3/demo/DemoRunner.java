package com.example.lab3.demo;

import com.example.lab3.service.ServiceC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {

    private final ServiceC serviceC;

    public DemoRunner(ServiceC serviceC) {
        System.out.println("💉 DemoRunner: constructor injection");
        this.serviceC = serviceC;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Application started. Let's check dependency order:");
        serviceC.action();
    }
}

