package oleksii.pl;

import java.util.Objects;

public class Pizza {
    private int id;
    private String name;
    private String description;
    private String size;
    private float price;
    private int popularity;
    private String category;

    public Pizza(int id, String name, String description, String size, float price, int popularity, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.popularity = popularity;
        this.category = category;
    }

    public Pizza() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id && Float.compare(pizza.price, price) == 0 && popularity == pizza.popularity && Objects.equals(name, pizza.name) && Objects.equals(description, pizza.description) && Objects.equals(size, pizza.size) && Objects.equals(category, pizza.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, size, price, popularity, category);
    }

    @Override
    public String toString() {
        return "Pizza " + id + ": " +
                name + " - " +
                description +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", popularity=" + popularity +
                ", category='" + category + '\'' +
                ')';
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
