import api.*;
import java.util.*;

public class Solution implements SolutionInterface {
    private APICaller api;

    public Solution(APICaller api) {
        this.api = api;
    }

    public int popularSize() {
        Map<Integer, Integer> sizeCount = new HashMap<>();
        int totalPeople = api.getNumberOfPeople();
        for (int i = 0; i < totalPeople; i++) {
            int size = api.getSize(i);
            if (sizeCount.containsKey(size)) {
                sizeCount.put(size, sizeCount.get(size) + 1);
            } else {
                sizeCount.put(size, 1);
            }
        }
        int maxCount = 0;
        int popularSize = -1;
        for (Map.Entry<Integer, Integer> entry : sizeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularSize = entry.getKey();
            } else if (entry.getValue() == maxCount) {
                popularSize = -1;
            }
        }
        return popularSize;
    }
    
    public String popularName() {
        int popularSize = popularSize();
        if (popularSize == -1) {
            return "";
        }
        Map<String, Integer> nameCount = new HashMap<>();
        int totalPeople = api.getNumberOfPeople();
        for (int i = 0; i < totalPeople; i++) {
            if (api.getSize(i) == popularSize) {
                String name = api.getName(i);
                if (nameCount.containsKey(name)) {
                    nameCount.put(name, nameCount.get(name) + 1);
                } else {
                    nameCount.put(name, 1);
                }
            }
        }
        int maxCount = 0;
        String popularName = "";
        for (Map.Entry<String, Integer> entry : nameCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularName = entry.getKey();
            } else if (entry.getValue() == maxCount) {
                popularName = "";
            }
        }
        return popularName;
    }
}    