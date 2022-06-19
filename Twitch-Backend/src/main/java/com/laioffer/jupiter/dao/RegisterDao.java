package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class RegisterDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean register(User user) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (PersistenceException | IllegalStateException ex) {
            // if hibernate throws this exception, it means the user already be register
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            if (session != null) session.close();
        }
        return true;
    }
}


/*
package com.laioffer.jupiter.dao;

import com.laioffer.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

*/
/*Repository contains the annotation of Component, which tells the Spting IoC to create the objects
* Repository is usually marked in data base using*//*

@Repository
public class RegisterDao {

    // we Autowie SessionFactory, use the object created by sessionFactory to revise the DB
    // This is provided by Hibernate (ORM).
    // sesstionFactory as a dependency, the Spring will create the sessionFactory instance,
    // and inject it to Dao. (The sessionFactory is registered as a Bean, the Spring will inject
    // it into Dao class's field.
    @Autowired
    private SessionFactory sessionFactory;

    public boolean register(User user){
        Session session = null;

        try{
            // create the session object
            session = sessionFactory.openSession();
            // has the ability to roll back
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (PersistenceException | IllegalStateException ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally{
            if(session != null){
                session.close();
            }
        }
        return true;
    }
}
*/
