package com.javarush.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.osgi.service.component.annotations.Component;
import org.springframework.context.annotation.Bean;

@Component
public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static ServiceRegistry serviceRegistry;

    private static Session session;

    public static void startTransaction() {
        session.getTransaction().begin();
    }

    public static void commitTransaction() {
        session.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        session.getTransaction().rollback();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession() {
        if (session == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Bean
    public static SessionFactory getSessionFactory() {
        if (session == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
        }
        return sessionFactory;
    }
 
    private static SessionFactory buildSessionFactory() {
        try {
            // Создает сессию с hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();

            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            serviceRegistry = serviceRegistryBuilder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static void shutdown() {
        // Чистит кеш и закрывает соединение с БД
        getSessionFactory().close();
    }
 
}