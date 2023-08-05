package com.example.jv6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @NotBlank(message = "Vui long nhap ID")
    private String id;

    @NotBlank(message = "Vui long nhap ten")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}
