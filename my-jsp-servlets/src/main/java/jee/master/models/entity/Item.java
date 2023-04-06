package jee.master.models.entity;

import java.util.Objects;

public class Item {

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item myItem = (Item) o;
        return Objects.equals(product.getId(), myItem.product.getId())  &&  Objects.equals(product.getName(), myItem.product.getName());
    }

    private Product product;
    private Integer quantity;

}
