package donnees;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import mvc.SujetObserve;

import java.util.*;

public class CollectionProcesseurs extends SujetObserve implements Iterable<Processeur>{

    private List<Processeur> listeProcesseurs;
    private List<String> listeMarques, listeModeles, listeSockets;
    private List<Integer> listeNbCoeurs, listeNbThreads, listeCaches, listeAnnees;
    private List<Float> listeFrequences;
    private HashMap<Processeur, Image> petitesImages;
    private HashMap<Processeur, Image> images;
    private boolean triCroissant = true;
    private boolean estTrie = false;
    private boolean enVueDetails = false;
    private Processeur processeurEnVueDetails = null;
    private Image imageProcesseurEnVueDetails = null;
    private boolean modeConsultation = true;
    private boolean fenetreAjoutEstVisible = false;
    private int tailleImage = 300;
    private boolean enCoursDeModification = false;
    private IntegerProperty nbProcesseurs;
    private IntegerProperty indiceProcesseurEnVueDetails;

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
        petitesImages = new HashMap<>();
        images = new HashMap<>();
        nbProcesseurs = new SimpleIntegerProperty(0);
        indiceProcesseurEnVueDetails = new SimpleIntegerProperty(0);
    }

    /**
     * Ajoute un processeur à la collection
     * @param processeur Le processeur à ajouter
     */
    public void ajouterProcesseur(Processeur processeur) {
        estTrie = false;
        listeProcesseurs.add(processeur);
        nbProcesseurs.set(nbProcesseurs.get()+1);

        // Ajout des stamps.tags
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
    public void modifierProcesseur(Processeur processeur, String marque, String modele, String socket, float frequence, int nbCoeurs, int nbThreads, int cache, int annee) {
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
        if (listeProcesseurs.contains(processeur)){
            nbProcesseurs.setValue(nbProcesseurs.getValue() - 1);
        }
        listeProcesseurs.removeIf(p -> p.getIdentifiant() == processeur.getIdentifiant());
    }

    /**
     * Trie la collection de processeurs par marque puis par modèle
     */
    public void trierProcesseurs(){
        if (estTrie) {
            Collections.reverse(listeProcesseurs);
            triCroissant = !triCroissant;
        }
        else {
            Collections.sort(listeProcesseurs, new Comparator<Processeur>() {
                public int compare(Processeur p1, Processeur p2) {
                    // Comparaison des marques
                    String x1 = p1.getMarque();
                    String x2 = p2.getMarque();
                    int sComp = x1.compareTo(x2);
                    if (sComp != 0) {return sComp;}

                    // Comparaison des modèles
                    else {
                        String y1 = p1.getModele();
                        String y2 = p2.getModele();
                        return y1.compareTo(y2);
                    }
                }
            });
            triCroissant = true;
            estTrie = true;
        }
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
            listeMarques.sort(String::compareToIgnoreCase);
        }
    }

    /**
     * Ajoute un modèle à la liste des modèles si est n'est pas déjà présent
     * @param modele Le modèle à ajouter
     */
    public void ajouterModele(String modele) {
        if (!listeModeles.contains(modele)) {
            listeModeles.add(modele);
            listeModeles.sort(String::compareToIgnoreCase);
        }
    }

    /**
     * Ajoute un socket à la liste des sockets si est n'est pas déjà présent
     * @param socket Le socket à ajouter
     */
    public void ajouterSocket(String socket) {
        if (!listeSockets.contains(socket)) {
            listeSockets.add(socket);
            listeSockets.sort(String::compareToIgnoreCase);
        }
    }

    /**
     * Ajoute une fréquence à la liste des fréquences si est n'est pas déjà présente
     * @param frequence La fréquence à ajouter
     */
    public void ajouterFrequence(float frequence) {
        if (!listeFrequences.contains(frequence)) {
            listeFrequences.add(frequence);
            listeFrequences.sort(Float::compareTo);
        }
    }

    /**
     * Ajoute un nombre de coeurs à la liste des nombres de coeurs si est n'est pas déjà présent
     * @param nbCoeurs Le nombre de coeurs à ajouter
     */
    public void ajouterNbCoeurs(int nbCoeurs) {
        if (!listeNbCoeurs.contains(nbCoeurs)) {
            listeNbCoeurs.add(nbCoeurs);
            listeNbCoeurs.sort(Integer::compareTo);
        }
    }

    /**
     * Ajoute un nombre de threads à la liste des nombres de threads si est n'est pas déjà présent
     * @param nbThreads Le nombre de threads à ajouter
     */
    public void ajouterNbThreads(int nbThreads) {
        if (!listeNbThreads.contains(nbThreads)) {
            listeNbThreads.add(nbThreads);
            listeNbThreads.sort(Integer::compareTo);
        }
    }

    /**
     * Ajoute une taille de cache à la liste des tailles de cache si est n'est pas déjà présente
     * @param cache La taille de cache à ajouter
     */
    public void ajouterCache(int cache) {
        if (!listeCaches.contains(cache)) {
            listeCaches.add(cache);
            listeCaches.sort(Integer::compareTo);
        }
    }

    /**
     * Ajoute une année à la liste des années si est n'est pas déjà présente
     * @param annee L'année à ajouter
     */
    public void ajouterAnnee(int annee) {
        if (!listeAnnees.contains(annee)) {
            listeAnnees.add(annee);
            listeAnnees.sort(Integer::compareTo);
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

    /**
     * Associe une petite image au processeur. Si une image est déjà associée, elle est remplacée
     * @param image L'image à associer
     * @param proc Le processeur
     */
    public void ajouterPetiteImage(Image image, Processeur proc){
        petitesImages.put(proc, image);
    }

    @Override
    /**
     * @return Un itérateur sur la collection de processeurs
     */
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

    /**
     * Retourne la petite image associée au processeur
     * @param proc Le processeur
     * @return L'image associée au processeur
     */
    public Image getPetiteImage(Processeur proc){return petitesImages.get(proc);}

    /**
     * @return true si la vue des details est visible, false sinon
     */
    public boolean estEnVueDetails(){return enVueDetails;}

    /**
     * Passe la vue en vue générale
     */
    public void passerEnVueGenerale(){enVueDetails = false;}

    /**
     * Passe la vue en vue des détails
     */
    public void passerEnVueDetails(){enVueDetails = true;}

    /**
     * Modifie le processeur inspecté dans la vue des détails
     * @param proc Le processeur à inspecter
     */
    public void inspecterProcesseur(Processeur proc){
        processeurEnVueDetails = proc;
        setImageProcesseurEnVueDetails(getImage(proc));
    }

    /**
     * @return Le processeur inspecté dans la vue des détails
     */
    public Processeur getProcesseurEnVueDetails(){return processeurEnVueDetails;}

    /**
     * Passe en mode consultation
     */
    public void modeConsultation(){modeConsultation = true;}

    /**
     * Passe en mode édition
     */
    public void modeEdition(){modeConsultation = false;}

    /**
     *
     * @return True si le mode est consultation, false sinon
     */
    public boolean estEnModeConsultation(){return modeConsultation;}

    /**
     * Passe le processeur en vue des détails au processeur suivant s'il y en a un après lui dans la liste
     */
    public void suivant(){
        if (listeProcesseurs.indexOf(processeurEnVueDetails) < getNbProcesseurs()-1) {
            int indice = listeProcesseurs.indexOf(processeurEnVueDetails);
            inspecterProcesseur(listeProcesseurs.get(indice + 1));
            setIndiceProcesseurEnVueDetailsProperty(indice + 1);
        }
    }

    /**
     * Passe le processeur en vue des détails au processeur précédent s'il y en a un avant lui dans la liste
     */
    public void precedent(){
        if(listeProcesseurs.indexOf(processeurEnVueDetails) > 0) {
            int indice = listeProcesseurs.indexOf(processeurEnVueDetails);
            inspecterProcesseur(listeProcesseurs.get(indice - 1));
            setIndiceProcesseurEnVueDetailsProperty(indice - 1);
        }
    }

    /**
     * @return La taille de l'image à afficher dans la vue des détails
     */
    public int getTailleImage(){return tailleImage;}

    /**
     * @return true si des modifications sont en cours, false sinon
     */
    public boolean estEnCoursDeModification() {return enCoursDeModification;}

    /**
     * Démarre les modifications
     */
    public void commencerModifications() {enCoursDeModification = true;}

    /**
     * Termine les modifications
     */
    public void terminerModifications() {enCoursDeModification = false;}

    /**
     * @return La propriété du nombre de processeurs
     */
    public IntegerProperty getPropertyNbProcesseurs() {return nbProcesseurs;}

    /**
     * @return Le nombre de processeurs
     */
    public int getNbProcesseurs() {return nbProcesseurs.intValue();}

    /**
     * @return La propriété de l'indice du processeur en vue des détails
     */
    public IntegerProperty getIndiceProcesseurEnVueDetailsProperty (){return indiceProcesseurEnVueDetails;}

    /**
     * @return L'indice du processeur en vue des détails
     */
    public int getIndiceProcesseurEnVueDetails (){return indiceProcesseurEnVueDetails.intValue();}

    /**
     * Modifie l'indice du processeur en vue des détails
     * @param indice Le nouvel indice
     */
    public void setIndiceProcesseurEnVueDetailsProperty (int indice){indiceProcesseurEnVueDetails.setValue(indice);}

    /**
     * @return La liste des marques présentes dans la base de données
     */
    public List<String> getListeMarques() {return listeMarques;}

    /**
     * @return La liste des modeles présents dans la base de données
     */
    public List<String> getListeModeles() {return listeModeles;}

    /**
     * @return La liste des sockets présents dans la base de données
     */
    public List<String> getListeSockets() {return listeSockets;}

    /**
     * @return La liste des fréquences présentes dans la base de données
     */
    public List<Float> getListeFrequence() {return listeFrequences;}

    /**
     * @return La liste des nombres de coeurs présents dans la base de données
     */
    public List<Integer> getListeNbCoeurs() {return listeNbCoeurs;}

    /**
     * @return La liste des nombres de threads présents dans la base de données
     */
    public List<Integer> getListeNbThreads() {return listeNbThreads;}

    /**
     * @return La liste des caches présents dans la base de données
     */
    public List<Integer> getListeCache() {return listeCaches;}

    /**
     * @return La liste des années présentes dans la base de données
     */
    public List<Integer> getListeAnnees() {return listeAnnees;}

    /**
     * @return La liste des fréquences en chaine de caractères
     */
    public List<String> getlisteFrequenceAsString() {
    	List<String> listeFrequenceAsString = new ArrayList<>();
    	for (Float f : listeFrequences) {
    		listeFrequenceAsString.add(f.toString());
    	}
    	return listeFrequenceAsString;
    }

    /**
     * @return La liste des nombres de coeurs en chaine de caractères
     */
    public List<String> getlisteNbCoeursAsString() {
    	List<String> listeNbCoeursAsString = new ArrayList<>();
    	for (Integer i : listeNbCoeurs) {
    		listeNbCoeursAsString.add(i.toString());
    	}
    	return listeNbCoeursAsString;
    }

    /**
     * @return La liste des nombres de threads en chaine de caractères
     */
    public List<String> getlisteNbThreadsAsString() {
    	List<String> listeNbThreadsAsString = new ArrayList<>();
    	for (Integer i : listeNbThreads) {
    		listeNbThreadsAsString.add(i.toString());
    	}
    	return listeNbThreadsAsString;
    }

    /**
     * @return La liste des caches en chaine de caractères
     */
    public List<String> getlisteCacheAsString() {
    	List<String> listeCacheAsString = new ArrayList<>();
    	for (Integer i : listeCaches) {
    		listeCacheAsString.add(i.toString());
    	}
    	return listeCacheAsString;
    }

    /**
     * @return La liste des années en chaine de caractères
     */
    public List<String> getlisteAnneesAsString() {
    	List<String> listeAnneesAsString = new ArrayList<>();
    	for (Integer i : listeAnnees) {
    		listeAnneesAsString.add(i.toString());
    	}
    	return listeAnneesAsString;
    }

    /**
     * @return L'image du processeur en vue des détails
     */
    public Image getImageProcesseurEnVueDetails() {return imageProcesseurEnVueDetails;}

    /**
     * Modifie l'image du processeur en vue des détails
     * @param image La nouvelle image
     */
    public void setImageProcesseurEnVueDetails(Image image) {imageProcesseurEnVueDetails = image;}
}
