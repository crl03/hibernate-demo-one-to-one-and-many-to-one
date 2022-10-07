package com.hibernatedemo;

import com.hibernatedemo.entity.Course;
import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstrcutorCoursesDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get instructor from db
            int theId = 1;
            Instructor tempInstructor = (Instructor) session.get(Instructor.class, theId);

            // retrieve instrcutor's courses
            System.out.println("Instructor Name: " + tempInstructor.getFirstName() + " " + tempInstructor.getLastName());
            System.out.println("\tCourses: " + tempInstructor.getCourses());


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}