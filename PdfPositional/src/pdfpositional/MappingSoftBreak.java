package pdfpositional;

/**
 *
 * @author jonny
 */
public class MappingSoftBreak extends Mapping {
    private static final MappingSoftBreak INSTANCE = new MappingSoftBreak();

    /**
     * constructor
     */
    private MappingSoftBreak() {
    }

    /**
     * singleton pattern getInstance
     * @return 
     */
    public static MappingSoftBreak getInstance() {
        return INSTANCE;
    }

}
