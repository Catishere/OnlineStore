package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.Product;
import com.sap.trashproject.onlinestore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public List<Product> findAll() {
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        criteria.from(Product.class);
        return currentSession.createQuery(criteria).getResultList();
    }

    public Long count() {
        CriteriaBuilder qb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Product.class)));
        return currentSession.createQuery(cq).getSingleResult();
    }

    public Optional<Product> findByProductName(String productName) {
        return currentSession.createQuery("from products where username= :name", Product.class)
                .setParameter("name", productName)
                .uniqueResultOptional();
    }

    public void delete(Product product) {
        currentSession.delete(product);
    }


    public Product get(Long productId) {
        return currentSession.get(Product.class, productId);
    }


    public void save(Product product) {
        currentSession.save(product);
    }
}
