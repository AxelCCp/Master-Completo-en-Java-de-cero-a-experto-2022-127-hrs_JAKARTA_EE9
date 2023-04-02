package jee.master.models.entity;

import java.time.LocalDate;

public class Product {

    public Product(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getPuchaseDate() {
        return puchaseDate;
    }

    public void setPuchaseDate(LocalDate puchaseDate) {
        this.puchaseDate = puchaseDate;
    }

    private Long id;
    private String sku;
    private String name;
    private String comments;
    private Division division;
    private LocalDate releaseDate;
    private LocalDate puchaseDate;

}
