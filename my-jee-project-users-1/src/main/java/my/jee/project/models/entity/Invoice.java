package my.jee.project.models.entity;



import java.util.List;

public class Invoice {




    private Long id;
    private String description;
    private List<Item> items;
    private Double price;
}
