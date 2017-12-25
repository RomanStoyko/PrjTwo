package view;

import model.entities.Sentence;

import java.util.List;

public class View {

    public void printMessage(String string){
            System.out.println(string);
    }

    public void printMessage(List<Sentence> sentences){
        for (Sentence s:sentences) {
            System.out.println(s);
        }

    }
}
