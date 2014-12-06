package com.pishgamanasia.self.DataModel;

/**
 * Created by parsa on 2014-12-06.
 */
public class Food {
    int id;
    String caption;
    String count;

    public Food(int id, String caption, String count) {
        this.id = id;
        this.caption = caption;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
