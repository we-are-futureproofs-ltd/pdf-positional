/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfpositional;

import org.apache.pdfbox.util.TextPosition;

/**
 *
 * @author jonny
 */
public class PdfCharacter {
    /**
     * Get the value of xPos
     *
     * @return the value of xPos
     */
    public float getxPos() {
        return position.getXDirAdj() * conversion;
    }

    /**
     * Get the value of yPos
     *
     * @return the value of yPos
     */
    public float getyPos() {
        return position.getYDirAdj() * conversion;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public float getWidth() {
        return position.getWidthDirAdj() * conversion;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public float getHeight() {
        return position.getHeightDir() * conversion;

    }

    private TextPosition position;

    /**
     * Get the value of position
     *
     * @return the value of position
     */
    public TextPosition getPosition() {
        return position;
    }

    /**
     * Set the value of position
     *
     * @param position new value of position
     */
    public void setPosition(TextPosition position) {
        this.position = position;
    }

    private Float conversion;

    /**
     * Get the value of conversion
     *
     * @return the value of conversion
     */
    public Float getConversion() {
        return conversion;
    }

    /**
     * Set the value of conversion
     *
     * @param conversion new value of conversion
     */
    public void setConversion(Float conversion) {
        this.conversion = conversion;
    }
    
    /**
     * check to determine if letter is part of current word
     * @param charPosition
     * @return boolean
     */
    public boolean isSameWord(TextPosition charPosition) {
        if (!isSameLine(charPosition)) {
            return false;
        }
        
        return isWithinPermittedSpacing(charPosition);
    }
    
    /**
     * check to see if character sits on same line
     * @param charPosition
     * @return 
     */
    public boolean isSameLine(TextPosition charPosition) {
        return compareFloat(charPosition.getYDirAdj(), this.getPosition().getYDirAdj());
    }

    /**
     * check to see if character sits within allowable space distance
     * @param charPosition
     * @return 
     */    
    public boolean isWithinPermittedSpacing(TextPosition charPosition) {
        return (this.getMaxNextWordXpos() >= charPosition.getXDirAdj());
    }
    
    /**
     * determine maximum xPos of next character
     * @return 
     */
    public Float getMaxNextWordXpos() {
        return this.getPosition().getXDirAdj()+ this.getPosition().getWidthDirAdj() + this.getPosition().getWidthOfSpace();
    }
    
    /**
     * compare 2 floats to within 1 SF
     * @param val1
     * @param val2
     * @return boolean
     */
    protected boolean compareFloat(float val1, float val2) {
        return Math.floor(val1 * 10) == Math.floor(val2 * 10);
    }
    
    private static final String REGEX_WHITESPACE = "[^a-zA-Z0-9\\uFB00\\uFB01\\uFB02\\uFB03\\uFB04\\uFB05\\uFB06]"; // old = "[-,.\\[\\](:;!?)/\\u00A0]"
    
    /**
     * is character a white space
     * @return boolean
     */
    public boolean isWhiteSpace() {
        String chr = this.position.getCharacter();
        if (this.position.getCharacter().matches(REGEX_WHITESPACE)) {
            return true;
        }
        
        return (Character.isWhitespace(chr.charAt(0)));
    }
    
    /**
     * get normalized Character
     * @return String
     */
    public String getNormalizedCharacter() {
        switch((int)this.position.getCharacter().charAt(0)) {
            case 64256: return "ff";
            case 64257: return "fi";
            case 64258: return "fl";
            case 64259: return "ffi";
            case 64260: return "ffl";
            case 64261: return "st";
            default: return this.position.getCharacter();
        }
    }


    /**
     * constructor
     * @param position
     * @param conversion 
     */
    public PdfCharacter(TextPosition position, Float conversion) {
        this.conversion = conversion;
        this.position = position;
    }
    
}
