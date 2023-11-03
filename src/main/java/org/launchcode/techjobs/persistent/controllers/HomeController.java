package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        return "index";
    }

    @GetMapping("/add")
    public String displayAddJobForm(@NotNull Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        List<Employer> employers = (List<Employer>) employerRepository.findAll();
        List<Skill> skills = (List<Skill>) skillRepository.findAll();
        model.addAttribute("employers", employers);
        model.addAttribute("skills", skills);
        return "add";
    }


    @PostMapping("/add")
    public String processAddJobForm (@ModelAttribute @Valid Job newJob,
                                     Errors errors, Model model, @RequestParam int employerId, @RequestParam (required = false) List<Integer> skills){
        System.out.println("Entering processAddJobForm");
        if (errors.hasErrors()) {
            System.out.println("Errors detected");
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
            Optional<Employer> result = employerRepository.findById(employerId);
            if (result.isEmpty()) {
                System.out.println("No employer selected");
                model.addAttribute("title", "Invalid Employer");
            } else {
                Employer selectedEmployer = result.get();
                newJob.setEmployer(selectedEmployer);
                newJob.setEmployer(selectedEmployer);
            }
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
            System.out.println("added skills to new job");
        }
        jobRepository.save(newJob);
        return "index";
    }


//    @PostMapping("/add")
//        public String processAddJobForm (@ModelAttribute @Valid Job newJob,
//                Errors errors, Model model, @RequestParam int employerId, @RequestParam (required = false) List<Integer> skills){
//        System.out.println("Entering processAddJobForm");
//        if (errors.hasErrors()) {
//                System.out.println("Errors detected");
//                model.addAttribute("title", "Add Job");
//                return "add";
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//                if (result.isEmpty()) {
//                    System.out.println("No employer selected");
//                    model.addAttribute("title", "Invalid Employer");
//                } if (skills.isEmpty() || skills == null){
//                    System.out.println("No skills selected");
//                    model.addAttribute("title", "No Skills Selected");
//                }
//            Employer selectedEmployer = result.get();
//            newJob.setEmployer(selectedEmployer);
//            newJob.setEmployer(selectedEmployer);
//            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//            newJob.setSkills(skillObjs);
//            System.out.println("added skills to new job");
//            }
//            jobRepository.save(newJob);
//            return "index";
//        }

    @GetMapping("/view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}



////code where I thought I might need to account for empty employer and skills fields:
//@GetMapping("/add")
//public String displayAddJobForm(@NotNull Model model) {
//    model.addAttribute("title", "Add Job");
//    model.addAttribute(new Job());
//    List<Employer> employers = (List<Employer>) employerRepository.findAll();
//    List<Skill> skills = (List<Skill>) skillRepository.findAll();
//    if (employers.isEmpty() && skills.isEmpty()) {
//        //displays message to the users in the case that no employers have been added yet
//        model.addAttribute("noEmployers", "Employer list is empty, you must add the employer first.");
//        model.addAttribute("noSkills", "Skill list is empty, you must add the employer first.");
//    } else if (employers.isEmpty()) {
//        //displays message to the users in the case that no employers have been added yet
//        model.addAttribute("noEmployers", "Employer list is empty, you must add the employer first.");
//    } else if (skills.isEmpty()) {
//        //displays message to the users in the case that no employers have been added yet
//        model.addAttribute("noSkills", "Skill list is empty, you must add skill(s) first.");
//    } else {
//        model.addAttribute("employers", employers);
//    }
//    return "add";

//    @PostMapping("/add")
//    public String processAddJobForm (@ModelAttribute @Valid Job newJob,
//                                     Errors errors, Model model,@RequestParam int employerId, @RequestParam (required = false) List<Integer> skills){
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "add";
//        }
//        Optional<Employer> result = employerRepository.findById(employerId);
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Employer");
//        } else {
//            Employer selectedEmployer = result.get();
//            newJob.setEmployer(selectedEmployer);
//            newJob.setEmployer(selectedEmployer);
//        }
//        if (skills.isEmpty() || skills == null){
//            model.addAttribute("title", "No Skills Selected");
//        } else {
//            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//            newJob.setSkills(skillObjs);
//        }
//        jobRepository.save(newJob);
//        return "redirect:";
//    }
