/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.banque.com;

import Manager.models.com.ManagerCaissiers;
import Manager.models.com.ManagerCategories;
import Manager.models.com.ManagerClients;
import Manager.models.com.ManagerComptes;
import Manager.models.com.ManagerTransac;
import Manager.models.com.ManagerTypeTransac;
import bnk.models.com.BankTransaction;
import bnk.models.com.Caissiers;
import bnk.models.com.CategorieCompte;
import bnk.models.com.Clients;
import bnk.models.com.Comptes;
import bnk.models.com.TypeTransac;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityTransaction;

/**
 *
 * @author AZARIA
 */
public class BanqueController {
     private final ManagerTransac mnT=new ManagerTransac();
     private final ManagerTypeTransac managerType=new ManagerTypeTransac();
     private final ManagerComptes managerCompte=new ManagerComptes();
    
    public void traitement(int choix,Scanner sc){
        switch(choix){
            case 1:/*****ici choix==1 pour dire qu'on crée le compte client**/
                System.out.println("vous avez choisi le 1 pour la création de compte:");
                String num;
                /***on recupere le numero du client pour verifier si il existe dans notre systeme**/
                num=(String)Input("string", "Veuillez saisir votre numero de téléphone:");
                ManagerClients mn=new ManagerClients();
                List cls=mn.fetchBy("numberPhone", num.trim());
                int response;
                double mnt;
                Comptes cmp=new Comptes();
                String req="";
                EntityTransaction tx=mn.getEm().getTransaction();
                /**si la liste est vide , alors le client n'est pas enregistré dans le systeme**/
                /*
                *on va créer le client et son compte dans les lignes qui suivent
                */
                if(cls.isEmpty()){
                    System.out.println("creation d'un nouveau Client...");
                    String ch;
                    ch=(String)Input("string", "Entrez vos informations comme suit: nom pos-tnom prenom sexe(M ou F) date de naissance");
                    Clients cl=new Clients();
                    cl.setNumberPhone(num);
                    String[]chs;
                    if(!ch.isEmpty()){
                        try{
                            chs=ch.split(" ");
                        cl.setNom(chs[0]);
                        cl.setPostnom(chs[1]);
                        cl.setPrenom(chs[2]);
                        cl.setSexe(chs[3]);
                        cl.setBirthDay(new Date(chs[4]));
                        mn.creer(cl);
                        cmp.setClient(cl);
                        cmp.setDateCreation(new Date());
                        }
                        catch(Exception in){
                            System.out.println("------------------------------restart Operation------------------------------------");
                            menu(sc);
                        }
                        
                    }
                    
                  
                    mnt=(double)Input("double","Veuillez entrer un montant initial pour le compte, ex:0.00,100.00 ...");
                    
                    ManagerComptes mnC=new ManagerComptes();
                    cmp.setSold(mnt);
                    cmp.setNumCompte(mnC.generateNumCompte());
                    response=(int)Input("int","Veuillez choisir le type de compte:\n 1.PREMIUM avec 0.5 TAUX d'interet \n 2.STANDARD avec 0.3 TAUX d'interet");
                    
                    ManagerCategories mnCat=new ManagerCategories();
                    CategorieCompte cat=mnCat.fetchById(response);
                    cmp.setCategorie(cat);
                    req=(String)Input("string","Veuillez entrer votre matricule Agent:");
                    ManagerCaissiers mnCaissier=new ManagerCaissiers();
                    List<Caissiers> cc=mnCaissier.fetchBy("matricule", req.trim());
                    if(!cc.isEmpty())
                        cmp.setGestionnaire(cc.get(0));
                    else{
                        System.out.println("Matricule de l'agent n'existe pas, vous devez recommencer la procedure!");
                        menu(sc);
                    }
                   
                       // mnC.getEm().persist(cl);
                        mnC.creer(cmp);
                        System.out.println("le compte :"+cmp.getNumCompte()+" avec un solde de "+cmp.getSold()+"\n dont le taux d'interet:"+cmp.getCategorie().getTaux()+" de\n Mr "+cmp.getClient().getNom()+" a été crée avec succès...");
                        
                        /*tx.rollback();
                        System.out.println("le compte Client n°"+cmp.getNumCompte()+" pour le Mr "+cmp.getClient().getNom()+" n'a pas été crée! ");*/
                    
                    System.out.println("-------------------------------------------------------fin de la creation-------------------------------------------");
                    menu(sc);
                    
                }
                /** si le client existe alors le traitement suivant***/
                /*
                *qui va consister seulement à créer son compte
                */
                else{
                    Clients client=(Clients)cls.get(0);
                    cmp.setClient(client);
                    cmp.setDateCreation(new Date());
                    System.out.println("-------------------------------le client existe-----------------------------------");
                    mnt=(double)Input("double","Veuillez entrer un montant initial pour le compte, ex:0.00,100.00 ...");
                    
                    ManagerComptes mnC=new ManagerComptes();
                    cmp.setSold(mnt);
                    cmp.setNumCompte(mnC.generateNumCompte());
                    response=(int)Input("int","Veuillez choisir le type de compte:\n 1.PREMIUM avec 0.5 TAUX d'interet \n 2.STANDARD avec 0.3 TAUX d'interet");
                    ManagerCategories mnCat=new ManagerCategories();
                    CategorieCompte cat=mnCat.fetchById(response);
                    cmp.setCategorie(cat);
                    req=(String)Input("string","Veuillez entrer votre matricule Agent:");
                    ManagerCaissiers mnCaissier=new ManagerCaissiers();
                    List<Caissiers> cc=mnCaissier.fetchBy("matricule", req);
                    if(!cc.isEmpty())
                        cmp.setGestionnaire(cc.get(0));
                    else{
                        System.out.println("Matricule de l'agent n'existe pas, vous devez recommencer la procedure!");
                        menu(sc);
                    }
                   
                    mnC.creer(cmp);
                    System.out.println("le compte :"+cmp.getNumCompte()+" avec un solde de "+cmp.getSold()+"\n dont le taux d'interet:"+cmp.getCategorie().getTaux()+" de\n Mr "+cmp.getClient().getNom()+" a été crée avec succès...");
                             
                    System.out.println("-------------------------------------------------------fin de la creation-------------------------------------------");
                    menu(sc);
                }
                
                System.out.println("pourquoi?");
                break;
            case 2:
                System.out.println("vous avez choisi le 2 qui est la consultation d'un  compte");
                this.consulter();
                this.menu(sc);
                //System.out.println("pourquoi?");
                break;
            case 3:   
                System.out.println("vous avez choisi le 3");
                this.debiter();
                this.menu(sc);
                break;
            case 4:   
                System.out.println("vous avez choisi le 4");
                this.crediter();
                this.menu(sc);
                break;
            case 5:   
                System.out.println("vous avez choisi le 5");
                this.Rechercher();
                this.menu(sc);
                break;
            case 6:   
                System.out.println("vous avez choisi le 6");
                this.calculInteret();
                menu(sc);
                break;   
            case 7:   
                System.out.println("vous avez choisi le 7");
                this.rapport();
                menu(sc);
                break;   
                
            default:
                System.out.println("vous etes en dessous de la moyenne mentale!");
                
        }
        
    }
    public Object Input(String type,String text){
        Scanner sc=new Scanner(System.in);
        int result;
        Object ob=null;
        if(type.equals("int")){
            System.out.println(text);
                //int result;
            try{
                result=sc.nextInt();
            }
            catch(Exception ex){
                System.out.println(text);
                result=sc.nextInt();
            }
            ob= result;
        }
        else if(type.equals("double")){
            System.out.println(text);
            double result1;
            try{
                result1=sc.nextDouble();
            }
            catch(Exception ex){
                System.out.println(text);
                result1=sc.nextDouble();
            }
            ob= result1;
        }
        else if(type.equals("string")){
            System.out.println(text);
            String result2;
            try{
                result2=sc.nextLine();
            }
            catch(Exception ex){
                System.out.println(text);
                result2=sc.nextLine();
            }
            ob= result2;
        }
        else if(type.equals("long")){
            System.out.println(text);
            long result2;
            try{
                result2=sc.nextLong();
            }
            catch(Exception ex){
                System.out.println(text);
                result2=sc.nextLong();
            }
            ob= result2;
        }
        else{
            
        }
       
       return ob; 
    }
    public void consulter(/*Scanner sc*/){
        long numCmp=(long)Input("long", "Entrer le numero de compte à consulter");
        ManagerComptes managerCompte=new ManagerComptes();
        List<Comptes> comptes=managerCompte.fetchBy("numCompte", numCmp);
        /*
        *recupere le compte correspondant au numero de compte entré
        */
        if(!comptes.isEmpty()){
            Comptes cmp=comptes.get(0);
            double t=cmp.getCategorie().getTaux();
            System.out.println("Numero de Compte:"+cmp.getNumCompte()+"\n Type de compte:"+cmp.getCategorie().getLibele()+"\n Taux d'interet:"+t+"\n Solde:"+cmp.getSold());
            System.out.println("Date de creation:"+cmp.getDateCreation()+"");
            System.out.println("Titulaire de Compte:"+cmp.getClient().getNom()+" "+cmp.getClient().getPrenom()+" "+cmp.getClient().getPostnom());
            System.out.println("Numero de Telephone:"+cmp.getClient().getNumberPhone());
            BankTransaction tr=new BankTransaction();
            tr.setCleint(cmp.getClient());
            tr.setCompte(cmp);
            tr.setDateTransac(new Date());
            tr.setGestionnaire(cmp.getGestionnaire());
            tr.setMontant(0.00);
            TypeTransac tp= managerType.fetchById(2);
            tr.setTypeTransaction(tp);
            mnT.creer(tr);
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
        else{
            System.out.println("Le Numero de entré n'exite pas!!!");
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
    }
    public void debiter(){
        double numCmp=(double)Input("double", "Entrer le numero de compte à Debiter");
        ManagerComptes managerCompte=new ManagerComptes();
        List<Comptes> comptes=managerCompte.fetchBy("numCompte", numCmp);
        if(!comptes.isEmpty()){
            Comptes cmp=comptes.get(0);
            /*double t=cmp.getCategorie().getTaux();
            System.out.println("Numero de Compte:"+cmp.getNumCompte()+"\n Type de compte:"+cmp.getCategorie().getLibele()+"\n Taux d'interet:"+t+"\n Solde:"+cmp.getSold());
            System.out.println("Date de creation:"+cmp.getDateCreation()+"");
            System.out.println("Titulaire de Compte:"+cmp.getClient().getNom()+" "+cmp.getClient().getPrenom()+" "+cmp.getClient().getPostnom());
            System.out.println("Numero de Telephone:"+cmp.getClient().getNumberPhone());*/
            double montant=(double)Input("double","Entrez le montant à debiter:");
            if(cmp.getSold()>montant)
                cmp.setSold(cmp.getSold()-montant);
            managerCompte.update(cmp);
            System.out.println("Le compte "+cmp.getNumCompte()+" a été debité de "+montant+"\n d'ou le nouveau solde est:"+cmp.getSold());
            BankTransaction tr=new BankTransaction();
            tr.setCleint(cmp.getClient());
            tr.setCompte(cmp);
            tr.setDateTransac(new Date());
            tr.setGestionnaire(cmp.getGestionnaire());
            tr.setMontant(montant);
            TypeTransac tp= managerType.fetchById(3);
            tr.setTypeTransaction(tp);
            mnT.creer(tr);
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
        else{
            System.out.println("Le Numero de compte entré n'exite pas!!!");
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
    }
    public void crediter(){
        double numCmp=(double)Input("double", "Entrer le numero de compte à Créditer");
        ManagerComptes managerCompte=new ManagerComptes();
        List<Comptes> comptes=managerCompte.fetchBy("numCompte", numCmp);
        if(!comptes.isEmpty()){
            Comptes cmp=comptes.get(0);
            /*double t=cmp.getCategorie().getTaux();
            System.out.println("Numero de Compte:"+cmp.getNumCompte()+"\n Type de compte:"+cmp.getCategorie().getLibele()+"\n Taux d'interet:"+t+"\n Solde:"+cmp.getSold());
            System.out.println("Date de creation:"+cmp.getDateCreation()+"");
            System.out.println("Titulaire de Compte:"+cmp.getClient().getNom()+" "+cmp.getClient().getPrenom()+" "+cmp.getClient().getPostnom());
            System.out.println("Numero de Telephone:"+cmp.getClient().getNumberPhone());*/
            double montant=(double)Input("double","Entrez le montant à créditer:");
            double avant=cmp.getSold();
            if(montant>0)
                cmp.setSold(cmp.getSold()+montant);
            managerCompte.update(cmp);
            System.out.println("Le compte "+cmp.getNumCompte()+" avec un montantde "+avant+" a été crédité de "+montant+"\n d'ou le nouveau solde est:"+cmp.getSold());
            BankTransaction tr=new BankTransaction();
            tr.setCleint(cmp.getClient());
            tr.setCompte(cmp);
            tr.setDateTransac(new Date());
            tr.setGestionnaire(cmp.getGestionnaire());
            tr.setMontant(montant);
            TypeTransac tp= managerType.fetchById(4);
            tr.setTypeTransaction(tp);
            mnT.creer(tr);
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
        else{
            System.out.println("Le Numero de compte entré n'exite pas!!!");
            System.out.println("--------------------------------------------Fin consultation--------------------------------------------");
        }
    }
    public void Rechercher(){
        int idcl=(int)Input("int","Entrer le numero d'identification du client à rechercher");
        List <Comptes>cmps=managerCompte.fetchAll();
        List<Comptes> cmpsl=new LinkedList();
        if(!cmps.isEmpty()){
            for(int i=0;i<cmps.size();i++){
                if(cmps.get(i).getClient().getId().equals(idcl))
                    //cmps.remove(i);
                    cmpsl.add(cmps.get(i));
            }
            System.out.println("voici le nombre de compte associé au client "+idcl+" : "+cmpsl.size());
            System.out.println("Mm/Mr "+cmps.get(0).getClient().getNom()+" "+cmps.get(0).getClient().getPostnom()+" "+cmps.get(0).getClient().getPrenom()+" N°"+cmps.get(0).getClient().getId()+" possede le(s) compte(s) suivant:");
            cmpsl.forEach((cmp) -> {
                System.out.println("le "+cmp.getNumCompte()+" a un solde de "+cmp.getSold()+" qui un compte "+cmp.getCategorie().getLibele()+" avec un Taux d'interet de "+cmp.getCategorie().getTaux()+"--- "+cmp.getClient().getId());
            });
        }
        else{
            System.out.println("Cet identifiant n'existe pas chez nous");
        }
    }
    public void calculInteret(){
        System.out.println("*************************************************************");
        System.out.println("*****CALCUL DE L'INTERET POUR CHAQUE COMPTE DE LA BANQUE*****");
        System.out.println("*************************************************************");
        List <Comptes>cmps=managerCompte.fetchAll();
        cmps.forEach((cmp)->{
            double taux=(cmp.getCategorie().getTaux()*cmp.getSold());
            double sommeTotal=cmp.getSold()+taux;
            System.out.println("Le compte "+cmp.getNumCompte()+" appartenant à "+cmp.getClient().getNom()+" "+cmp.getClient().getPrenom()
            +" dont le solde est de "+ cmp.getSold()+" de Type "+cmp.getCategorie().getLibele()
            +" de Taux d'interet "+cmp.getCategorie().getTaux());
            System.out.println("\t-àprès calcul de l'interet de "+taux+", Le solde Total est de:"+sommeTotal);
           
        });
        System.out.println("-----------------fin du calcul de l'interet------------------");
    }
    public void rapport(){
        String str=(String)Input("string","Entrer une liste des comptes separé par des traits d'unions,ex:100-109-111 ou juste un compte");
        String[]tr;
        String trait="-";
        if(str.contains(trait)){
            tr=str.split(trait);
        }
        else{
            tr=new String[1];
            tr[0]=str;
        }
        List<BankTransaction> transacs=mnT.fetchAll();
         for (String tt : tr) {
             String str1=tt;
             transacs.forEach((transac)->{
             long cc=Long.parseLong(str1);
             if(transac.getCompte().getNumCompte()==cc){
                 Comptes cmp=transac.getCompte();
                 System.out.println("Compte "+cmp.getNumCompte()+" solde "+cmp.getSold()+" date de création "+cmp.getDateCreation() );
                 System.out.println("\t"+transac.getTypeTransaction().getLibele()+"-- "+transac.getMontant()+"--- "+transac.getDateTransac());
             }
                 
             });
         }
        System.out.println("------------------------------fin Rapport---------------------------------------------");
    }
    public void menu(Scanner sc){
        this.init();
        int choix=sc.nextInt();
        this.traitement(choix,sc);
    }
    public void init(){
        System.out.println("*******************************");
        System.out.println("*GESTION DE COMPTE D'UN CLIENT*");
        System.out.println("*******************************");
        System.out.println("Menu:");
        System.out.println("1.CREER UN COMPTE POUR LE CLIENT");
        System.out.println("2.CONSULTER UN COMPTE CLIENT");
        System.out.println("3.DEBITER UN COMPTE CLIENT");
        System.out.println("4.CREDITER UN COMPTE CLIENT");
        System.out.println("5.RECHERCHER UN COMPTE CLIENT");
        System.out.println("6.CALCULE DE L'INTERET POUR TOUS LES COMPTES");
        System.out.println("7.RAPPORT SUR UN OU PLUSIEURS COMPTES");
        System.out.println("8.RETOUR AU MENU");
        System.out.println("ENTRER VOTRE CHOIX ...");
          
    }
}
