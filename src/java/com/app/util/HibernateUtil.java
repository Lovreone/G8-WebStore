package com.app.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author grupa 1
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    
    public static SessionFactory getSessionFactory() {
        
        // Singleton Design Pattern
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            //configuration.configure("/com/app/util/hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
    
}
