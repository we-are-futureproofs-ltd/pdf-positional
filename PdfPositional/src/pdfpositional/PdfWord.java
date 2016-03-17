package pdfpositional;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author jonny
 */
public class PdfWord {
    private final ArrayList<PdfWordPosition> positions = new ArrayList<>();
    private PdfWordPosition position;
    
    private String word;
    private String wordNormalised;

    /**
     * wordNormalised getter
     * @return 
     */
    public String getWordNormalised() {
        return wordNormalised;
    }

    /**
     * wordNormalised setter
     * @param wordNormalised 
     */
    public void setWordNormalised(String wordNormalised) {
        this.wordNormalised = wordNormalised;
    }
    
    /**
     * positions array getter
     * @return ArrayList
     */
    public ArrayList<PdfWordPosition> getPositions() {
        return positions;
    }

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
    
    private Boolean softBreak = false;

    /**
     * Get the value of softBreak
     *
     * @return the value of softBreak
     */
    public Boolean isSoftBreak() {
        return softBreak;
    }

    /**
     * Set the value of softBreak
     *
     * @param softBreak new value of softBreak
     */
    public void setSoftBreak(Boolean softBreak) {
        this.softBreak = softBreak;
    }

    /**
     * add character to word
     * @param location
     * @return boolean
     */
    public boolean addCharacter(PdfCharacter location) {
        if (location.isSoftWordBreak()) {
            this.setSoftBreak(true);
            this.position.setEnd(location);
            return true;
        }
        
        String normalizedChar = location.getNormalizedCharacter();
        
        if (location.isPuctuationEnding()) {
            this.setWord(this.getWord() + normalizedChar);
            return false;
        }
        
        // if the last letter was a soft break then we need a new coord set
        if (isSoftBreak()) {
            this.setSoftBreak(false);
            this.position = new PdfWordPosition(location, location);
            this.positions.add(this.position);
        }
        
        this.setWord(this.getWord() + normalizedChar);
        this.setWordNormalised(this.getWordNormalised() + normalizedChar);
        this.position.setEnd(location);
        
        return true;
    }

    /**
     * constructor
     * @param location 
     */
    public PdfWord(PdfCharacter location) {
        this.word = location.getNormalizedCharacter();
        this.wordNormalised = location.getNormalizedCharacter();
        this.position = new PdfWordPosition(location, location);
        this.positions.add(this.position);
    }

    /**
     * to string
     * @return String
     */
    @Override
    public String toString() {
        return this.word;
    }
    
    /**
     * sanitize saved word
     * @param word
     * @return String
     */
    public String prepWordForSave(String word) {
        return word.replaceAll("^[']|[^\\x00-\\x7F]|[']$", "");
    }
    
    /**
     * convert data to JSON object
     * @return JSONObject
     */
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        JSONArray posArr = new JSONArray();
        
        // generate the positional data from array list
        for (int i = 0; i < positions.size(); i++) {
            PdfWordPosition pos = positions.get(i);
            JSONObject posObj = new JSONObject();
            posObj.put("height", pos.getHeight());
            posObj.put("width", pos.getWidth());
            posObj.put("x", pos.getStartX());
            posObj.put("y", pos.getStartY());
            posArr.add(posObj);
        }
        
        // create root JSON
        JSONObject wordObj = new JSONObject();
        wordObj.put("readable", prepWordForSave(getWord()));
        wordObj.put("normalised", prepWordForSave(getWordNormalised()));
        obj.put("word", wordObj);
        obj.put("layout", posArr);
        
        return obj;
    }
            
    
    
}
