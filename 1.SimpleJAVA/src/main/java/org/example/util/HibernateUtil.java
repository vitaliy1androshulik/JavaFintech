package org.example.util;

import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    //повертає фабрику для підключення до БД
    private static SessionFactory sessionFactory;

    //метод, який визивається автоматично без потреби створення класу
    static {
        try {
            var config = new Configuration()
                    .configure();//
            config.addAnnotatedClass(Genres.class);
            config.addAnnotatedClass(Games.class);
            config.addAnnotatedClass(OrderItems.class);
            config.addAnnotatedClass(Orders.class);
            config.addAnnotatedClass(Payments.class);
            config.addAnnotatedClass(Users.class);

            sessionFactory = config.buildSessionFactory();
            System.out.println("-------Ми підключаємось до БД!------");
        }catch (Exception e)
        {
            System.out.println("Помилка підключення до БД!"+e.getMessage());
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shutdown() {
        if(sessionFactory!=null) {
            sessionFactory.close();
        }
    }
}
