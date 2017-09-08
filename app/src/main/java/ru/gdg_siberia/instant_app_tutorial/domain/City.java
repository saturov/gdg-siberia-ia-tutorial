package ru.gdg_siberia.instant_app_tutorial.domain;

public class City {

    private String name;
    private String description;
    private int pictureId;

    public City(String name, int pictureId) {
        this.name = name;
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureId=" + pictureId +
                '}';
    }
}