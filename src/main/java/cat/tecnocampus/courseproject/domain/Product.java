package cat.tecnocampus.courseproject.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;



public class Product {
    private String id;
    private String name;
    private double price;
    private String measure_unit;
    private String provider;
    private String vat_type;
    private String category;
    private LocalDate initial_date;
    private String day_of_week;
    private int num_of_periods;
    private String period;
    private String image;

    public Product(String id, String name, double price, String measure_unit, String provider, String vat_type, String category, LocalDate initial_date, String day_of_week, int num_of_periods, String period, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.measure_unit = measure_unit;
        this.provider = provider;
        this.vat_type = vat_type;
        this.category = category;
        this.initial_date = initial_date;
        this.day_of_week = day_of_week;
        this.num_of_periods = num_of_periods;
        this.period = period;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMeasure_unit() {
        return measure_unit;
    }

    public void setMeasure_unit(String measure_unit) {
        this.measure_unit = measure_unit;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getVat_type() {
        return vat_type;
    }

    public void setVat_type(String vat_type) {
        this.vat_type = vat_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(LocalDate initial_date) {
        this.initial_date = initial_date;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getNum_of_periods() {
        return num_of_periods;
    }

    public void setNum_of_periods(int num_of_periods) {
        this.num_of_periods = num_of_periods;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }








 
}
