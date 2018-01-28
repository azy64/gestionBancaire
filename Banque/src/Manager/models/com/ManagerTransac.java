/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import bnk.models.com.BankTransaction;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author AZARIA
 */
public class ManagerTransac extends ManagerModel<BankTransaction>{

    private final String select_all="select u from BankTransaction as u";
    private Query query;

    @Override
    public void creer(BankTransaction ob) {
        EntityTransaction tx=this.getEm().getTransaction();
        try{
            tx.begin();
            this.getEm().persist(ob);
            tx.commit();
            
        }
        catch(Exception e){
            //tx.rollback();
            e.printStackTrace();
        }
        //this.getEm().close();
        //this.getFac().close();
    }

    @Override
    public BankTransaction fetchById(int id) {
        return this.getEm().find(BankTransaction.class, id);
    }

    @Override
    public List<BankTransaction> fetchAll() {
        this.query=this.getEm().createQuery(select_all);
        return this.query.getResultList();
    }

    @Override
    public List<BankTransaction> fetchBy(String attribut, Object ob) {
       String req="select u from BankTransaction as u where u."+attribut+"=:"+attribut;
        this.query=this.getEm().createQuery(req);
        this.query.setParameter(attribut, ob);
        return this.query.getResultList();
    
    }

    @Override
    public boolean update(BankTransaction ob) {
        EntityTransaction tx=this.getEm().getTransaction();
        tx.begin();
        try{
            this.getEm().persist(ob);
            tx.commit();
            return true;
        }
        catch(Exception ex){
            tx.rollback();
            return false;
        }
    }

    @Override
    public void delete(BankTransaction ob) {
        this.getEm().remove(ob);
    }
    
}
