package com.project.Accommodator.model;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name="Posting")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String rent;
    private String address;
    private String description;
    private String date;
    private String category;
    private String image;
    private String type;
    private String pincode;
    private String email;
    private Integer ownerId;

//    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Favorite> favorites;

    public Posting() {
    }

    public Posting(Integer postId, String rent, String address, String description, String date, String category, String image, String type, String pincode, String email, Integer ownerId) {
        this.postId = postId;
        this.rent = rent;
        this.address = address;
        this.description = description;
        this.date = date;
        this.category = category;
        this.image = image;
        this.type = type;
        this.pincode = pincode;
        this.email = email;
        this.ownerId = ownerId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
