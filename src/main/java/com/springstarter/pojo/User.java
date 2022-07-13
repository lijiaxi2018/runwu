package com.springstarter.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * POPJ class that is used to load the document from database userdata.author
 */
@Document(collection = "author")
public class User {

    private int author_id;
    private String username;
    private String password;
    private String avatar_filename;
    private String organization;
    private Date date_created;
    private int works;
    private int favorites;
    private int following;
    private int follower;

    public User() {
    }

    public User(int author_id, String username, String password, String avatar_filename, String organization, Date date_created, int works, int favorites, int following, int follower) {
        this.author_id = author_id;
        this.username = username;
        this.password = password;
        this.avatar_filename = avatar_filename;
        this.organization = organization;
        this.date_created = date_created;
        this.works = works;
        this.favorites = favorites;
        this.following = following;
        this.follower = follower;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar_filename() {
        return avatar_filename;
    }

    public void setAvatar_filename(String avatar_filename) {
        this.avatar_filename = avatar_filename;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public int getWorks() {
        return works;
    }

    public void setWorks(int works) {
        this.works = works;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "User{" +
                "author_id=" + author_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar_filename='" + avatar_filename + '\'' +
                ", organization='" + organization + '\'' +
                ", date_created=" + date_created +
                ", works=" + works +
                ", favorites=" + favorites +
                ", following=" + following +
                ", follower=" + follower +
                '}';
    }
}
