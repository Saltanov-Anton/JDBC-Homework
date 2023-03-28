package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column
    private String cityName;
}
