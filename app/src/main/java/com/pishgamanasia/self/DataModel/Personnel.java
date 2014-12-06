package com.pishgamanasia.self.DataModel;

/**
 * Created by parsa on 2014-12-06.
 */
public class Personnel {
    int id;

    String name;
    String family;
    String nationalNo;
    String imageUrl;

    public Personnel(int id, String name, String family, String nationalNo, String imageUrl) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalNo = nationalNo;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalNo() {
        return nationalNo;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
