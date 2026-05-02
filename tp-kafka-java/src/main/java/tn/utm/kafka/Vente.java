package tn.utm.kafka;

public class Vente {
    private String idClient;
    private double montant;
    private String ville;
    private String produit;

    public Vente() {} // Requis par Jackson

    public Vente(String idClient, double montant, String ville, String produit) {
        this.idClient = idClient;
        this.montant = montant;
        this.ville = ville;
        this.produit = produit;
    }

    // Getters et Setters
    public String getIdClient() { return idClient; }
    public void setIdClient(String idClient) { this.idClient = idClient; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getProduit() { return produit; }
    public void setProduit(String produit) { this.produit = produit; }

    @Override
    public String toString() {
        return String.format("Vente[Client=%s, Montant=%.2f DT, Ville=%s, Produit=%s]", 
                             idClient, montant, ville, produit);
    }
}
