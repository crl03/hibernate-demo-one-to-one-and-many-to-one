package com.hibernatedemo;

import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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

            // get instructorDetail object
            int theId = 5;
            InstructorDetail tempDetail =
                    (InstructorDetail) session.get(InstructorDetail.class, theId);

            // print instructorDetail
            System.out.println("Instructor detil: " + tempDetail);

            // print associate instructor
            System.out.println("The associated detail: " + tempDetail.getInstructor());

            // remove the associated object reference
            // break bi-directional link
            // setting the istructorDetail for the instructor object to null using its setters
            tempDetail.getInstructor().setInstructorDetail(null);


            // deleting instructorDetail, this will delete instructor because we chose CascadeType.ALL
            // changed CascadeType to include everything but delete, now instructor.get(instructorDetail) will be null
            // can verify this in SQL tables
            System.out.println("Deleting tempDetail: " + tempDetail);
            session.delete(tempDetail);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // handle connection leak issue
            //session.close();
            factory.close();
        }
    }
}