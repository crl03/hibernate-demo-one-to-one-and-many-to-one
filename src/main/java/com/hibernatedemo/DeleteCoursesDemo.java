package com.hibernatedemo;

import com.hibernatedemo.entity.Course;
import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {
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

            // get course
            int theId = 1;
            Course tempCourse = (Course) session.get(Course.class, theId);

            // delete the course
            System.out.println("Deleting: " + tempCourse);
            session.delete(tempCourse);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}