package com.sap.trashproject.onlinestore.repository;

import com.sap.trashproject.onlinestore.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sap.trashproject.onlinestore.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ClientRepository implements IClientRepository {
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

    @Override
    public List<Client> findAll() {
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
        criteria.from(Client.class);
        return currentSession.createQuery(criteria).getResultList();
    }

    @Override
    public Long count() {
        CriteriaBuilder qb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Client.class)));
        return currentSession.createQuery(cq).getSingleResult();
    }

    @Override
    public void deleteById(Long clientId) {
        currentSession.delete(clientId);
    }

    @Override
    public void save(Client client) {
        currentSession.save(client);
    }
}
