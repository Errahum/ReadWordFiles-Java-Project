/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author kimo3
 */
public class Tp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Programmeur Jeremy Sauro 

        choixOption();

    }

    private static FileOutputStream ouvrirFichierEcriture(String nomFic) {
        FileOutputStream ficSortie = null;
        try {
            ficSortie = new FileOutputStream(nomFic);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur de création de fichier");
        }
        return ficSortie;
    }

    private static FileInputStream ouvrirFichierLecture(String nomFichier) {
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream(nomFichier);
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur d’ouverture de fichier");
        }
        return fichier;
    }

    private static void fermerFichier(Closeable fichier) {
        try {
            fichier.close();
        } catch (IOException ex) {
            System.out.println("Erreur de fermeture de fichier");
        }
    }

    private static String[] CompterFichier() {
        boolean erreur = false;
        do { //do while pour recommencer jusqu'à la bonne réponse
            erreur = false;
            Scanner clavier = new Scanner(System.in);
            String documentOuverture = clavier.nextLine();

            if (!(documentOuverture.equals(clavier))) { // ne marche pas pour l'instant car j'ai pas  trouver de solution
                System.out.println("Erreur");
                erreur = true;
            }

            FileInputStream fichier = ouvrirFichierLecture(documentOuverture);

            Scanner lecteurFichier = new Scanner(fichier); // lire fichier

            int nombreLignes = 0;

            //Lire tout le fichier avec un while
            while (lecteurFichier.hasNext()) {
                lecteurFichier.nextLine();

                nombreLignes++;

            }
            String[] tabMot = new String[nombreLignes];

            System.out.println(nombreLignes + "lignes");

            CeationTableau(tabMot, documentOuverture);

            return tabMot;
        } while (erreur = true); //terminer la boucle si la valeur est false
    }

    private static void CeationTableau(String[] tab, String documentOuverture) {

        FileInputStream fichier = ouvrirFichierLecture(documentOuverture);

        Scanner lecteurFichier = new Scanner(fichier); // lire fichier

        String ligne = null;
        int compteur1 = 0;
        while (lecteurFichier.hasNext()) { //création d'un tableau avec un while 
            tab[compteur1] = lecteurFichier.nextLine();
            compteur1++;
        }
        for (int i = 0; i < tab.length; i++) { //afficher tout le contenue du tableau
            System.out.println(tab[i]);
        }
    }

    private static void choixOption() {
        boolean quitter = false;

        quitter = false;
        bienvenue();

        String[] tab = CompterFichier();

        boolean erreur = false;
        do { //boucle pour recommencer si il y a une érreur
            erreur = false;

            do { //boucle pour pouvoir quitter 
                afficherMenu1();

                Scanner clavier = new Scanner(System.in);
                int choixMenu = clavier.nextInt();

                switch (choixMenu) {
                    case 1:
                        statisitique(tab); //option 1 statistique
                        break;
                    case 2:
                        motPlusPetit(tab); //option 2 mots plus petits
                        break;
                    case 3:
                        motPlusGrand(tab); //option 3 mots plus grands
                        break;
                    case 4:
                        palindrome(tab); //option 4 les palindromes
//                        PlaindromeTest(tab);
                        break;
                    case 5:
                        pairEtImpairFichier(tab); //option 5 crée le fichier pair et impair
                        break;
                    case 6:
                        System.out.println("Aurevoir!"); //option 6 quitter
                        quitter = true; //quitter est à true donc le while va avoir terminer sa boucle
                        break;
                    default:
                }
                if (!(choixMenu == 1 || choixMenu == 2 || choixMenu == 3 || choixMenu == 4 || choixMenu == 5 || choixMenu == 6)) {
                    System.out.println("Erreur, veuillez choisir soit 1,2,3,4,5 ou 6");
                    erreur = true; // Il y a une erreur donc la boucle recommence
                }
            } while (quitter == false); //boucle pour pouvoir quitter 
        } while (erreur == true); //boucle pour recommencer si il y a une érreur

    }

    private static void bienvenue() {
        System.out.println("Bienvenue au super programme TP2 qui fait des recherches sur des mots.");
        System.out.println("Pour commencer, choisissez un fichier qui contient une séquence de mots.");
    }

    private static void afficherMenu1() {
        System.out.println("1. Afficher les statistiques:");
        System.out.println("2. Afficher le(s) mot(s) le(s) plus petit(s):");
        System.out.println("3. Afficher le(s) mot(s) le(s) plus grand(s):");
        System.out.println("4. Afficher le(s) mot(s) qui sont des palindromes:");
        System.out.println("5. Afficher les mots de longueurs paire et impaire:");
        System.out.println("6. Quitter:");
        System.out.println("Votre choix:");
    }

    private static void statisitique(String[] mots) {
        char alfabet[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int nombreMots = 0;
        FileOutputStream fichierSortie = ouvrirFichierEcriture("stats.txt"); //creation de la sortie stats
        PrintStream sortie = new PrintStream(fichierSortie);

        sortie.println("Il y a " + mots.length + " mots:");
        System.out.println("Il y a " + mots.length + " mots:");

        for (int i = 0; i < alfabet.length; i++) { //boucle pour chaque lettres
            for (int j = 0; j < mots.length; j++) { //boucle pour chaque mots

                if (alfabet[i] == mots[j].charAt(0)) { //Nombre mots compter
                    nombreMots++;

                }

            }
            sortie.println("Il y a " + nombreMots + " mots qui commencent par " + alfabet[i]);
            System.out.println("Il y a " + nombreMots + " mots qui commencent par " + alfabet[i]);
            nombreMots = 0;
        }

    }

    private static void motPlusPetit(String[] mots) {

        String[] tableau = new String[mots.length];
        int cmpt = mots[0].length();
        for (int i = 0; i < mots.length; i++) {

            if (cmpt >= mots[i].length()) { //boucle pour trouver le mot le plus petit en int
                cmpt = mots[i].length();
            }
        }
        for (int i = 0; i < tableau.length; i++) { //mettre les mots plus petits dans le tableau
            if (cmpt >= mots[i].length()) {
                tableau[i] = mots[i];
            }
        }

        System.out.println("La plus petite est:" + cmpt);

        //String et sortie
        FileOutputStream fichierSortie = ouvrirFichierEcriture("petit.txt"); //creation du fichier petit
        PrintStream sortie = new PrintStream(fichierSortie);
        for (int j = 0; j < tableau.length; j++) { //boucle pour imprimer et effacer les case vide du tableau

            if (tableau[j] != null) {

                sortie.println(tableau[j]);
                System.out.println(tableau[j]);

            }
        }
    }

    private static void palindrome(String[] mots) {
        String[] tableau = new String[mots.length];
        for (int i = 0; i < mots.length; i++) { //Boucle pour convertir les mots en Char
            char[] inversion = mots[i].toCharArray();

            System.out.println(inversionTableau(inversion, mots));
            //convertir char invertion en String
            if (mots[i].equals(inversion)) {
                tableau[i] = mots[i].toString();

            }

        }

        //String et sortie
        FileOutputStream fichierSortie = ouvrirFichierEcriture("palindrome.txt");
        PrintStream sortie = new PrintStream(fichierSortie);
        for (int j = 0; j < tableau.length; j++) {

            if (tableau[j] != null) {

                sortie.println(tableau[j]);
                System.out.println(tableau[j]);
            }

        }

    }

    // nombre
    // erbmon 
    private static String inversionTableau(char[] inversion, String[] tab) {
        String mot = "test";
        String motInverse = "";
        for (int i = 0; i < tab.length - 1 - i; i++) {
            motInverse += mot.charAt(i);
        }
        return motInverse;

    }

    private static void motPlusGrand(String[] mots) {
        String[] tableau = new String[mots.length];
        int cmpt = mots[0].length();
        for (int i = 0; i < mots.length; i++) { //boucle pour trouver le mot le plus grand en int

            if (cmpt <= mots[i].length()) {
                cmpt = mots[i].length();
            }

        }
        for (int i = 0; i < tableau.length; i++) { //mettre les mots plus grands dans le tableau
            if (cmpt <= mots[i].length()) {
                tableau[i] = mots[i];
            }
        }
        System.out.println("La plus grand est:" + cmpt);

        //String et sortie
        FileOutputStream fichierSortie = ouvrirFichierEcriture("grand.txt"); //creation du fichier grand
        PrintStream sortie = new PrintStream(fichierSortie);
        for (int j = 0; j < tableau.length; j++) { //boucle pour imprimer et effacer les case vide du tableau

            if (tableau[j] != null) {

                sortie.println(tableau[j]);
                System.out.println(tableau[j]);
            }

        }
    }

    private static void pairEtImpairFichier(String[] tab) {
        pair(tab);
        impair(tab);
    }

    private static void pair(String[] tab) {
        FileOutputStream fichierSortie = ouvrirFichierEcriture("pair.txt"); //crée le fichier pair
        PrintStream sortie = new PrintStream(fichierSortie);
        String[] tableau = new String[tab.length];
        sortie.println("Les mots pairs sont:");
        System.out.println("Les mots pairs sont:");
        for (int i = 0; i < tab.length; i++) { //boucle pour compter les chiffres pair
            char[] mots = tab[i].toCharArray();
            if (mots.length % 2 == 0) { //Chiffre pair

                sortie.println(mots);
                System.out.println(mots);
            }
        }
    }

    private static void impair(String[] tab) {
        FileOutputStream fichierSortie = ouvrirFichierEcriture("impair.txt"); //crée le fichier impair
        PrintStream sortie = new PrintStream(fichierSortie);
        String[] tableau = new String[tab.length];
        sortie.println("Les mots impairs sont:");
        System.out.println("Les mots impairs sont:");
        for (int i = 0; i < tab.length; i++) { //boucle pour compter les chiffres impair
            char[] mots = tab[i].toCharArray();
            if (mots.length % 2 == 1) { //Chiffre impair

                sortie.println(mots);
                System.out.println(mots);
            }
        }
    }
}
