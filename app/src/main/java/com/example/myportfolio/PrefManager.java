package com.example.myportfolio;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class PrefManager {

    private static final String PREF_NAME = "MyPortfolioPrefs";
    private final SharedPreferences prefs;

    // ===== KEYS =====
    private static final String KEY_NAME = "name";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ABOUT = "about";
    private static final String KEY_PROFILE_PHOTO = "profile_photo";

    private static final String KEY_GITHUB = "github";
    private static final String KEY_RESUME = "resume";

    private static final String KEY_CERTIFICATES = "certificates";
    private static final String KEY_SKILLS = "skills";

    public PrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // ================= PROFILE INFO =================
    public void setName(String name) {
        prefs.edit().putString(KEY_NAME, name).apply();
    }

    public String getName() {
        return prefs.getString(KEY_NAME, "Your Name");
    }

    public void setTitle(String title) {
        prefs.edit().putString(KEY_TITLE, title).apply();
    }

    public String getTitle() {
        return prefs.getString(KEY_TITLE, "Android Developer");
    }

    public void setAbout(String about) {
        prefs.edit().putString(KEY_ABOUT, about).apply();
    }

    public String getAbout() {
        return prefs.getString(KEY_ABOUT, "Write something about yourself");
    }

    // ================= PROFILE PHOTO =================
    public void setProfilePhoto(String uri) {
        prefs.edit().putString(KEY_PROFILE_PHOTO, uri).apply();
    }

    public String getProfilePhoto() {
        return prefs.getString(KEY_PROFILE_PHOTO, "");
    }

    // ================= QUICK LINKS =================
    public void setGithub(String github) {
        prefs.edit().putString(KEY_GITHUB, github).apply();
    }

    public String getGithub() {
        return prefs.getString(KEY_GITHUB, "");
    }

    public void setResume(String resume) {
        prefs.edit().putString(KEY_RESUME, resume).apply();
    }

    public String getResume() {
        return prefs.getString(KEY_RESUME, "");
    }

    // ================= CERTIFICATES =================

    public void saveCertificates(ArrayList<Certificate> list) {

        Gson gson = new Gson();

        String json = gson.toJson(list);

        prefs.edit()
                .putString(KEY_CERTIFICATES, json)
                .apply();
    }

    public ArrayList<Certificate> getCertificates() {

        Gson gson = new Gson();

        String json =
                prefs.getString(KEY_CERTIFICATES, null);

        Type type =
                new TypeToken<ArrayList<Certificate>>() {}.getType();

        ArrayList<Certificate> list =
                gson.fromJson(json, type);

        if (list == null) {
            return new ArrayList<>();
        }

        return list;
    }

    // ===== SKILLS =====
    public void saveSkills(List<Skill> skills) {
        Gson gson = new Gson();
        String json = gson.toJson(skills);
        prefs.edit().putString("skills_list", json).apply();
    }

    public List<Skill> getSkillsList() {
        Gson gson = new Gson();
        String json = prefs.getString("skills_list", null);

        Type type = new TypeToken<List<Skill>>() {}.getType();
        List<Skill> list = gson.fromJson(json, type);

        return list == null ? new ArrayList<>() : list;
    }

    // ================= GENERIC SECTION (OPTIONAL) =================
    public void setSection(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getSection(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }
    // ===== EDUCATION =====
    public void saveEducation(List<Education> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        prefs.edit().putString("education_list", json).apply();
    }

    public List<Education> getEducationList() {
        Gson gson = new Gson();
        String json = prefs.getString("education_list", null);

        Type type = new TypeToken<List<Education>>() {}.getType();
        List<Education> list = gson.fromJson(json, type);

        return list == null ? new ArrayList<>() : list;
    }
// CONTACT

    public void setContactEmail(String v) {
        prefs.edit().putString("email", v).apply();
    }

    public String getContactEmail() {
        return prefs.getString("email", "example@gmail.com");
    }

    public void setContactPhone(String v) {
        prefs.edit().putString("phone", v).apply();
    }

    public String getContactPhone() {
        return prefs.getString("phone", "+91XXXXXXXXXX");
    }


// ===== CONTACT =====

    public void setEmail(String email){
        prefs.edit().putString("contact_email", email).apply();
    }

    public String getEmail(){
        return prefs.getString("contact_email", "");
    }

    public void setPhone(String phone){
        prefs.edit().putString("contact_phone", phone).apply();
    }

    public String getPhone(){
        return prefs.getString("contact_phone", "");
    }

    public void setGithubContact(String github){
        prefs.edit().putString("contact_github", github).apply();
    }

    public String getGithubContact(){
        return prefs.getString("contact_github", "");
    }

    public void setLinkedin(String linkedin){
        prefs.edit().putString("contact_linkedin", linkedin).apply();
    }

    public String getLinkedin(){
        return prefs.getString("contact_linkedin", "");
    }

    // ================= EXPERIENCE =================

    public void saveExperience(ArrayList<Experience> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);

        prefs.edit().putString("experience_list", json).apply();
    }

    public ArrayList<Experience> getExperience() {

        Gson gson = new Gson();
        String json = prefs.getString("experience_list", null);

        Type type = new TypeToken<ArrayList<Experience>>() {}.getType();

        ArrayList<Experience> list =
                gson.fromJson(json, type);

        return list == null ? new ArrayList<>() : list;
    }
    // ================= PROJECTS =================

    public void saveProjects(ArrayList<Project> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);

        prefs.edit().putString("project_list", json).apply();
    }

    public ArrayList<Project> getProjects() {

        Gson gson = new Gson();
        String json = prefs.getString("project_list", null);

        Type type = new TypeToken<ArrayList<Project>>() {}.getType();

        ArrayList<Project> list =
                gson.fromJson(json, type);

        return list == null ? new ArrayList<>() : list;
    }



}
