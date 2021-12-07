package org.example.classes;

import java.util.Objects;

public class Candy implements Comparable<Candy>{
    private int id;

    private String name;

    private String companyName;

    private Type type;

    private Ingredients ingredients;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Type getType() {
        return type;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append('\n');
        result.append(companyName).append('\n');
        result.append(type).append('\n');
        result.append(ingredients).append('\n');
        return result.toString();
    }

    @Override
    public int compareTo(Candy o) {
        return (Double.compare(this.ingredients.getSugar(), o.getIngredients().getSugar()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return id == candy.id &&
                name.equals(candy.name) &&
                companyName.equals(candy.companyName) &&
                type == candy.type &&
                ingredients.equals(candy.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, companyName, type, ingredients);
    }
}
