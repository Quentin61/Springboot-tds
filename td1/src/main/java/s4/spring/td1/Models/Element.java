package s4.spring.td1.Models;

import java.util.Objects;

public class Element
{


    private String nom;
    private int evaluation;

    public Element(String nom)
    {
        this.nom= nom;
        this.evaluation=0;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(int evuation) {
        this.evaluation = evuation;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return evaluation == element.evaluation &&
                Objects.equals(nom, element.nom);
    }

}
