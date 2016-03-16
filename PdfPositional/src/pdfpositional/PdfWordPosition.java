package pdfpositional;

/**
 *
 * @author jonny
 */
public class PdfWordPosition {
    PdfCharacter start;
    PdfCharacter end;

    /**
     * start getter
     * @return 
     */
    public PdfCharacter getStart() {
        return start;
    }

    /**
     * start setter
     * @param start 
     */
    public void setStart(PdfCharacter start) {
        this.start = start;
    }

    /**
     * end getter
     * @return 
     */
    public PdfCharacter getEnd() {
        return end;
    }

    /**
     * end setter
     * @param end 
     */
    public void setEnd(PdfCharacter end) {
        this.end = end;
    }

    /**
     * constructor
     * @param start
     * @param end 
     */
    public PdfWordPosition(PdfCharacter start, PdfCharacter end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * get width
     * @return float
     */
    public float getWidth() {
        return getEnd().getxPos() - getStart().getxPos() + getEnd().getWidth();
    }
    
    /**
     * get height
     * @return float
     */
    public float getHeight() {
        return getStart().getHeight();
    }

    /**
     * get start x position
     * @return float
     */
    public float getStartX() {
        return getStart().getxPos();
    }
    
    /**
     * get start y position
     * @return float
     */
    public float getStartY() {
        return getStart().getyPos();
    }
    
}
