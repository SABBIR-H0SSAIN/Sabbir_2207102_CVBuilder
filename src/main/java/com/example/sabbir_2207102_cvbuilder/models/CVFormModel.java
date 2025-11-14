package com.example.sabbir_2207102_cvbuilder.models;

public class CVFormModel {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String educationalQualifications;
    private String skills;
    private String workExperiances;
    private String projects;

    public CVFormModel(String fullName, String email, String phoneNumber, String address, String educationalQualifications, String skills, String workExperiances, String projects) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.educationalQualifications = educationalQualifications;
        this.skills = skills;
        this.workExperiances = workExperiances;
        this.projects = projects;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getEducationalQualifications() { return educationalQualifications; }
    public String getSkills() { return skills; }
    public String getWorkExperiances() { return workExperiances; }
    public String getProjects() { return projects; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setEducationalQualifications(String educationalQualifications) { this.educationalQualifications = educationalQualifications; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setWorkExperiances(String workExperiances) { this.workExperiances = workExperiances; }
    public void setProjects(String projects) { this.projects = projects; }
}
