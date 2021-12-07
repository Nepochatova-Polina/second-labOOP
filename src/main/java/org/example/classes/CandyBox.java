package org.example.classes;

import java.util.ArrayList;
import java.util.List;

public class CandyBox {
    private List<Candy> candyList;

    public List<Candy> getcandyList() {
        if(candyList == null){
            candyList = new ArrayList<>();
        }
        return candyList;
    }
}
