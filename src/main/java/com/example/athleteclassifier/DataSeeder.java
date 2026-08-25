package com.example.athleteclassifier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AthleteRepository repo;
    private final TypologyClassifier classifier;

    public DataSeeder(AthleteRepository repo, TypologyClassifier classifier) {
        this.repo = repo;
        this.classifier = classifier;
    }

    @Override
    public void run(String... args) {
        if (repo.count() > 0) return;

        double[][] data = {
                // peakPowerW, meanPowerW, fatigueIndex, timeToPeakS, smmKg, pha, ecw, tbw
                {1686, 933,  69.4, 1.43, 44.1, 6.8, 21.3, 57.3},
                {1746, 1015, 60.7, 1.82, 46.8, 7.0, 21.8, 58.1},
                {1705, 1004, 62.1, 2.82, 48.5, 7.1, 22.1, 59.2},
                {1491, 910,  58.2, 1.41, 43.2, 6.3, 20.9, 56.4},
                {1722, 913,  75.1, 1.42, 44.8, 7.0, 21.5, 57.8},
                {1559, 857,  65.8, 1.41, 45.2, 6.5, 21.2, 57.0},
                {1589, 874,  78.2, 1.61, 46.1, 6.7, 21.6, 58.0},
                {1319, 826,  57.1, 1.01, 43.8, 7.2, 21.0, 56.8},
                {1426, 999,  50.5, 1.21, 46.9, 7.5, 21.9, 58.5},
                {1413, 788,  60.5, 0.80, 44.5, 7.0, 21.4, 57.5},
                {1234, 661,  62.9, 1.21, 41.3, 5.6, 20.8, 56.1},
                {1237, 753,  56.1, 1.41, 43.6, 7.3, 21.1, 56.9},
        };

        String[] ids = {
                "bbebc1dc","6f511f3e","21565640","9ae63fac",
                "a149847b","48087ecf","ad5c3960","3e63ba49",
                "12df3d06","26950ac6","9823b027","s389a986"
        };

        java.util.List<Athlete> athletes = new java.util.ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            Athlete a = new Athlete();
            a.setAthleteId(ids[i]);
            a.setPeakPowerW(data[i][0]);
            a.setMeanPowerW(data[i][1]);
            a.setFatigueIndex(data[i][2]);
            a.setTimeToPeakS(data[i][3]);
            a.setSmmKg(data[i][4]);
            a.setPha(data[i][5]);
            a.setEcw(data[i][6]);
            a.setTbw(data[i][7]);
            athletes.add(a);
        }

        classifier.classify(athletes);
        repo.saveAll(athletes);
        System.out.println("✅ Seeded " + athletes.size() + " athletes");
    }
}