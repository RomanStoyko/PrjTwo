package controller;

import model.Model;
import model.entities.Sentence;
import model.util.FileExtensionError;
import view.View;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View();
        this.model = new Model();
    }


    public void doWork() {
        while (true){
            view.printMessage("Input file pass to .txt file");
            view.printMessage("For exit input -ext");
            File file;
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                String filePass = bf.readLine();

                if(filePass.equals("-ext")){
                    break;
                }

                if(filePass.contains(".")) {
                    if (!filePass.endsWith(".txt")) {
                        throw new FileExtensionError(".txt");
                    }
                }else{
                    filePass =  filePass + ".txt";
                }

                file = new File(filePass);
                if (!file.exists()) {
                    throw new FileNotFoundException(filePass);
                }

            }catch (IOException e){
                if(e instanceof FileNotFoundException) {
                    view.printMessage("No such file: " + e.getMessage());
                }
                if(e instanceof FileExtensionError) {
                    view.printMessage(e.getMessage());
                }
                view.printMessage("Wrong input repeat");
                continue;
            }
            List<Sentence> sentences = null;
            try {
                sentences = model.getFileSentence(file);
            } catch (IOException e) {
                view.printMessage("Error IO: " + e.getMessage());
                continue;
            } catch (OutOfMemoryError | SecurityException e){
                view.printMessage( e.getMessage());
                continue;
            }

            Collections.sort(sentences, new Comparator<Sentence>() {
                @Override
                public int compare(Sentence o1, Sentence o2) {
                    return o2.getWords().length - o1.getWords().length;
                }
            });

            view.printMessage(sentences);
        }
    }
}
