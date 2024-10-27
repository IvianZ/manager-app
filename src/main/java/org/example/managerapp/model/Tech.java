package org.example.managerapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "techs")
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    @Builder.Default
    private Instant createdAt = Instant.now();

    @JoinTable(
        name = "employees_techs",
        joinColumns = @JoinColumn(
                name = "tech_id",
                referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "employee_id",
                referencedColumnName = "id"
        )
    )
    @OneToMany
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Tech{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
