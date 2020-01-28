package by.academy.it.travelcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transfer {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String arriveAddress;
    @Column
    private String departureAddress;
    @Column
    private LocalDateTime arriveTime;
    @Column
    private LocalDateTime departureTime;
    @Column
    private Double price;
    @Column
    private String currencyCode;
}
