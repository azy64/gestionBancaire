/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import bnk.models.com.CategorieCompte;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author AZARIA
 */
public class ManagerCategories extends ManagerModel<CategorieCompte>{

    private final String select_all="select u from CategorieCompte as u";
    private Query query;
    @Override
    public void creer(CategorieCompte ob) {
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
    public CategorieCompte fetchById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         return this.getEm().find(CategorieCompte.class, id);
    }

    @Override
    public List<CategorieCompte> fetchAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.query=this.getEm().createQuery(select_all);
        return this.query.getResultList();
    }

    @Override
    public List<CategorieCompte> fetchBy(String attribut, Object ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="select u from CategorieCompte as u where u."+attribut+"=:"+attribut;
        this.query=this.getEm().createQuery(req);
        this.query.setParameter(attribut, ob);
        return this.query.getResultList();
    }

    @Override
    public boolean update(CategorieCompte ob) {
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
    public void delete(CategorieCompte ob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.getEm().remove(ob);
    }
    
}
