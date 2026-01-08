package com.tastytown.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Lazy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private String foodId;

    private String foodName;
    private String foodDescription;
    private double foodPrice;
    private String foodImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
