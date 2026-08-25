package com.example.athleteclassifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AthleteController {

    private final AthleteRepository repo;

    public AthleteController(AthleteRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Athlete> athletes = repo.findAll();
        model.addAttribute("athletes", athletes);
        model.addAttribute("q1", athletes.stream().filter(a -> a.getQuadrant().equals("Q1")).toList());
        model.addAttribute("q2", athletes.stream().filter(a -> a.getQuadrant().equals("Q2")).toList());
        model.addAttribute("q3", athletes.stream().filter(a -> a.getQuadrant().equals("Q3")).toList());
        model.addAttribute("q4", athletes.stream().filter(a -> a.getQuadrant().equals("Q4")).toList());
        return "index";
    }

    @GetMapping("/athlete/{id}")
    public String detail(@PathVariable String id, Model model) {
        repo.findAll().stream()
                .filter(a -> a.getAthleteId().equals(id))
                .findFirst()
                .ifPresent(a -> {
                    model.addAttribute("athlete", a);
                    String desc = switch (a.getQuadrant()) {
                        case "Q1" -> "Vyšší PP/SMM · užší rozptyl";
                        case "Q2" -> "Vyšší PP/SMM · širší rozptyl";
                        case "Q3" -> "Nižší PP/SMM · užší rozptyl";
                        default   -> "Nižší PP/SMM · širší rozptyl";
                    };
                    String color = switch (a.getQuadrant()) {
                        case "Q1" -> "linear-gradient(135deg,#1D9E75,#1D9E75BB)";
                        case "Q2" -> "linear-gradient(135deg,#EF9F27,#EF9F27BB)";
                        case "Q3" -> "linear-gradient(135deg,#378ADD,#378ADDBB)";
                        default   -> "linear-gradient(135deg,#E24B4A,#E24B4ABB)";
                    };
                    model.addAttribute("quadrantDesc", desc);
                    model.addAttribute("hdrColor", color);
                });
        return "detail";
    }
