package donnees;

public class Processeur {
    private int identifiant;
    private String marque;
    private String modele;
    private String socket;
    private int frequence;
    private int nbCoeurs;
    private int nbThreads;
    private int cache;
    private int annee;

    /**
     * Constructeur complet d'un processeur
     * @param marque La marque
     * @param modele Le modèle
     * @param socket Le socket
     * @param frequence La fréquence
     * @param nbCoeurs Le nombre de coeurs
     * @param nbThreads Le nombre de threads
     * @param cache La taille du cache en MB
     * @param annee L'année de sortie
     */
    public Processeur(String marque, String modele, String socket, int frequence, int nbCoeurs, int nbThreads, int cache, int annee) {
        this.identifiant = FabriqueIdentifiant.getIntance().getIdentifiant();
        this.marque = marque;
        this.modele = modele;
        this.socket = socket;
        this.frequence = frequence;
        this.nbCoeurs = nbCoeurs;
        this.nbThreads = nbThreads;
        this.cache = cache;
        this.annee = annee;
    }

    /**
     * Constructeur minimal d'un processeur
     * @param marque La marque
     * @param modele Le modèle
     */
    public Processeur (String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }

    /**
     * @return L'identifiant du processeur
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * @return La marque du processeur
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Modifie la marque du processeur
     * @param marque La marque du processeur
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return Le modèle du processeur
     */
    public String getModele() {
        return modele;
    }

    /**
     * Modifie le modèle du processeur
     * @param modele Le modèle du processeur
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * @return Le socket du processeur
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Modifie le socket du processeur
     * @param socket Le socket du processeur
     */
    public void setSocket(String socket) {
        this.socket = socket;
    }

/**
     * @return La fréquence du processeur
     */
    public int getFrequence() {
        return frequence;
    }

    /**
     * Modifie la fréquence du processeur
     * @param frequence La fréquence du processeur
     */
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    /**
     * @return Le nombre de coeurs du processeur
     */
    public int getNbCoeurs() {
        return nbCoeurs;
    }

    /**
     * Modifie le nombre de coeurs du processeur
     * @param nbCoeurs Le nombre de coeurs du processeur
     */
    public void setNbCoeurs(int nbCoeurs) {
        this.nbCoeurs = nbCoeurs;
    }

    /**
     * @return Le nombre de threads du processeur
     */
    public int getNbThreads() {
        return nbThreads;
    }

    /**
     * Modifie le nombre de threads du processeur
     * @param nbThreads Le nombre de threads du processeur
     */
    public void setNbThreads(int nbThreads) {
        this.nbThreads = nbThreads;
    }

    /**
     * @return La taille du cache du processeur
     */
    public int getCache() {
        return cache;
    }

    /**
     * Modifie la taille du cache du processeur
     * @param cache La taille du cache du processeur
     */
    public void setCache(int cache) {
        this.cache = cache;
    }

    /**
     * @return L'année de sortie du processeur
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Modifie l'année de sortie du processeur
     * @param annee L'année de sortie du processeur
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
