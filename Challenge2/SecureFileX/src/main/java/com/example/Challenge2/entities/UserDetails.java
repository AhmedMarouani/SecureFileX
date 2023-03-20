package com.example.Challenge2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String githubProfileLink;
    private String linkedInProfileLink;
    private Date createdAt;
    private Date updatedAt;

    @OneToOne(mappedBy = "userDetails")
    private User user;
}
