package com.example.gestiondesrservationsdesalle.Entity;

import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Employee {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private RoleEmployee role;

    private String email;
}
