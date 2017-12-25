package model.entities;



public class Sentence {
    private String sentence;
    private Word[] words;

    public Sentence() {
    }

    public Sentence(String sentence) {

        this.sentence =  sentence.replaceAll("(\\s+)"," ").trim();
        sentenceToWords(this.sentence);
    }

    private void sentenceToWords(String sentence){
       String[] words =  sentence.split("\\S+");
        this.words = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            this.words[i] = new Word(words[i]);
        }
    }

    public String getSentence() {
        return sentence;
    }

    public Word[] getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return this.sentence.equals(sentence.sentence);
    }

    @Override
    public int hashCode() {

        return sentence.hashCode();
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "sentence='" + sentence + '\'' +
                ", size=" + words.length +
                '}';
    }
}
