/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import bnk.models.com.Comptes;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author AZARIA
 */
public class ManagerComptes extends ManagerModel<Comptes>{
     private final String select_all="select u from Comptes as u";
    private Query query;

    @Override
    public void creer(Comptes ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityTransaction tx=this.getEm().getTransaction();
        try{
            tx.begin();
            this.getEm().persist(ob);
            tx.commit();
            this.getEm().close();
            this.getFac().close();
        }
        catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Comptes fetchById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.getEm().find(Comptes.class, id);
    }

    @Override
    public List<Comptes> fetchAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.query=this.getEm().createQuery(select_all);
        return this.query.getResultList();
    }

    @Override
    public List<Comptes> fetchBy(String attribut, Object ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="select u from Comptes as u where u."+attribut+"=:"+attribut;
        this.query=this.getEm().createQuery(req);
        this.query.setParameter(attribut, ob);
        return this.query.getResultList();
    }

    @Override
    public boolean update(Comptes ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void delete(Comptes ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.getEm().remove(ob);
    }
    
    public long generateNumCompte(){
        List<Comptes> l=this.fetchAll();
        if(!l.isEmpty()){
            long num=l.get(l.size()-1).getNumCompte();
            return num+1;
        }
        else
            return 100;
    }
}
