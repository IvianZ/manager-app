package org.example.managerapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "domain_user")
    private String domainUser;

    @Column(name = "computer_name")
    private String computerName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @JoinTable(
        name = "employees_techs",
        joinColumns = @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "tech_id",
            referencedColumnName = "id"
        )
    )
    @OneToMany
    private List<Tech> techs;

    @JoinTable(
        name = "employees_projects",
        joinColumns = @JoinColumn(
                name = "employee_id",
                referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "project_id",
                referencedColumnName = "id"
        )
    )
    @OneToMany
    private List<Project> projects;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
