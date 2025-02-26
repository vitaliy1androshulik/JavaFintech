package org.example.seeder;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {


    private CategorySeeder categorySeeder;
    private ProductSeeder productsSeeder;

    @Override
    public void run(String... args) throws Exception {
        categorySeeder.seed();
        productsSeeder.seed();
    }
}
