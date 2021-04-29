package de.neuefische.studendb.db;

import de.neuefische.studendb.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDb {

    private Map<String, Student> students;

    public StudentDb(){
        this.students = new HashMap<>();
    }

    public StudentDb(List<Student> list) {
        Map<String, Student> studentMap = new HashMap<String, Student>();
        for(Student student : list){
            studentMap.put(student.getId(), student);
        }
        this.students = studentMap;
    }

    public StudentDb(Student[] list) {
        Map<String, Student> studentMap = new HashMap<>();
        for(Student student : list){
            studentMap.put(student.getId(), student);
        }
        this.students = studentMap;
    }


    public List<Student> list() {
        return List.copyOf(students.values());
    }

    @Override
    public String toString() {
        return "StudentDb{" +
                "students=" + students +
                '}';
    }

    public Student randomStudent() {
        int index = (int) Math.floor(Math.random() * students.size());
        return students.get(index);
    }

    public void add(Student student) {
        students.put(student.getId(), student);
    }

    public void remove(Student student) {
        students.remove(student.getId());
    }

    private int findIndex(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).equals(student)){
                return i;
            }
        }
        return -1;
    }

    public Student findFirstById(String id){
        if(id == null) {
            return null;
        }
        if(students.containsKey(id)){
            return students.get(id);
        }
        return null;
    }

    public void removeById(String id){
        if(id == null){
            return;
        }
        if(students.containsKey(id)){
            students.remove(id);
        }
    }
}
