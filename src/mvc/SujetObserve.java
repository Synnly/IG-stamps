package mvc;

import java.util.ArrayList;

public class SujetObserve {

    private ArrayList<Observateur> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observateur observateur) {
        this.observateurs.add(observateur);
    }

    public void notifierObservateurs() {
        for (Observateur observateur : this.observateurs) {
            observateur.reagir();
        }
    }
}
