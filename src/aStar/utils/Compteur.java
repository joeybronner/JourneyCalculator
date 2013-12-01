package aStar.utils;

public class Compteur {

        private long debut = 0;
        private long fin = 0;
        private boolean compte = false;


        public void demarrer()
        {
                this.debut = System.currentTimeMillis();
                this.compte = true;
        }


        public void arreter()
        {
                this.fin = System.currentTimeMillis();
                this.compte = false;
        }


        // Durée en millisecondes
        public long getDureeMilliSec()
        {
                long duree;
                if (compte) {
                        duree = (System.currentTimeMillis() - debut);
                }
                else {
                        duree = (fin - debut);
                }
                return duree;
        }


        // Durée en secondes
        public long getDureeSec() {
                long duree;
                if (compte) {
                        duree = ((System.currentTimeMillis() - debut) / 1000);
                }
                else {
                        duree = ((fin - debut) / 1000);
                }
                return duree;
        }
}