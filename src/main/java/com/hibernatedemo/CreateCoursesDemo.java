package com.hibernatedemo;

import com.hibernatedemo.entity.Course;
import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            // create some courses
            Course tempCourse1 = new Course("Magic 101");
            Course tempCourse2 = new Course("How to Stop Those Who Try to Pass");

            // add courses to instructor
            // we have this .add method because we defined an add method for convenience in the
            //      Instructor class
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}