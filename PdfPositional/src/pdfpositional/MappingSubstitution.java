package pdfpositional;

/**
 *
 * @author jonny
 */
public class MappingSubstitution extends Mapping {
    private static final MappingSubstitution INSTANCE = new MappingSubstitution();

    /**
     * constructor
     */
    private MappingSubstitution() {
    }

    /**
     * singleton pattern getInstance
     * @return 
     */
    public static MappingSubstitution getInstance() {
        return INSTANCE;
    }

}
