package donnees;

import javafx.scene.image.Image;
import mvc.SujetObserve;

import java.util.*;

public class CollectionProcesseurs extends SujetObserve implements Iterable<Processeur>{

    private List<Processeur> listeProcesseurs;
    private List<String> listeMarques, listeModeles, listeSockets;
    private List<Integer> listeFrequences, listeNbCoeurs, listeNbThreads, listeCaches, listeAnnees;
    private HashMap<Processeur, Image> images = new HashMap<>();

    private boolean fenetreAjoutEstVisible = false;

    /**
     * Constructeur de la collection de processeurs
     */
    public CollectionProcesseurs() {
        listeProcesseurs = new ArrayList<>();
        listeMarques = new ArrayList<>();
        listeModeles = new ArrayList<>();
        listeSockets = new ArrayList<>();
        listeFrequences = new ArrayList<>();
        listeNbCoeurs = new ArrayList<>();
        listeNbThreads = new ArrayList<>();
        listeCaches = new ArrayList<>();
        listeAnnees = new ArrayList<>();
        images = new HashMap<>();
    }

    /**
     * Ajoute un processeur à la collection
     * @param processeur Le processeur à ajouter
     */
    public void ajouterProcesseur(Processeur processeur) {
        listeProcesseurs.add(processeur);

        // Ajout des tags
        ajouterMarque(processeur.getMarque());
        ajouterModele(processeur.getModele());
        ajouterSocket(processeur.getSocket());
        ajouterFrequence(processeur.getFrequence());
        ajouterNbCoeurs(processeur.getNbCoeurs());
        ajouterNbThreads(processeur.getNbThreads());
        ajouterCache(processeur.getCache());
        ajouterAnnee(processeur.getAnnee());
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
        for (Processeur p : listeProcesseurs) {
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
        for (Processeur p : listeProcesseurs) {
            if (p.getIdentifiant() == processeur.getIdentifiant()) {
                listeProcesseurs.remove(p);
            }
        }
    }

    /**
     * Trie la collection de processeurs par année
     * @param sensInverse Si vrai, trie dans l'ordre inverse
     */
    public void trierProcesseurs(boolean sensInverse){
        if (sensInverse) {
            Collections.sort(listeProcesseurs, Comparator.comparing(Processeur::getAnnee));
            Collections.reverse(listeProcesseurs);
        } else {
            Collections.sort(listeProcesseurs, Comparator.comparing(Processeur::getAnnee));
        }
    }

    /**
     * @return Le nombre de processeurs dans la collection
     */
    public int getNbProcesseurs() {
        return listeProcesseurs.size();
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
        return listeProcesseurs.get(identifiant);
    }

    /**
     * Ajoute une marque à la liste des marques si est n'est pas déjà présente
     * @param marque La marque à ajouter
     */
    public void ajouterMarque(String marque) {
        if (!listeMarques.contains(marque)) {
            listeMarques.add(marque);
        }
    }

    /**
     * Ajoute un modèle à la liste des modèles si est n'est pas déjà présent
     * @param modele Le modèle à ajouter
     */
    public void ajouterModele(String modele) {
        if (!listeModeles.contains(modele)) {
            listeModeles.add(modele);
        }
    }

    /**
     * Ajoute un socket à la liste des sockets si est n'est pas déjà présent
     * @param socket Le socket à ajouter
     */
    public void ajouterSocket(String socket) {
        if (!listeSockets.contains(socket)) {
            listeSockets.add(socket);
        }
    }

    /**
     * Ajoute une fréquence à la liste des fréquences si est n'est pas déjà présente
     * @param frequence La fréquence à ajouter
     */
    public void ajouterFrequence(int frequence) {
        if (!listeFrequences.contains(frequence)) {
            listeFrequences.add(frequence);
        }
    }

    /**
     * Ajoute un nombre de coeurs à la liste des nombres de coeurs si est n'est pas déjà présent
     * @param nbCoeurs Le nombre de coeurs à ajouter
     */
    public void ajouterNbCoeurs(int nbCoeurs) {
        if (!listeNbCoeurs.contains(nbCoeurs)) {
            listeNbCoeurs.add(nbCoeurs);
        }
    }

    /**
     * Ajoute un nombre de threads à la liste des nombres de threads si est n'est pas déjà présent
     * @param nbThreads Le nombre de threads à ajouter
     */
    public void ajouterNbThreads(int nbThreads) {
        if (!listeNbThreads.contains(nbThreads)) {
            listeNbThreads.add(nbThreads);
        }
    }

    /**
     * Ajoute une taille de cache à la liste des tailles de cache si est n'est pas déjà présente
     * @param cache La taille de cache à ajouter
     */
    public void ajouterCache(int cache) {
        if (!listeCaches.contains(cache)) {
            listeCaches.add(cache);
        }
    }

    /**
     * Ajoute une année à la liste des années si est n'est pas déjà présente
     * @param annee L'année à ajouter
     */
    public void ajouterAnnee(int annee) {
        if (!listeAnnees.contains(annee)) {
            listeAnnees.add(annee);
        }
    }

    /**
     * Associe une image au processeur. Si une image est déjà associée, elle est remplacée
     * @param image L'image à associer
     * @param proc Le processeur
     */
    public void ajouterImage(Image image, Processeur proc){
        images.put(proc, image);
    }

    @Override
    public Iterator<Processeur> iterator() {
        return listeProcesseurs.iterator();
    }

    /**
     * Cache la fenêtre d'ajout
     */
    public void masquerFenetreAjout(){fenetreAjoutEstVisible = false;}

    /**
     * Affiche la fenêtre d'ajout
     */
    public void afficherFenetreAjout(){fenetreAjoutEstVisible = true;}

    /**
     * @return true si la fenêtre d'ajout est visible, false sinon
     */
    public boolean fenetreAjoutEstVisible(){return fenetreAjoutEstVisible;}

    /**
     * Retourne l'image associée au processeur
     * @param proc Le processeur
     * @return L'image associée au processeur
     */
    public Image getImage(Processeur proc){return images.get(proc);}
}
