package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @NotNull(message="Employer is required")
    private Employer employer;
    private final List<Skill> skills = new ArrayList<>();
    public Job(Employer employer) {
        this.employer = employer;
    }

    public Job() {
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }
    public void addSkill(Skill skill){
        this.skills.add(skill);
    }
}
