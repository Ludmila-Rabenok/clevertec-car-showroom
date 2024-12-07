package ru.clevertec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_showrooms")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@NamedEntityGraph(
        name = "CarShowroom.withCarAndCategory",
        attributeNodes = {
                @NamedAttributeNode(value = "cars", subgraph = "cars.withCategory")
        },
        subgraphs = {
                @NamedSubgraph(name = "cars.withCategory", attributeNodes = @NamedAttributeNode("category"))
        }
)
public class CarShowroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "carShowroom", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();

    public void addCarToShowroom(Car car) {
        if (cars == null) {
            cars = new ArrayList<>();
        }
        cars.add(car);
        car.setCarShowroom(this);
    }
}