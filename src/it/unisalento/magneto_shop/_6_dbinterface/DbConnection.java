package it.unisalento.magneto_shop._6_dbinterface;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    //credenziali di accesso al database
    private static final String db_name = "dbMagneto";
    private static final String db_user = "0000";
    private static final String db_password = "0000";

    private static boolean connected;
    private static DbConnection instance;
    private static Connection db_connection;
    private ArrayList<byte[]> blobData = new ArrayList<byte[]>();

    /**COSTRUTTORE*/
    public DbConnection(){ }

    //SEZIONE METODI

    /**
     * Connessione al database
     * */
    public static boolean connect(){

        connected=false;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            db_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ db_name +"?autoreconnect=true&useSSL=false&user=" + db_user + "&password="+ db_password);
            connected=true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return connected;
    }

    /**
     * Effettua una query al database e resistituisce una lista di array di Stringhe
     * contenenti i risultati in tuple
     * @param query la query da eseguire al database
     * @return result il risultato della query*/

    public ArrayList<String[]> db_query(String query){

        ArrayList<String[]> result = new ArrayList<>();

        blobData.clear();
        String[] record;
        int column = 0;

        try{
            Statement stm = db_connection.createStatement();
            ResultSet rs = stm.executeQuery(query);


			/*
			 * La lista result viene creata a partire dal ResultSetMetaData
			 * con array di stringhe riempite con i valori colonna delle tuple
			 * trovate e aggiunte infine di volta in volta a result (ArrayList<String[]>)
			 * */

            ResultSetMetaData rsmd = rs.getMetaData();
            column = rsmd.getColumnCount();

            while(rs.next()){
                record = new String[column];
                for (int i=0; i<column; i++){
                    if(rsmd.getColumnType(i+1) == Types.LONGVARBINARY)
                        blobData.add(rs.getBytes(i+1));
                    record[i] = rs.getString(i+1);
                }
                result.add( (String[]) record.clone() );
            }

            stm.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String[]> db_query_NO_FOTO(String query){

        ArrayList<String[]> result = new ArrayList<>();

        String[] record;
        int column = 0;

        try{
            Statement stm = db_connection.createStatement();
            ResultSet rs = stm.executeQuery(query);


            /*
             * La lista result viene creata a partire dal ResultSetMetaData
             * con array di stringhe riempite con i valori colonna delle tuple
             * trovate e aggiunte infine di volta in volta a result (ArrayList<String[]>)
             * */

            ResultSetMetaData rsmd = rs.getMetaData();
            column = rsmd.getColumnCount();

            while(rs.next()){
                record = new String[column];
                for (int i=0; i<column; i++){

                    record[i] = rs.getString(i+1);
                }
                result.add( (String[]) record.clone() );
            }

            stm.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Effettua l'aggiornamento del database secondo la query passata come parametro
     * @param query la query che consente di effettuare l'aggiornamento
     * @result aggiornamento effettuato/non-effettuato
     * */
    public boolean db_update(String query) {

        int num = 0;
        boolean result = false;
        try {

            Statement stm = db_connection.createStatement();
            num = stm.executeUpdate(query);
            result = true;
            stm.close();

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public boolean db_update_Item(String itemName, float itemPrice, float itemSalePrice, String itemDescription, int idCategory, int idDepartemnt, int idManager, String itemPathImg, int avaiable) {

        boolean result = false;

        try{

            PreparedStatement ps = db_connection.prepareStatement("insert into ITEM(product,description,price,sales,photo,CATEGORY_idCATEGORY,DEPARTMENT_idDEPARTMENT,MANAGER_idMANAGER,available) values(?,?,?,?,?,?,?,?,?)");


            InputStream fileInputStream = new FileInputStream(new File(itemPathImg));

            ps.setString(1, itemName);
            ps.setString(2, itemDescription);
            ps.setFloat(3, itemPrice);
            ps.setFloat(4, itemSalePrice);
            ps.setBlob(5,fileInputStream);
            ps.setInt(6, idCategory);
            ps.setInt(7, idDepartemnt);
            ps.setInt(8, idManager);
            ps.setInt(9,avaiable);

            ps.executeUpdate();
            ps.close();
            result = true;

        }catch(Exception ex){ ex.printStackTrace();result = false; }
        return result;
    }

    public boolean db_update_Item_Foto(String itemPathImg, int idItem ) {

        boolean result = false;

        try{
            PreparedStatement ps = db_connection.prepareStatement("UPDATE ITEM SET photo = ? WHERE idITEM = ?");


            InputStream fileInputStream = new FileInputStream(new File(itemPathImg));

            ps.setBlob(1,fileInputStream);
            ps.setInt(2,idItem);

            ps.executeUpdate();
            ps.close();
            result = true;

        }catch(Exception ex){ ex.printStackTrace();result = false; }
        return result;
    }
    /**
     * Disconnessione dal database */
    public void disconnect(){

        try{

            db_connection.close();
            connected=false;

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean isConnected(){

        if(db_connection == null)
            return false;
        else
            return true;
    }


    public ArrayList<byte[]> getBlobData(){
        return blobData;
    }

    /** SINGLETON */
    public static DbConnection getInstance(){
        if(instance == null){
            instance = new DbConnection();
            if(!connected)
                connect();}
        return instance;
    }

}
