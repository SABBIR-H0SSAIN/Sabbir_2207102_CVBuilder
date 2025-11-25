package com.example.sabbir_2207102_cvbuilder.db;

import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBHelper {
    private final DBConnector dbManager;

    public DBHelper() {
        this.dbManager = DBConnector.getInstance();
    }

    public int insertCV(CVFormModel cv) {
        String sql = """
            INSERT INTO cv_records 
            (full_name, email, phone_number, address, educational_qualifications, 
             skills, work_experiences, projects, image_path)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhoneNumber());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducationalQualifications());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWorkExperiances());
            pstmt.setString(8, cv.getProjects());
            pstmt.setString(9, cv.getImagePath());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        cv.setId(generatedId);
                        return generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateCV(CVFormModel cv) {
        String sql = """
            UPDATE cv_records 
            SET full_name = ?, email = ?, phone_number = ?, address = ?,
                educational_qualifications = ?, skills = ?, work_experiences = ?,
                projects = ?, image_path = ?, updated_at = CURRENT_TIMESTAMP
            WHERE id = ?
        """;

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhoneNumber());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getEducationalQualifications());
            pstmt.setString(6, cv.getSkills());
            pstmt.setString(7, cv.getWorkExperiances());
            pstmt.setString(8, cv.getProjects());
            pstmt.setString(9, cv.getImagePath());
            pstmt.setInt(10, cv.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCV(int id) {
        String sql = "DELETE FROM cv_records WHERE id = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<CVFormModel> findAllCVs() {
        ObservableList<CVFormModel> cvList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM cv_records ORDER BY updated_at DESC";

        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CVFormModel cv = new CVFormModel();
                cv.setId(rs.getInt("id"));
                cv.setFullName(rs.getString("full_name"));
                cv.setEmail(rs.getString("email"));
                cv.setPhoneNumber(rs.getString("phone_number"));
                cv.setAddress(rs.getString("address"));
                cv.setEducationalQualifications(rs.getString("educational_qualifications"));
                cv.setSkills(rs.getString("skills"));
                cv.setWorkExperiances(rs.getString("work_experiences"));
                cv.setProjects(rs.getString("projects"));
                cv.setImagePath(rs.getString("image_path"));

                cvList.add(cv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cvList;
    }

    public CVFormModel findCVById(int id) {
        String sql = "SELECT * FROM cv_records WHERE id = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CVFormModel cv = new CVFormModel();
                    cv.setId(rs.getInt("id"));
                    cv.setFullName(rs.getString("full_name"));
                    cv.setEmail(rs.getString("email"));
                    cv.setPhoneNumber(rs.getString("phone_number"));
                    cv.setAddress(rs.getString("address"));
                    cv.setEducationalQualifications(rs.getString("educational_qualifications"));
                    cv.setSkills(rs.getString("skills"));
                    cv.setWorkExperiances(rs.getString("work_experiences"));
                    cv.setProjects(rs.getString("projects"));
                    cv.setImagePath(rs.getString("image_path"));

                    return cv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
