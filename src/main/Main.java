package main;
import sn.isi.dao.BD;
import sn.isi.entities.Client;
import sn.isi.dao.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws Exception {


        BD bd  = new BD();
        bd.getConnection();
        Scanner sc=new Scanner(System.in);
        int choix=0;
        ClientImpl controll = new ClientImpl();

        do {
    choix =controll.menu();
            switch (choix) {
                case 1:
                    Client cl = new Client();
                    System.out.println("entrer le nom du client");
                    cl.setNom(sc.nextLine());
                    System.out.println("entrer le prenom du client");
                    cl.setPrenom(sc.nextLine());

                    System.out.println("entrer l'email du client");
                    cl.setEmail(sc.nextLine());
                    System.out.println("entrer le numero du client");
                    cl.setTelephone(sc.nextLine());
                    controll.creaction(cl);
                    break;
                case 2:
                    System.out.println("Affichage");
                    controll.affichageListClient();

                    break;
                case 3:
                    System.out.println("Modification");

                    System.out.println("veuillez saisir le mail du client a modifier");
                    String email;
                    email= sc.nextLine();
                    Client cl1 = controll.recherche(email);
                    if (cl1 != null){
                        System.out.println("entrer le nouveau nom du client");
                        cl1.setNom(sc.nextLine());
                        System.out.println("entrer le nouveau prenom du client");
                        cl1.setPrenom(sc.nextLine());
                        System.out.println("entrer le nouveau mail du client");
                        cl1.setEmail(sc.nextLine());
                        System.out.println("entrer le nouveau telephone du client");
                        cl1.setTelephone(sc.nextLine());
                        controll.Edition(cl1);

                    }


                    break;
                case 4:
                    System.out.println("recherche");
                    String mail;

                    System.out.println("veillez saisir  l'email");
                    mail= sc.nextLine();
                    Client cl2 = controll.recherche(mail);
                    controll.affichage(cl2);


                    break;
                case 5:
                    break;
                default:
                    System.out.println("veuillez  faire votre choix");
                    break;
            }
        }while (choix != 5);
    }
}
