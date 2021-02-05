package com.example.pokdexcreando.models;

public class EvolutionDetails {
    private Family family;
    public String sprite;
    public EvolutionDetails(){

    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Family getFamily() {
        return family;
    }

    public String getSprite() {
        return sprite;
    }
}
