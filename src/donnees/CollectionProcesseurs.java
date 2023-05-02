package donnees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionProcesseurs extends mvc.SujetObserve{

    private List<Processeur> listeProcesseurs;
    private List<String> listeMarques, listeModeles, listeSockets;
    private List<Integer> listeFrequences, listeNbCoeurs, listeNbThreads, listeCaches, listeAnnees;

    /**
     * Constructeur de la collection de processeurs
     */
    public CollectionProcesseurs() {
        this.listeProcesseurs = new ArrayList<>();
        this.listeMarques = new ArrayList<>();
        this.listeModeles = new ArrayList<>();
        this.listeSockets = new ArrayList<>();
        this.listeFrequences = new ArrayList<>();
        this.listeNbCoeurs = new ArrayList<>();
        this.listeNbThreads = new ArrayList<>();
        this.listeCaches = new ArrayList<>();
        this.listeAnnees = new ArrayList<>();
    }

    /**
     * Ajoute un processeur à la collection
     * @param processeur Le processeur à ajouter
     */
    public void ajouterProcesseur(Processeur processeur) {
        this.listeProcesseurs.add(processeur);
    }

    /**
     * Modifie le processeur passé en paramètre
     * @param processeur Le processeur à modifier
     * @param marque La marque
     * @param modele Le modèle
     * @param socket Le socket
     * @param frequence La fréquence
     * @param nbCoeurs Le nombre de coeurs
     * @param nbThreads Le nombre de threads
     * @param cache La taille du cache en MB
     * @param annee L'année de sortie
     */
    public void modifierProcesseur(Processeur processeur, String marque, String modele, String socket, int frequence, int nbCoeurs, int nbThreads, int cache, int annee) {
        for (Processeur p : this.listeProcesseurs) {
            if (p.getIdentifiant() == processeur.getIdentifiant()) {
                p.setMarque(marque);
                p.setModele(modele);
                p.setSocket(socket);
                p.setFrequence(frequence);
                p.setNbCoeurs(nbCoeurs);
                p.setNbThreads(nbThreads);
                p.setCache(cache);
                p.setAnnee(annee);
            }
        }
    }

    /**
     * Supprime le processeur passé en paramètre
     * @param processeur Le processeur à supprimer
     */
    public void supprimerProcesseur(Processeur processeur) {
        for (Processeur p : this.listeProcesseurs) {
            if (p.getIdentifiant() == processeur.getIdentifiant()) {
                this.listeProcesseurs.remove(p);
            }
        }
    }

    /**
     * Trie la collection de processeurs par année
     * @param sensInverse Si vrai, trie dans l'ordre inverse
     */
    public void trierProcesseurs(boolean sensInverse){
        if (sensInverse) {
            Collections.sort(this.listeProcesseurs, Comparator.comparing(Processeur::getAnnee));
            Collections.reverse(this.listeProcesseurs);
        } else {
            Collections.sort(this.listeProcesseurs, Comparator.comparing(Processeur::getAnnee));
        }
    }

    /**
     * @return Le nombre de processeurs dans la collection
     */
    public int getNbProcesseurs() {
        return this.listeProcesseurs.size();
    }

    /**
     * Exporte la collection de processeurs dans un fichier JSON
     * @param chemin Le chemin du fichier
     */
    public void exporterCollection(String chemin) {
        // TODO
    }

    /**
     * Importe une collection de processeurs depuis un fichier JSON
     * @param chemin Le chemin du fichier
     */
    public void importerCollection(String chemin) {
        // TODO
    }

    /**
     * @param identifiant L'identifiant du processeur
     * @return Le processeur correspondant à l'identifiant
     */
    public Processeur getProcesseur(int identifiant) {
        return this.listeProcesseurs.get(identifiant);
    }

    /**
     * Ajoute une marque à la liste des marques si est n'est pas déjà présente
     * @param marque La marque à ajouter
     */
    public void ajouterMarque(String marque) {
        if (!this.listeMarques.contains(marque)) {
            this.listeMarques.add(marque);
        }
    }

    /**
     * Ajoute un modèle à la liste des modèles si est n'est pas déjà présent
     * @param modele Le modèle à ajouter
     */
    public void ajouterModele(String modele) {
        if (!this.listeModeles.contains(modele)) {
            this.listeModeles.add(modele);
        }
    }

    /**
     * Ajoute un socket à la liste des sockets si est n'est pas déjà présent
     * @param socket Le socket à ajouter
     */
    public void ajouterSocket(String socket) {
        if (!this.listeSockets.contains(socket)) {
            this.listeSockets.add(socket);
        }
    }

    /**
     * Ajoute une fréquence à la liste des fréquences si est n'est pas déjà présente
     * @param frequence La fréquence à ajouter
     */
    public void ajouterFrequence(int frequence) {
        if (!this.listeFrequences.contains(frequence)) {
            this.listeFrequences.add(frequence);
        }
    }

    /**
     * Ajoute un nombre de coeurs à la liste des nombres de coeurs si est n'est pas déjà présent
     * @param nbCoeurs Le nombre de coeurs à ajouter
     */
    public void ajouterNbCoeurs(int nbCoeurs) {
        if (!this.listeNbCoeurs.contains(nbCoeurs)) {
            this.listeNbCoeurs.add(nbCoeurs);
        }
    }

    /**
     * Ajoute un nombre de threads à la liste des nombres de threads si est n'est pas déjà présent
     * @param nbThreads Le nombre de threads à ajouter
     */
    public void ajouterNbThreads(int nbThreads) {
        if (!this.listeNbThreads.contains(nbThreads)) {
            this.listeNbThreads.add(nbThreads);
        }
    }

    /**
     * Ajoute une taille de cache à la liste des tailles de cache si est n'est pas déjà présente
     * @param cache La taille de cache à ajouter
     */
    public void ajouterCache(int cache) {
        if (!this.listeCaches.contains(cache)) {
            this.listeCaches.add(cache);
        }
    }

    /**
     * Ajoute une année à la liste des années si est n'est pas déjà présente
     * @param annee L'année à ajouter
     */
    public void ajouterAnnee(int annee) {
        if (!this.listeAnnees.contains(annee)) {
            this.listeAnnees.add(annee);
        }
    }

}
