package sn.isi.dao;

import sn.isi.entities.Client;
import java.sql.ResultSet;
import java.util.Scanner;

public class ClientImpl implements IClient {
    DatabaseHelper bd =  DatabaseHelper.getInstance();

    @Override
    public int menu() {
        int choix = 0;
        Scanner clavier = new Scanner(System.in);
        System.out.println("--------------MAILING--------------");
        System.out.println("1 -  Creation ");
        System.out.println("2 -  Affichage");
        System.out.println("3 -  Modification");
        System.out.println("4 -  Recherche");
        System.out.println("5  - Quitter  ");
        System.out.println("Taper votre choix ");
        choix = clavier.nextInt();
        return choix;



    }

    @Override
    public void creaction(Client client) {
        bd =  DatabaseHelper.getInstance();
        int k = 0;
        String sql = "INSERT INTO client VALUES (null, ?, ?, ?, ?)";
        try {
            bd.myPrepareStatement(sql);
            Object [] par = {client.getNom(),client.getPrenom(),client.getEmail(),client.getTelephone()};
            bd.addParameters(par);

            k =bd.myExecuteUpdate();
        }catch(Exception e){
            System.out.println("Erreur de Connexion addClient");
        }
        // return k;
    }


    @Override
    public void affichageListClient() {
        String	sql = "SELECT * FROM client";

        int i=0;
        try {

            bd.myPrepareStatement(sql);
            ResultSet rs = bd.myExecuteQuery();

            while (rs.next()) {

                Client cl = new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("tel"));
                System.out.println(" CLIENT  "+cl.getId());

                affichage(cl);
                System.out.println("");
                System.out.println("");


            }
            rs.close();

            bd.closeConnection();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }




    @Override
    public void affichage(Client client) {
        System.out.println(" id : "+client.getId());
        System.out.println(" prenom : "+client.getNom());
        System.out.println(" nom : "+client.getPrenom());
        System.out.println(" email: "+client.getEmail());
        System.out.println(" telephone: "+client.getTelephone());


    }

    @Override
    public void Edition(Client client) {
        bd =  DatabaseHelper.getInstance();
        int k = 0;
        String sql = "UPDATE client SET nom = ?, prenom = ?, email = ?,tel= ? WHERE id = ? ";

        System.out.println(sql);
        try {
            bd.myPrepareStatement(sql);
            Object [] par = {client.getNom(),client.getPrenom(),client.getEmail(),client.getTelephone(),client.getId()};
            bd.addParameters(par);

            k =bd.myExecuteUpdate();
        }catch(Exception e){
            System.out.println("Erreur de Connexion addClient");
        }
    }


    @Override
    public Client recherche(String email) {
        bd =  DatabaseHelper.getInstance();
        int k = 0;
        String sql = "SELECT * FROM client WHERE email=?";
        try {
            bd.myPrepareStatement(sql);
            Object [] par = {email};
            bd.addParameters(par);

            ResultSet rs = bd.myExecuteQuery();
            Client cl;
            while (rs.next()) {

                cl = new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("tel"));

                return cl;
            }
            rs.close();


        }catch(Exception e){
            System.out.println("Erreur de Connexion addClient");
        }
        return null;

    }



    }



