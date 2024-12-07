package ru.clevertec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cars")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "car_showroom_id")
    private CarShowroom carShowroom;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    public void addReviewToCar(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
        review.setCar(this);
    }
}