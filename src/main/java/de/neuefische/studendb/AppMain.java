package de.neuefische.studendb;

import de.neuefische.studendb.db.StudentDb;
import de.neuefische.studendb.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
             students.add(new Student("Jane", "42"));
             students.add(new Student("Klaus", "13"));
             students.add(new Student("Franky", "100"));


        StudentDb studentDb = new StudentDb(students);

        for(int i=0; i < 5; i++){
            System.out.println(studentDb.randomStudent());
        }

    }

}
