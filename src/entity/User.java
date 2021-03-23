/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hp
 */
public class User {

    private Integer userId;
    private String userNom;
    private String userPrenom;
    private String userBio;
    private Integer userAge;
    private Integer userTel;
    private String userEmail;
    private String userPassword;
    private String userType;

    public User(Integer userId, String userNom, String userPrenom, String userBio, Integer userAge, Integer userTel, String userEmail, String userPassword, String userType) {
        this.userId = userId;
        this.userNom = userNom;
        this.userPrenom = userPrenom;
        this.userBio = userBio;
        this.userAge = userAge;
        this.userTel = userTel;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserTel() {
        return userTel;
    }

    public void setUserTel(Integer userTel) {
        this.userTel = userTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userNom=" + userNom + ", userPrenom=" + userPrenom + ", userBio=" + userBio + ", userAge=" + userAge + ", userTel=" + userTel + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userType=" + userType + '}';
    }

}
