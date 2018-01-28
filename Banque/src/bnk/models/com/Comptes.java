/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnk.models.com;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AZARIA
 */
@Entity
public class Comptes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="sold")
    private double sold;
    @Column(name="dateCreation")
    @Temporal( TemporalType.DATE)
    private Date dateCreation;
    @Column(name="numCompte")
    private long numCompte;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Clients client;
    @ManyToOne/*(cascade = {CascadeType.PERSIST,CascadeType.MERGE})*/
    private CategorieCompte categorie;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Caissiers gestionnaire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comptes)) {
            return false;
        }
        Comptes other = (Comptes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date date_cr) {
        this.dateCreation = date_cr;
    }

    public long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(long numCompte) {
        this.numCompte = numCompte;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public CategorieCompte getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieCompte categorie) {
        this.categorie = categorie;
    }

    public Caissiers getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Caissiers gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
    
    

    @Override
    public String toString() {
        return "bnk.models.com.Comptes[ id=" + id + " ]";
    }
    
}
