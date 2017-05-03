package pl.sternik.mb.linklinks.entities;


public enum LanguageVersion {
	
    POLISH("POLISH"),
    ENGLISH("ENGLISH"),
    SPANISH("SPANISH"),
    RUSSIAN("RUSSIAN"),
    MANDARIN("MANDARIN"), 
    HINDI("HINDI"),
    ARABIC("ARABIC"),
    PORTUGUESE("PORTUGUESE"),
    BENGALI("BENGALI"),
    JAPANESE("JAPANESE"),
    GERMAN("GERMAN"),
    FRENCH("FRENCH"),
    ITALIAN("ITALIAN");    
    
    
    private final String name;

    private LanguageVersion(final String name) {
    	
    	this.name = name.toUpperCase();
    }
    
//    public static Status forName(final String name) {
//        if (name == null) {
//            throw new IllegalArgumentException("Nie mozna nula dla Status");
//        }
//        if (name.equalsIgnoreCase("NOWA")) {
//            return NOWA;
//        } else if (name.equalsIgnoreCase("DO_SPRZEDANIA")) {
//            return Status.DO_SPRZEDANIA;
//        }
//        throw new IllegalArgumentException("Nazwa \"" + name + "\" nie pasuje do zadengo Statusu");
//    }
//    
    
    public String getName() {
        return this.name;
    }
}
