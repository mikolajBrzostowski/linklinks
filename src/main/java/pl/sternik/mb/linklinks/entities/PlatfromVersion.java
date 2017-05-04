package pl.sternik.mb.linklinks.entities;


public enum PlatfromVersion {
    
    PC("PC"),
    PS4("PS4"),
    PS3("PS3"),
    PS2("PS2"),
    PS1("PS1"),
    PSVITA("PSVITA"),
    XBOXONE("XBOXONE"),
    XBOX360("XBOX360"),
    XBOX("XBOX"),
    WII("WII"),
    NDS("NDS"),
    WIIU("WIIU"),
    N3DS("N3DS"),
    GBA("GBA"),
    GCN("GCN");
	
	public static final PlatfromVersion[] ALL = { 
			PC,
		    PS4,
		    PS3,
		    PS2,
		    PS1,
		    PSVITA,
		    XBOXONE,
		    XBOX360,
		    XBOX,
		    WII,
		    NDS,
		    WIIU,
		    N3DS,
		    GBA,
		    GCN		};
    
    
  
    private final String name;

    private PlatfromVersion(final String name) {
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
