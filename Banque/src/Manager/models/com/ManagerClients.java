/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import bnk.models.com.Clients;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author AZARIA
 */
public class ManagerClients extends ManagerModel<Clients>{
    
    private final String select_all="select u from Clients as u";
    private Query query;

    @Override
    public void creer(Clients cl) {
        EntityTransaction tx=this.getEm().getTransaction();
        try{
            tx.begin();
            this.getEm().persist(cl);
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
    public Clients fetchById(int id) {
        return this.getEm().find(Clients.class, id);
    }

    @Override
    public List<Clients> fetchAll() {
        //return this.getEm().createQuery("select * from clients").
        this.query=this.getEm().createQuery(select_all);
        return this.query.getResultList();
        
    }

    @Override
    public void delete(Clients ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.getEm().remove(ob);
    }

    @Override
    public List<Clients> fetchBy(String attribut, Object ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="select u from Clients as u where u."+attribut+"=:"+attribut;
        this.query=this.getEm().createQuery(req);
        this.query.setParameter(attribut, ob);
        return this.query.getResultList();
    }

    @Override
    public boolean update(Clients ob) {
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

    
    
}
