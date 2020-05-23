package Meple;

public class Student {


    int id;
    String faculty;
    String last_name;
    String first_name;
    int av_grade;

    public Student() {
    }

    public Student(int id, String faculty, String last_name, String first_name, int av_grade) {
        this.id = id;
        this.faculty = faculty;
        this.last_name = last_name;
        this.first_name = first_name;
        this.av_grade = av_grade;
    }

    public Student(String faculty, String last_name, String first_name, int av_grade) {
        this.faculty = faculty;
        this.last_name = last_name;
        this.first_name = first_name;
        this.av_grade = av_grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getAv_grade() {
        return av_grade;
    }

    public void setAv_grade(int av_grade) {
        this.av_grade = av_grade;
    }
}
