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
public class BankTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="dateTransac")
    @Temporal(TemporalType.DATE)
    private Date dateTransac;
    @Column(name="montant")
    private double montant;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Clients cleint;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Comptes compte;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Caissiers gestionnaire;
    @ManyToOne(cascade = CascadeType.MERGE)
    private TypeTransac typeTransaction;
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
        if (!(object instanceof BankTransaction)) {
            return false;
        }
        BankTransaction other = (BankTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Date getDateTransac() {
        return dateTransac;
    }

    public void setDateTransac(Date dateTransac) {
        this.dateTransac = dateTransac;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Clients getCleint() {
        return cleint;
    }

    public void setCleint(Clients cleint) {
        this.cleint = cleint;
    }

    public Comptes getCompte() {
        return compte;
    }

    public void setCompte(Comptes compte) {
        this.compte = compte;
    }

    public Caissiers getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Caissiers gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public TypeTransac getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransac typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
    

    @Override
    public String toString() {
        return "bnk.models.com.BankTransaction[ id=" + id + " ]";
    }
    
}
