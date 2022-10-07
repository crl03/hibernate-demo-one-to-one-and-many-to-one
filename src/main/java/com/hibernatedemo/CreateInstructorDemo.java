package com.hibernatedemo;

import com.hibernatedemo.entity.Course;
import com.hibernatedemo.entity.Instructor;
import com.hibernatedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
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
            // create objects
//            Instructor tempInstructor =
//                    new Instructor("Gandalf", "The Grey", "ggrey@gmail.com");
//            InstructorDetail tempInstructorDetail =
//                    new InstructorDetail("http://www.youtube.com/OGGrey", "Magic");
//            Instructor tempInstructor =
//                    new Instructor("Frodo", "Baggins", "fbaggins@gmail.com");
//            InstructorDetail tempInstructorDetail =
//                    new InstructorDetail("http://www.youtube.com/OGFbagg", "Hiding");
            Instructor tempInstructor =
                    new Instructor("Sandy", "Bottom", "sb@gmail.com");
            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.youtube.com/", "Texas");



            // associate objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();


            // save instructor
            // this will now save the tempInstructorDetils object because of CascadeType.All
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}