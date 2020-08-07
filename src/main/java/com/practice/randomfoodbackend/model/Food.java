package com.practice.randomfoodbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    private String foodName;
    private String tags;
}
