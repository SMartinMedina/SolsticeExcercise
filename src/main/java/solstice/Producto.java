package solstice;

public class Producto {

    private long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
    private boolean availability;

    public Producto(long id, String name, String category, Double retail_price, Double discounted_price, boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retail_price = retail_price;
        this.discounted_price = discounted_price;
        this.availability = availability;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getRetail_price() {
        return retail_price;
    }

    public Double getDiscounted_price() {
        return discounted_price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRetail_price(Double retail_price) {
        this.retail_price = retail_price;
    }

    public void setDiscounted_price(Double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
