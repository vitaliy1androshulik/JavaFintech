package org.example;

import org.example.entities.Games;
import org.example.entities.Genres;
import org.example.util.HibernateUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        var Session = HibernateUtil.getSession();
        try{
            Session.beginTransaction();
            var genre = new Genres();
            genre.setName("Хоррор");

            var games = new Games();
            games.setTitle("Minecraft");
            games.setPrice(new BigDecimal("12.99"));
            Session.persist(genre);//новіший спосіб зберігання
            Session.persist(games);
            //Session.save(genre);
            Session.getTransaction().commit();
        }catch (Exception e)
        {
            System.out.println("Щось пішло не так!"+e.getMessage());
        }
    }
}
