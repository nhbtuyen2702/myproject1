package com.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

/*
INSERT INTO products (name, price) VALUES
('Dell XPS 13', 1200),
('MacBook Air', 1000),
('HP Spectre x360', 1300),
('Lenovo ThinkPad X1 Carbon', 1500),
('iPhone 13 Pro', 1200),
('Samsung Galaxy S21 Ultra', 1300),
('Google Pixel 6 Pro', 1000),
('OnePlus 9 Pro', 1100),
('Asus ROG Zephyrus G14', 1400),
('Microsoft Surface Laptop 4', 1500);
*/