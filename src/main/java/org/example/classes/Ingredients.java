package org.example.classes;

import java.util.Objects;

public class Ingredients {
    private double milk;

    private double sugar;

    private double cacao;

    private double vanilla;

    private double fructose;

    public double getMilk() {
        return milk;
    }

    public double getSugar() {
        return sugar;
    }

    public double getCacao() {
        return cacao;
    }

    public double getVanilla() {
        return vanilla;
    }

    public double getFructose() {return fructose;}

    public void setMilk(double milk) {
        this.milk = milk;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setCacao(double cacao) {
        this.cacao = cacao;
    }

    public void setVanilla(double vanilla) {
        this.vanilla = vanilla;
    }

    public void setFructose(double fructose) {this.fructose = fructose;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients ingredients = (Ingredients) o;
        return sugar == ingredients.sugar &&
                Double.compare(ingredients.vanilla, vanilla) == 0 &&
                milk == ingredients.milk &&
                cacao == ingredients.cacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(milk, sugar, cacao, vanilla);
    }
}
