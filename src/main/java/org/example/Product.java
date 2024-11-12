package org.example;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {
   private String name;
    private Double price;
    private LocalDate creationDate;
    private  String type;
    private  LocalDate validUntil;


}
