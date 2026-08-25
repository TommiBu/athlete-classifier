package com.example.athleteclassifier;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TypologyClassifier {

    public List<Athlete> classify(List<Athlete> athletes) {

        // Výpočet odvozených metrik
        for (Athlete a : athletes) {
            a.setPpPerSmm(a.getPeakPowerW() / a.getSmmKg());
            a.setFiMeanRatio(a.getFatigueIndex() / a.getMeanPowerW() * 1000);
        }

        // Prahy — medián pp/smm a fi_mean_ratio
        double ppThr  = median(athletes.stream()
                .mapToDouble(Athlete::getPpPerSmm).sorted().toArray());
        double fmrThr = median(athletes.stream()
                .mapToDouble(Athlete::getFiMeanRatio).sorted().toArray());

        // Skupiny H/L pro TTP práh
        double ttpH = median(athletes.stream()
                .filter(a -> a.getPpPerSmm() >= ppThr)
                .mapToDouble(Athlete::getTimeToPeakS).sorted().toArray());
        double ttpL = median(athletes.stream()
                .filter(a -> a.getPpPerSmm() < ppThr)
                .mapToDouble(Athlete::getTimeToPeakS).sorted().toArray());

        // Klasifikace každého hráče
        for (Athlete a : athletes) {
            boolean highPp  = a.getPpPerSmm()    >= ppThr;
            boolean lowFmr  = a.getFiMeanRatio() <= fmrThr;
            String quadrant = highPp && lowFmr  ? "Q1" :
                    highPp            ? "Q2" :
                            lowFmr            ? "Q3" : "Q4";

            double ttpThr = highPp ? ttpH : ttpL;
            String tag    = a.getTimeToPeakS() < ttpThr ? "R" : "P";

            a.setQuadrant(quadrant);
            a.setProfil(quadrant + tag);
        }

        return athletes;
    }

    private double median(double[] sorted) {
        int n = sorted.length;
        if (n == 0) return 0;
        return n % 2 == 0
                ? (sorted[n/2 - 1] + sorted[n/2]) / 2.0
                : sorted[n/2];
    }
}