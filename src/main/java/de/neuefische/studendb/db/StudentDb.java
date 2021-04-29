package de.neuefische.studendb.db;

import de.neuefische.studendb.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDb {

    private List<Student> students;

    public StudentDb(List<Student> students) {
        this.students = students;
    }
    public StudentDb(Student[] students) {
        this.students = new ArrayList<>();
        for(Student student : students){
            this.students.add(student);
        }
    }

    public List<Student> list() {
        return students;
    }

    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < students.size(); i++) {
            result += students.get(i) + "\n";
        }
        return result;
    }

    public Student randomStudent() {
        int index = (int) Math.floor(Math.random() * students.size());
        return students.get(index);
    }

    public void add(Student student) {
        students.add(student);
    }

    public void remove(Student student) {
        students.remove(student);
    }

    private int findIndex(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).equals(student)){
                return i;
            }
        }
        return -1;
    }


}
