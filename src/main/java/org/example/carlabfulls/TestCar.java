package org.example.carlabfulls;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCar implements CommandLineRunner {

    @Autowired
    private CarRepository repository;

    @Override
    public void run(String... args) {
        repository.save(new Car(1L,"Toyota", "Corolla", "White", 2020, "ABC-123", 15000));
        repository.save(new Car(2L,"Ford", "Focus", "Blue", 2019, "XYZ-999", 12000));
    }
}