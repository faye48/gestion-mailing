package sn.isi.dao;
import sn.isi.entities.Client;
public interface IClient {
    public  int menu();
    public  void creaction(Client client);
    public  void affichageListClient();
    public  void affichage(Client client);
    public  void Edition(Client client);

    public Client recherche(String email);

}
