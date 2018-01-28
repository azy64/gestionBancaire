/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import bnk.models.com.TypeTransac;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author AZARIA
 */
public class ManagerTypeTransac extends ManagerModel<TypeTransac>{

    private final String select_all="select u from TypeTransac as u";
    private Query query;
    @Override
    public void creer(TypeTransac ob) {
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
    public TypeTransac fetchById(int id) {
        return this.getEm().find(TypeTransac.class, id);
    }

    @Override
    public List<TypeTransac> fetchAll() {
       this.query=this.getEm().createQuery(select_all);
        return this.query.getResultList();
    }

    @Override
    public List<TypeTransac> fetchBy(String attribut, Object ob) {
        String req="select u from TypeTransac as u where u."+attribut+"=:"+attribut;
        this.query=this.getEm().createQuery(req);
        this.query.setParameter(attribut, ob);
        return this.query.getResultList();
    }

    @Override
    public boolean update(TypeTransac ob) {
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
    public void delete(TypeTransac ob) {
        this.getEm().remove(ob);
    }
    
}
