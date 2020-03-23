package s4.spring.td5.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Script>  scripts;

    private String name;
}
