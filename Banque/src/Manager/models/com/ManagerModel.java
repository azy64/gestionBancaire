/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.models.com;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AZARIA
 */
public abstract class ManagerModel<T> {
    private final EntityManagerFactory fac=Persistence.createEntityManagerFactory("BanquePU");
    private final EntityManager em=fac.createEntityManager();

    public EntityManagerFactory getFac() {
        return fac;
    }

    public EntityManager getEm() {
        return em;
    }
    
    public abstract void creer(T ob);
    public abstract T fetchById(int id);
    public abstract List<T> fetchAll();
    public abstract List<T> fetchBy(String attribut,Object ob);
    public abstract boolean update(T ob);
    public abstract void delete(T ob);
    
}
