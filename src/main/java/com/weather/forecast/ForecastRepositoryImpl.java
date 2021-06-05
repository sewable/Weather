package com.weather.forecast;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ForecastRepositoryImpl implements ForecastRepository {

    private SessionFactory sessionFactory;

    public ForecastRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Forecast save(Forecast forecast) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(forecast);

        transaction.commit();
        session.close();

        return forecast;
    }
}
