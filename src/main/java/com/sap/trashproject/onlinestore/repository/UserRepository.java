package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sap.trashproject.onlinestore.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
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

    public List<User> findAll() {
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return currentSession.createQuery(criteria).getResultList();
    }

    public Long count() {
        CriteriaBuilder qb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(User.class)));
        return currentSession.createQuery(cq).getSingleResult();
    }

    public Optional<User> findByUsername(String username) {
        return currentSession.createQuery("from users where username= :username", User.class)
                .setParameter("username", username)
                .uniqueResultOptional();
    }

    public void delete(User user) {
        currentSession.delete(user);
    }


    public User get(Long userId) {
        return currentSession.load(User.class, userId);
    }


    public void save(User user) {
        currentSession.save(user);
    }
}
