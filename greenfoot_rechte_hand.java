    public void act() {
        if (gesteinVorhanden()) {
            nachricht("Gestein gefunden !!");
            return; // Wenn Gestein da ist, nichts tun
        }

        if (!huegelVorhanden("rechts")) {
            // Rechts frei → zuerst rechts abbiegen
            drehe("rechts");
            if (!huegelVorhanden("vorne")) {
                fahre();
            }
        } else if (!huegelVorhanden("vorne")) {
            // Vorne frei → geradeaus
            fahre();
        } else if (!huegelVorhanden("links")) {
            // Links frei → nach links drehen
            drehe("links");
            if (!huegelVorhanden("vorne")) {
                fahre();
            }
        } else {
            // Überall blockiert → umdrehen
            drehe("rechts");
            drehe("rechts");
            if (!huegelVorhanden("vorne")) {
                fahre();
            }
        }
    }
