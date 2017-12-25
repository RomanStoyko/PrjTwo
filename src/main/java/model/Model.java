package model;

import model.entities.Sentence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

    /**
     *  convert {@link String @param text} to List of {@link Sentence}
     * @param text {@link String } input
     * @return List of {@link Sentence}
     */
    public List<Sentence> stringToListOfSentence(String text) {
        if(text == null || text.isEmpty()){
            return Collections.emptyList();
        }
        List<Sentence> sentences = new ArrayList<>();
        BreakIterator bi = BreakIterator.getSentenceInstance(); // wi will have an error if sentence = Text. 1. 
        bi.setText(text);
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
            String sentence = text.substring(index, bi.current());
            sentences.add(new Sentence(sentence));
            index = bi.current();
        }
        return sentences;
    }

    /**
     * convert file to string using Bytes
     * @param file - input file
     * @return  String
     *
     * @throws  IOException
     *          if an I/O error occurs reading from the stream
     * @throws  OutOfMemoryError
     *          if an array of the required size cannot be allocated, for
     *          example the file is larger that {@code 2GB}
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
     *          method is invoked to check read access to the file.
     */
    public String getFileString(File file) throws  IOException{
        String fileString = "";
            byte[] encoded = Files.readAllBytes(file.toPath());
            fileString = new String(encoded, "windows-1251");
        return fileString;
    }

    /**
     * convert file to List of {@link Sentence}
     * @param file input file
     * @return  List of {@link Sentence}
     *
     * @throws  IOException
     *          if an I/O error occurs reading from the stream
     * @throws  OutOfMemoryError
     *          if an array of the required size cannot be allocated, for
     *          example the file is larger that {@code 2GB}
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
     *          method is invoked to check read access to the file.
     */
    public List<Sentence> getFileSentence(File file) throws  IOException{
        return stringToListOfSentence(getFileString(file));
    }
}
