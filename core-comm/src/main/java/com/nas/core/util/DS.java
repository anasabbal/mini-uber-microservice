package com.nas.core.util;


import java.sql.*;
import java.util.Scanner;

public class DS {
    public final static String URL = "jdbc:postgresql://localhost:5432/test";
    public final static String USER = "postgres";
    public final static String PASSWORD = "postgres";
    public static Integer ID = 1;


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void main(String[] args) throws SQLException {
        // ... code précédent ...
        Connection conn = connect();
        String str=" CREATE TABLE note (idNote INTEGER PRIMARY KEY, matiere VARCHAR(255), coefficient INTEGER, note INTEGER)";
        Statement s = null;
        try {
            s = conn.createStatement();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            s.executeUpdate(str);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);

        System.out.println("1) Entrer une note");
        System.out.println("2) Chercher une note");
        int choix = sc.nextInt();
        switch (choix){
            case 1:
                // ... code précédent ...
                System.out.print("Matiere : ");
                String matiere = sc.next();
                System.out.print("Coefficient : ");
                int coefficient = sc.nextInt();
                System.out.print("Note : ");
                double note = sc.nextDouble();
                // Insère les données de la note dans la table "note"

                String sql = "INSERT INTO note (idNote, matiere, coefficient, note) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, ID);
                stmt.setString(2, matiere);
                stmt.setInt(3, coefficient);
                stmt.setDouble(4, note);
                ID++;


                stmt.executeUpdate();

                System.out.println("Note ajoutée avec succès!");
                break;
            case 2:
                // ... code précédent ...
                System.out.print("Matiere : ");
                String _matiere = sc.next();
                String _sql = "SELECT * FROM note WHERE Matiere = ?";
                PreparedStatement _stmt = conn.prepareStatement(_sql);
                _stmt.setString(1, _matiere);
                ResultSet rs = _stmt.executeQuery();

                while (rs.next()) {
                    int idNote = rs.getInt("idnote");
                    int _coefficient = rs.getInt("coefficient");
                    double _note = rs.getDouble("note");
                    System.out.println("idNote: " + idNote + ", Matiere: " + _matiere + ", Coefficient: " + _coefficient + ", Note: " + _note);
                }
                break;
            default:
                System.out.println("Le choix n'est correct");
                break;
        }
        conn.close();
    }
}
