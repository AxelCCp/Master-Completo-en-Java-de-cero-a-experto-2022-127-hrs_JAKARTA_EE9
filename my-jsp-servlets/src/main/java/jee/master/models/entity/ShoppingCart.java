package jee.master.models.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {

    public ShoppingCart(){
        itemsList = new ArrayList<>();
    }

    public void addItemCart(Item itemCart){
        if(itemsList.contains(itemCart)){
            Optional<Item> itemCartOptional = itemsList.stream().filter(i -> i.equals(itemCart)).findAny();
            if(itemCartOptional.isPresent()){
                Item i = itemCartOptional.get();
                i.setQuantity(i.getQuantity() + 1);
            }
        }else{
            this.itemsList.add(itemCart);
        }
    }

    public Integer getTotal(){
        return itemsList.stream().mapToInt( i -> i.getQuantity() * i.getProduct().getPrice()).sum();
    }

    public void removeProducts(List<String> productsIds) {
        if(productsIds != null){
            productsIds.forEach(id -> this.deleteProduct(id));
        }
    }

    private void deleteProduct(String id){
        Optional<Item> product = itemsList.stream().filter(item -> id.equals(Long.toString(item.getProduct().getId()))).findAny();
        product.ifPresent(item -> itemsList.remove(item));
    }

    public void updateQuantity(String id, Integer quantity){
        Optional<Item> product = itemsList.stream().filter(item -> id.equals(Long.toString(item.getProduct().getId()))).findAny();
        product.ifPresent(item -> item.setQuantity(quantity));
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    List<Item>itemsList;


}
