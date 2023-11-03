package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    @NotNull(message = "Description is required")
    @Size(min = 15, max = 250, message = "Skill description must be between 15 and 250 characters")
    private String description;

//    @ManyToMany(mappedBy = "skills")
//    private final List<Job> jobs = new ArrayList<>();


    public Skill(String description) {
        this.description = description;
    }

    public Skill(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<Job> getJobs() {
//        return jobs;
//    }
}
