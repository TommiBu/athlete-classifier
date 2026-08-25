package com.example.athleteclassifier;

import jakarta.persistence.*;

@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String athleteId;
    private double peakPowerW;
    private double meanPowerW;
    private double fatigueIndex;
    private double timeToPeakS;
    private double smmKg;
    private double pha;
    private double ecw;
    private double tbw;

    // klasifikace — vyplní classifier
    private String quadrant;
    private String profil;
    private double ppPerSmm;
    private double fiMeanRatio;

    // gettery a settery — v IntelliJ: Alt+Insert → Getters and Setters
    public Long getId() { return id; }
    public String getAthleteId() { return athleteId; }
    public void setAthleteId(String athleteId) { this.athleteId = athleteId; }
    public double getPeakPowerW() { return peakPowerW; }
    public void setPeakPowerW(double peakPowerW) { this.peakPowerW = peakPowerW; }
    public double getMeanPowerW() { return meanPowerW; }
    public void setMeanPowerW(double meanPowerW) { this.meanPowerW = meanPowerW; }
    public double getFatigueIndex() { return fatigueIndex; }
    public void setFatigueIndex(double fatigueIndex) { this.fatigueIndex = fatigueIndex; }
    public double getTimeToPeakS() { return timeToPeakS; }
    public void setTimeToPeakS(double timeToPeakS) { this.timeToPeakS = timeToPeakS; }
    public double getSmmKg() { return smmKg; }
    public void setSmmKg(double smmKg) { this.smmKg = smmKg; }
    public double getPha() { return pha; }
    public void setPha(double pha) { this.pha = pha; }
    public double getEcw() { return ecw; }
    public void setEcw(double ecw) { this.ecw = ecw; }
    public double getTbw() { return tbw; }
    public void setTbw(double tbw) { this.tbw = tbw; }
    public String getQuadrant() { return quadrant; }
    public void setQuadrant(String quadrant) { this.quadrant = quadrant; }
    public String getProfil() { return profil; }
    public void setProfil(String profil) { this.profil = profil; }
    public double getPpPerSmm() { return ppPerSmm; }
    public void setPpPerSmm(double ppPerSmm) { this.ppPerSmm = ppPerSmm; }
    public double getFiMeanRatio() { return fiMeanRatio; }
    public void setFiMeanRatio(double fiMeanRatio) { this.fiMeanRatio = fiMeanRatio; }
}