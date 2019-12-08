package com.sap.trashproject.onlinestore.util;

import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Product.class)
                    .configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
}
