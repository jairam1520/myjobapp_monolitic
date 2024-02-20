package com.myjobapp.MyJobApp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myjobapp.MyJobApp.job.Job;
import com.myjobapp.MyJobApp.reviews.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;


    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
