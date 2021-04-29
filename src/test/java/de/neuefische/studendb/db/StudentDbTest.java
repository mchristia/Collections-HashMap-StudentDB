package de.neuefische.studendb.db;

import de.neuefische.studendb.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDbTest {

    private static Arguments[] provideTestAddArguments() {
        return new Arguments[]{
                Arguments.of(
                        new ArrayList<Student>(List.of(
                                new Student("Student 1", "1"),
                                new Student("Student 2", "2")
                        ))
                        ,
                        new ArrayList<Student>(List.of(
                                new Student("Student 1", "1"),
                                new Student("Student 2", "2"),
                                new Student("Jane", "42")
                        ))
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of()),
                        new ArrayList<Student>(List.of(new Student("Jane", "42")))
                )
        };
    }

    private static Arguments[] provideTestRemoveArguments() {
        return new Arguments[]{
                Arguments.of(
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Jane", "42"),
                                new Student("Peter", "23")
                        ))

                        ,
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                        ,
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of(
                                new Student("Jane", "42"),
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                        ,
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23"),
                                new Student("Jane", "42")
                        ))
                        ,
                        new ArrayList<Student>(List.of(
                                new Student("Hans", "12"),
                                new Student("Peter", "23")
                        ))
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of()),
                        new ArrayList<Student>(List.of())
                ),
                Arguments.of(
                        new ArrayList<Student>(List.of(new Student("Jane", "42"))),
                        new ArrayList<Student>(List.of())
                )
        };
    }

    @Test
    @DisplayName("list() returns all students in the db")
    public void testList() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jane", "42"));
        students.add(new Student("Klaus", "13"));
        students.add(new Student("Franky", "100"));

        StudentDb studentDb = new StudentDb(students);

        // When
        List<Student> actual = studentDb.list();

        // Then
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Jane", "42"));
        expected.add(new Student("Klaus", "13"));
        expected.add(new Student("Franky", "100"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toString() returns a formatted list of all students")
    public void testToString() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jane", "42"));
        students.add(new Student("Klaus", "13"));
        students.add(new Student("Franky", "100"));

        StudentDb studentDb = new StudentDb(students);

        // When
        String actual = studentDb.toString();

        // Then
        String expected = "Student{name='Jane', id='42'}\n"
                + "Student{name='Klaus', id='13'}\n"
                + "Student{name='Franky', id='100'}\n";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideTestAddArguments")
    public void testAdd(ArrayList<Student> givenStudents, ArrayList<Student> expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);
        Student student = new Student("Jane", "42");

        // When
        studentDb.add(student);
        List<Student> actualStudents = studentDb.list();

        // Then
        assertEquals(expectedStudents, actualStudents);
    }

    @ParameterizedTest
    @MethodSource("provideTestRemoveArguments")
    public void testRemove(ArrayList<Student> givenStudents, ArrayList<Student> expectedStudents) {
        // Given
        StudentDb studentDb = new StudentDb(givenStudents);

        // When
        studentDb.remove(new Student("Jane", "42"));
        List<Student> actualStudents = studentDb.list();

        // Then
        assertEquals(expectedStudents, actualStudents);
    }
}