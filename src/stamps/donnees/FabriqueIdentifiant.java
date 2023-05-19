package stamps.donnees;

public class FabriqueIdentifiant {
    private int identifiant = 0;
    private static FabriqueIdentifiant intance = new FabriqueIdentifiant();

    public static FabriqueIdentifiant getIntance() {return intance;}

    private FabriqueIdentifiant(){
        this.identifiant = 0;
    }

    public int getIdentifiant(){
        return identifiant++;
    }
}
