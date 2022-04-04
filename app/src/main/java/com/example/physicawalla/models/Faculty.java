package com.example.physicawalla.models;

import java.util.List;

public class Faculty {

    int id;
    String name, subject, qualification;
    List<String> subjects,qualifications;
    String url;


    public Faculty(){

    }


    public Faculty(int id, String name, List<String> subjects, List<String> qualifications, String url) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.subject = subjects.get(0);
        this.qualifications = qualifications;
        this.qualification = qualifications.get(0);
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getQualification() {
        return qualification;
    }
}
