package WishList;
import java.sql.*;

public class DataBase {

    Connection c;

    Statement query;

    String user, password, dataBaseName;
    String marca, producte;

    boolean connectat = false;


    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.dataBaseName = databaseName;
    }


    public void connect(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+dataBaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador){
        try{
            String q =  "SELECT " + nomColumna +
                    " FROM " + nomTaula +
                    " WHERE "+ nomClau  + " = '" + identificador + "' ";
            System.out.println(q);
            ResultSet rs= query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "";
    }

    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public String[] getInfoArray(String nomTaula, String nomColumna){
        int n = getNumFilesTaula(nomTaula);
        String[] info = new String[n];
        String q = "SELECT "+ nomColumna +
                " FROM " + nomTaula +
                " ORDER BY " + nomColumna + " ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString(1);
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void printArray1D(String[] user){
        for(int i=0; i<user.length; i++){
            System.out.printf("%d: %s.\n", i, user[i]);
        }
    }
    public void printArray2D(String[][] info){
        if(info==null){
            System.out.println("No se puede imprimir: El array es nulo (revisa el error de SQL arriba).");
            return;
        }
        for(int i=0; i<info.length; i++){
            System.out.printf("%d", i);
            for(int j=0; j<info[i].length; j++) {
                System.out.printf("%s \t", info[i][j]);
            }
        }
        System.out.println();
    }

    //Funció que retorna el nom d'un client amb una certa contrasenya
    public String getNomClientAmbPASSWORD(String PASSWORD){
        String c= "SELECT nom FROM Usuari WHERE password = '" + PASSWORD + "'";
        System.out.println(c);
        try{
            ResultSet rs = query.executeQuery(c);
            rs.next();
            String user = rs.getString("nom");
            return user;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String[] getNomTotsClients(){
        String q = "SELECT nom FROM Usuari ORDER BY nom ASC";
        System.out.println(q);
        try{
            int numFilesTaula = getNumFilesTaula("Usuari");
            String[] info = new String[numFilesTaula];
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString("nom");
                f++;
            }
            return info;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public String[][] getInfoTotsClients(){
        String q = "SELECT password, nom FROM Usuari ORDER BY nom ASC";
        try{
            int numFiles=getNumFilesTaula("Usuari");
            String[][] info = new String[numFiles][2];
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f][0] = rs.getString("password");
                info[f][1] = rs.getString("nom");
                f++;
            }
            return info;
        }
        catch (Exception e){
            System.out.println(e);

        }
        return null;
    }
    public String[][] getInfoProductesZara() {
        String qf = "SELECT COUNT(*)" +
                "FROM producte.nom, producte.marca" +
                "WHERE marca.nom = Zara" +
                "ORDER BY producte ASC";
        System.out.println(qf);

        int nf=this.getNumFilesQuery(qf);
        String[][] info = new String[nf][3];

        String q = "SELECT  producte.nom AS nom, producte.marca AS mar," +
                "FROM producte p, marca m" +
                "WHERE p.marca = m.nom AND p.nom = 'Zara" +
                "ORDER BY p.marca ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while(rs.next()){
                info[f][0] = rs.getString("nom");
                info[f][1]= rs.getString("mar");

            }
        }
        catch (Exception e){

        }

        return info;
    }

public int getNumFilesQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return(rs.getInt('n'));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
}
    public String[][] getProductesDeCarpeta(String nomCarpeta, String nomUsuari) {
        String qCount = "SELECT COUNT(*) AS total FROM Producte " +
                "WHERE Carpeta_nom = '" + nomCarpeta + "' " +
                "AND Usuari_nom = '" + nomUsuari + "'";

        int files = 0;
        try {
            ResultSet rsCount = query.executeQuery(qCount);
            if (rsCount.next()) {
                files = rsCount.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("Error comptant productes: " + e.getMessage());
        }

        if (files == 0) return new String[0][0];

        // 2. Fem la consulta real per obtenir les dades
        // Seleccionem nom, preu i marca (3 columnes)
        String q = "SELECT nom, preu, marca FROM Producte " +
                "WHERE Carpeta_nom = '" + nomCarpeta + "' " +
                "AND Usuari_nom = '" + nomUsuari + "' " +
                "ORDER BY nom ASC";

        String[][] info = new String[files][3];
        try {
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f][0] = rs.getString("nom");
                info[f][1] = rs.getString("preu");
                info[f][2] = rs.getString("marca");
                f++;
            }
            return info;
        } catch (Exception e) {
            System.out.println("Error en getProductesDeCarpeta: " + e.getMessage());
            return new String[0][0];
        }
    }

    void insertInfoTaulaUnitat(String num, String nom){
        try {
            String sNom = nom.replace("\'", "\\'");
            String q = "INSERT INTO unitat (numero, nom) VALUES ('" + num + "','" + sNom + "')";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void updateInfoTaulaUnitat(String id, String num, String nom){
        try {
            String q = "UPDATE unitat SET numero='"+num+"', nom='"+nom+"' WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    void deleteInfoTaulaUnitat(String id){
        try {
            String q = "DELETE FROM unitat WHERE numero='"+id+"'";
            System.out.println(q);
            query.execute(q);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public boolean loginCorrecte(String nom, String password){
        boolean correcte = false;

        try {
            String sql = "SELECT COUNT(*) AS N FROM Usuari WHERE nom = ? AND password = ?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, nom);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int n = rs.getInt("N");
                if(n > 0){
                    correcte = true;
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return correcte;
    }
    public void insertProducte(String nom, Float preu, String marca, String carpeta, String usuari){

        try {

            String sqlCarpeta = "INSERT IGNORE INTO Carpeta (nom, Usuari_nom) VALUES (?, ?)";
            PreparedStatement psCarpeta = c.prepareStatement(sqlCarpeta);
            psCarpeta.setString(1, carpeta);
            psCarpeta.setString(2, usuari);
            psCarpeta.executeUpdate();

            String sql = "INSERT INTO Producte (nom, preu, marca, Carpeta_nom, Usuari_nom) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setFloat(2, preu);
            ps.setString(3, marca);
            ps.setString(4, carpeta);
            ps.setString(5, usuari);

            ps.executeUpdate();

            System.out.println("Producto insertado correctamente!");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}