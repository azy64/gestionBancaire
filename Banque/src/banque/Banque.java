/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banque;

import controller.banque.com.BanqueController;
import java.util.Scanner;


/**
 *
 * @author AZARIA
 */
public class Banque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //Clients cl=new Clients();
        Scanner sc=new Scanner(System.in);
        BanqueController cntr= new BanqueController();
        cntr.menu(sc);   
        
    }
    
 }
