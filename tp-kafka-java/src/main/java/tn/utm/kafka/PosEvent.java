package tn.utm.kafka;

import java.util.List;

public class PosEvent {
    private String type; // VENTE, RETOUR, OUVERTURE
    private String idCaisse;
    private String ville;
    private String timestamp;
    private double montant;
    private List<String> produits;

    public PosEvent() {}

    public PosEvent(String type, String idCaisse, String ville, String timestamp, double montant, List<String> produits) {
        this.type = type;
        this.idCaisse = idCaisse;
        this.ville = ville;
        this.timestamp = timestamp;
        this.montant = montant;
        this.produits = produits;
    }

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getIdCaisse() { return idCaisse; }
    public void setIdCaisse(String idCaisse) { this.idCaisse = idCaisse; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public List<String> getProduits() { return produits; }
    public void setProduits(List<String> produits) { this.produits = produits; }

    @Override
    public String toString() {
        return "PosEvent{" +
                "type='" + type + '\'' +
                ", ville='" + ville + '\'' +
                ", montant=" + montant +
                '}';
    }
}
