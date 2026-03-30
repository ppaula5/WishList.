package WishList;
import processing.core.PApplet;

public class DataBaseClasseConnectionTest extends PApplet {

    public static DataBase db;

    public static void main(String[] args) {

        db = new DataBase("admin", "12345", "WishList");
        db.connect();

        //Numero de clients
        int n = db.getNumFilesTaula("Usuari");
        System.out.printf("Hi ha %d clients.\n", n);

        //Nom del client amb dni = 41624803R
        String nomClient = db.getNomClientAmbPASSWORD("123456");
        System.out.println(nomClient);

        //Noms de tots els clients ordenats alfabèticament
        String[] users = db.getNomTotsClients();
        db.printArray1D(users);

        //Tota la informació de la taula clients
        String[][] infoClients=db.getInfoTotsClients();
        db.printArray2D(infoClients);

        String[] user = db.getInfoArray("Usuari", "password");
        for(int i=0; i<user.length; i++){
            System.out.println(user[i]);
        }
        String[][] productesRoba = db.getProductesDeCarpeta("Ropa", "Paula");

        System.out.println("Productes a la carpeta 'Ropa':");
        db.printArray2D(productesRoba);
    }

    public void draw() {
        // Fons de la finestra
        String info = db.connectat ? "OK" : "ERROR";
        fill(0); textSize(48);
        text("Connexió a la BBDD : "+ info, 100, 100);
    }
}