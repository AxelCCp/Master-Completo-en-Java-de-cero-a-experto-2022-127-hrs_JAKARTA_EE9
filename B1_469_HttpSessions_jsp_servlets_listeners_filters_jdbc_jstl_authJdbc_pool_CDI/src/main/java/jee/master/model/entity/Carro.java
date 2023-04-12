package jee.master.model.entity;

import jakarta.enterprise.context.SessionScoped;
import jdk.jfr.Name;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//1.-SE DEFINE UN BEAN CON SU CONTEXTO
    //1.1.-LE DAMOS UN CONTEXTO
    //1.2.-LE DAMOS UN NOMBRE
//2.-CON @SESSION SCOPED Y  CONVERSACION, SIEMPRE SE IMPLEMENTA SERIALIZABLE.


@SessionScoped      //1.1
@Name("carro")      //1.2
public class Carro implements Serializable {

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
