package jee.master.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {

    public Carro(){
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro){
        if(items.contains(itemCarro)){
            Optional<ItemCarro> itemCarroOptional = items.stream().filter(i -> i.equals(itemCarro)).findAny();
            if(itemCarroOptional.isPresent()){
                ItemCarro i = itemCarroOptional.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        }else{
            this.items.add(itemCarro);
        }

    }

    public int getTotal(){
        return items.stream().mapToInt(i -> i.getImporte()).sum();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    List<ItemCarro>items;
}
