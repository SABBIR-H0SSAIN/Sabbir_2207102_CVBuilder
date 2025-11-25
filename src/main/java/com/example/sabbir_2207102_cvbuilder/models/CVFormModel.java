package com.example.sabbir_2207102_cvbuilder.models;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class CVFormModel {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final StringProperty fullName = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty phoneNumber = new SimpleStringProperty("");
    private final StringProperty address = new SimpleStringProperty("");
    private final StringProperty educationalQualifications = new SimpleStringProperty("");
    private final StringProperty skills = new SimpleStringProperty("");
    private final StringProperty workExperiances = new SimpleStringProperty("");
    private final StringProperty projects = new SimpleStringProperty("");
    private final StringProperty imagePath = new SimpleStringProperty("");

    public CVFormModel() {}

    public void setData(String fullName, String email, String phoneNumber, String address,
                        String educationalQualifications, String skills, String workExperiances,
                        String projects, String imagePath) {
        this.fullName.set(fullName);
        this.email.set(email);
        this.phoneNumber.set(phoneNumber);
        this.address.set(address);
        this.educationalQualifications.set(educationalQualifications);
        this.skills.set(skills);
        this.workExperiances.set(workExperiances);
        this.projects.set(projects);
        this.imagePath.set(imagePath);
    }

    public IntegerProperty idProperty() { return id; }
    public StringProperty fullNameProperty() { return fullName; }
    public StringProperty emailProperty() { return email; }
    public StringProperty phoneNumberProperty() { return phoneNumber; }
    public StringProperty addressProperty() { return address; }
    public StringProperty educationalQualificationsProperty() { return educationalQualifications; }
    public StringProperty skillsProperty() { return skills; }
    public StringProperty workExperiancesProperty() { return workExperiances; }
    public StringProperty projectsProperty() { return projects; }
    public StringProperty imagePathProperty() { return imagePath; }

    public int getId() { return id.get(); }
    public String getFullName() { return fullName.get(); }
    public String getEmail() { return email.get(); }
    public String getPhoneNumber() { return phoneNumber.get(); }
    public String getAddress() { return address.get(); }
    public String getEducationalQualifications() { return educationalQualifications.get(); }
    public String getSkills() { return skills.get(); }
    public String getWorkExperiances() { return workExperiances.get(); }
    public String getProjects() { return projects.get(); }
    public String getImagePath() { return imagePath.get(); }

    public Image getImage() {
        String path = imagePath.get();
        return (path != null && !path.isEmpty()) ? new Image(path) : null;
    }

    public void setId(int id) { this.id.set(id); }
    public void setFullName(String fullName) { this.fullName.set(fullName); }
    public void setEmail(String email) { this.email.set(email); }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }
    public void setAddress(String address) { this.address.set(address); }
    public void setEducationalQualifications(String educationalQualifications) {
        this.educationalQualifications.set(educationalQualifications);
    }
    public void setSkills(String skills) { this.skills.set(skills); }
    public void setWorkExperiances(String workExperiances) { this.workExperiances.set(workExperiances); }
    public void setProjects(String projects) { this.projects.set(projects); }
    public void setImagePath(String imagePath) { this.imagePath.set(imagePath); }

    @Override
    public String toString() {
        return String.format("CV #%d: %s - %s", getId(), getFullName(), getEmail());
    }
}