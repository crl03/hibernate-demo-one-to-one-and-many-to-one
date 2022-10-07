package com.hibernatedemo;

import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get instructor by id
            int theId = 3;
            Instructor tempInstructor =
                    (Instructor) session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + tempInstructor);

            // delete the instructor
            if (tempInstructor != null) {

                System.out.println("Deleting instructor: " + tempInstructor);

                // NOTE: this will also delete the InstructorDetails object because of the CascadeType.All
                session.delete(tempInstructor);
            }


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}