package com.example.pokdexcreando.models;

import java.util.List;

public class Family {
    private int id;
    private List<String> evolutionLine;

    public void setId(int id) {
        this.id = id;
    }

    public void setEvolutionLine(List<String> evolutionLine) {
        this.evolutionLine = evolutionLine;
    }

    public int getId() {
        return id;
    }

    public List<String> getEvolutionLine() {
        return evolutionLine;
    }
}
