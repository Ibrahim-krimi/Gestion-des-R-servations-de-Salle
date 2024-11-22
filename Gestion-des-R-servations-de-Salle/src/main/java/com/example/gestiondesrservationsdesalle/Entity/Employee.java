package com.example.gestiondesrservationsdesalle.Entity;

import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    private String nom;

    private RoleEmployee role;

    private String email;
}
