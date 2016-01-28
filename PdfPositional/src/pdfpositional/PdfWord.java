/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import org.json.simple.JSONObject;

/**
 *
 * @author jonny
 */
public class PdfWord {
    
    private PdfLocation locationStart;

    /**
     * Get the value of locationStart
     *
     * @return the value of locationStart
     */
    public PdfLocation getLocationStart() {
        return locationStart;
    }

    /**
     * Set the value of locationStart
     *
     * @param locationStart new value of locationStart
     */
    public void setLocationStart(PdfLocation locationStart) {
        this.locationStart = locationStart;
    }

        private PdfLocation locationEnd;

    /**
     * Get the value of locationEnd
     *
     * @return the value of locationEnd
     */
    public PdfLocation getLocationEnd() {
        return locationEnd;
    }

    /**
     * Set the value of locationEnd
     *
     * @param locationEnd new value of locationEnd
     */
    public void setLocationEnd(PdfLocation locationEnd) {
        this.locationEnd = locationEnd;
    }

    private String word;

    /**
     * Get the value of word
     *
     * @return the value of word
     */
    public String getWord() {
        return word;
    }

    /**
     * Set the value of word
     *
     * @param word new value of word
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    public void addCharacter(String character, PdfLocation location) {
        this.setWord(this.getWord() + character);
        this.setLocationEnd(location);
    }


    public PdfWord(String startChar, PdfLocation location) {
        this.word = startChar;
        this.locationStart = location;
        this.locationEnd = location;
    }

    @Override
    public String toString() {
        return this.word;
    }
    
    
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("word", this.getWord().replaceAll("[^\\x00-\\x7F]", ""));
        obj.put("height", this.getLocationStart().getHeight());
        obj.put("width", this.getLocationEnd().getxPos() - this.getLocationStart().getxPos() + this.getLocationEnd().getWidth());
        obj.put("x", this.getLocationStart().getxPos());
        obj.put("y", this.getLocationStart().getyPos());
        
        return obj;
    }
            
    
    
}
