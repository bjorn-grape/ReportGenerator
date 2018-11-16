package com.bjorn_grape.app;

import java.util.HashMap;
import java.util.function.Consumer;

public class MarkovFactory {
    HashMap<String, Integer> StringToId = new HashMap<String, Integer>();
    HashMap<Integer, String> IdToString = new HashMap<Integer, String>();
    Integer currentId = 1;
    HashMapOfNextWordProbabilityAtRange hmpat = new HashMapOfNextWordProbabilityAtRange();


    public MarkovFactory(String input) {
        simpleNextIndexMapper(input);
    }

    public void simpleNextIndexMapper(String input) {
        Reader rd = new Reader(input);
        hmpat = new HashMapOfNextWordProbabilityAtRange();

        String str = rd.getToken();
        StringToId.put("\0", 0);
        IdToString.put(0, "\0");
        while (str.length() > 0) {
            if (!StringToId.containsKey(str)) {
                StringToId.put(str, currentId);
                IdToString.put(currentId, str);
                currentId++;
            }
            str = rd.getToken();
        }
        rd.resetIndex();
        str = rd.getToken();
        while (str.length() > 0) {
            String tmpstr = rd.getToken();
            hmpat.PutWord(StringToId.get(str), StringToId.get(tmpstr));
            str = tmpstr;
        }
        hmpat.Normalizeprobabilities();
        hmpat.clearProbabilityConstructor();
    }

    public String getNextWord(String word){
       return IdToString.get(hmpat.GetNextMostProbableWord(StringToId.get(word)));
    }
}
