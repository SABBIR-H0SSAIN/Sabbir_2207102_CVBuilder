package com.example.sabbir_2207102_cvbuilder.models;

import javafx.scene.image.Image;

public class CVFormModel {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String educationalQualifications;
    private String skills;
    private String workExperiances;
    private String projects;
    private String imagePath;

    public void setData(String fullName, String email, String phoneNumber, String address, String educationalQualifications, String skills, String workExperiances, String projects,String imagePath) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.educationalQualifications = educationalQualifications;
        this.skills = skills;
        this.workExperiances = workExperiances;
        this.projects = projects;
        this.imagePath = imagePath;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getEducationalQualifications() { return educationalQualifications; }
    public String getSkills() { return skills; }
    public String getWorkExperiances() { return workExperiances; }
    public String getProjects() { return projects; }
    public String getImagePath() { return imagePath; }
    public Image getImage() { return new Image(imagePath); }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setEducationalQualifications(String educationalQualifications) { this.educationalQualifications = educationalQualifications; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setWorkExperiances(String workExperiances) { this.workExperiances = workExperiances; }
    public void setProjects(String projects) { this.projects = projects; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
