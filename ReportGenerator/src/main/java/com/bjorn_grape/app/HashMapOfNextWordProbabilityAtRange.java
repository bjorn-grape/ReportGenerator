package com.bjorn_grape.app;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapOfNextWordProbabilityAtRange {
    public int depth;

    private HashMap<Integer, HashMap<Integer, Float>> IdToNextWordIdProbabilityConstructor = new HashMap<>();
    private HashMap<Integer, ArrayList<ProbaForId>> IdToNextWordIdProbabilityNormalized = new HashMap<>();

    // must not be used after NormalizeProbabilities is called
    public void PutWord(Integer IdKey, Integer IdValue) {
        HashMap<Integer, Float> tmp = IdToNextWordIdProbabilityConstructor.get(IdKey);
        if (tmp != null) {
            if (tmp.get(IdValue) != null)
                tmp.put(IdValue, tmp.get(IdValue) + 1f);
            else
                tmp.put(IdValue, 1f);
        } else {
            tmp = new HashMap<>();
            IdToNextWordIdProbabilityConstructor.put(IdKey, tmp);
            tmp.put(IdValue, 1f);
        }
    }

    public void Normalizeprobabilities() {
        IdToNextWordIdProbabilityConstructor.forEach((integer1, IntFloatHM) -> {
            Float tmpsumm = 0f;
            for (Float value : IntFloatHM.values()) {
                tmpsumm += value;
            }
            final Float sum = tmpsumm;

            ArrayList<ProbaForId> arrayList = new ArrayList<>();
            IntFloatHM.forEach((i1, f1) -> arrayList.add(new ProbaForId(f1 /= sum, i1)));
            // sort in decreasing order
            arrayList.sort((t1, t2) -> t1.Probability < t2.Probability ? 1 : -1);
            IdToNextWordIdProbabilityNormalized.put(integer1, arrayList);
        });
    }

    public void clearProbabilityConstructor() {
        IdToNextWordIdProbabilityConstructor = new HashMap<>();
    }

    public void clearAll() {
        IdToNextWordIdProbabilityConstructor = new HashMap<>();
        IdToNextWordIdProbabilityNormalized = new HashMap<>();
    }

    public Integer GetNextMostProbableWord(Integer Id) {
        if (IdToNextWordIdProbabilityNormalized.get(Id) == null)
            throw new IllegalArgumentException("Word does not exist!");
        return IdToNextWordIdProbabilityNormalized.get(Id).get(0).Id;
    }

    private class ProbaForId {
        Float Probability;
        Integer Id;

        ProbaForId(Float pro, Integer i) {
            Probability = pro;
            Id = i;
        }
    }
}
