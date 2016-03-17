package pdfpositional;

/**
 *
 * @author jonny
 */
public class MappingPunctuation extends Mapping {
    private static final MappingPunctuation INSTANCE = new MappingPunctuation();

    /**
     * constructor
     */
    private MappingPunctuation() {
    }

    /**
     * singleton pattern getInstance
     * @return 
     */
    public static MappingPunctuation getInstance() {
        return INSTANCE;
    }

}
