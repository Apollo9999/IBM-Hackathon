package com.happyspace.earthquake;

/**
 * Play with inheritance.
 */
public class PersonFun {

    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
            System.out.print("#1");

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void method1(){
            System.out.print("Person 1");
        }

        public void method2(){
            System.out.print("Person 2");
        }
    }

    public static class Student extends Person {
        private int id;

        public Student(){
            this("Student", 1234);
            System.out.print("#2 ");
        }

        public Student(String name, int id) {
            super(name);
            this.id = id;
            System.out.print("#3");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void method1() {
            System.out.print("Student 1 ");
            super.method1();
            method2();
        }

        @Override
        public void method2() {
            System.out.print("Student 2 ");
        }
    }

    public static class Undergrad extends Student {
        public void method2() {
            System.out.print("Undergrad 2 ");
        }
    }

    public static class Faculty extends Person{
        private String id;

        public Faculty(String name, String id) {
            super(name);
            this.id = id;
            System.out.print("#3");
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


    public static void main(String[] args) {
        Student student = new Student();

        /**
        Person person = new Person("moo");
        Person person1 = new Person("moo");
        Faculty faculty = new Faculty("moo", "moo");
        Object o = new Faculty("moo", "moo");

        String name = student.getName();
        person = student;
        // int id = person.getID();
        // faculty = person1;
        o = student;
        **/


        Person u = new Undergrad();
        u.method1();



    }
}
