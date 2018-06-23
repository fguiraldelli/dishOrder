package order;

import java.math.BigDecimal;

public class ItemOrder {

    private Enum foodType;
    private Enum dishType;
    private BigDecimal dishPrice;
    private BigDecimal totalDishPrice;
    private Integer dishQuantities;

    public ItemOrder(Enum foodType, Enum dishType, BigDecimal dishPrice, int dishQuantities) {
        this.foodType = foodType;
        this.dishType = dishType;
        this.dishPrice = dishPrice;
        this.dishQuantities = dishQuantities;
    }

    public Enum getFoodType() {
        return foodType;
    }

    public void setFoodType(Enum foodType) {
        this.foodType = foodType;
    }

    public Enum getDishType() {
        return dishType;
    }

    public void setDishType(Enum dishType) {
        this.dishType = dishType;
    }

    public BigDecimal getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(BigDecimal dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishQuantities() {
        return dishQuantities;
    }

    public void setDishQuantities(int dishQuantities) {
        this.dishQuantities = dishQuantities;
    }

    public BigDecimal getTotalDishPrice() {
        return totalDishPrice;
    }

    public void setTotalDishPrice(BigDecimal totalDishPrice) {
        this.totalDishPrice = dishPrice.multiply(BigDecimal.valueOf(dishQuantities));
    }
}
