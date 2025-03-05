import java.util.*;

public class GraphGenerator {
    //functie ce calculeaza numarul de muchii al unui graf neorientat
    public static long numarMuchii(int[][] param) {
        int n = param.length, m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (param[i][j] == 1) {
                    m++;
                }
            }
        }
        return m;
    }

    //functie ce consruieste un vector cu gradele nodurilor
    public static ArrayList<Integer> gradeleGrafului(int[][] param) {
        int n = param.length;
        ArrayList<Integer> listaGrade = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int grad = 0;
            for (int j = 0; j < n; j++) {
                if (param[i][j] == 1) {
                    grad++;
                }
            }
            listaGrade.add(grad);
        }
        return listaGrade;
    }

    //functie ce calculeaza suma gradelor
    public static long sumaGradele(ArrayList<Integer> gradele) {
        int n = gradele.size();
        int suma = 0;
        for (int i = 0; i < n; i++) {
            suma += gradele.get(i);
        }
        return suma;
    }

    //functie ce construieste un sir de caractere pe baza grafului
    public static String matriceMisto(int[][] param) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < param.length; i++) {
            for (int j = 0; j < param.length; j++) {
                if (param[i][j] == 1) {
                    sb.append("● ");
                }
                else {
                    sb.append("○ ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        //verific sa am 2 argumente
        if (args.length < 2) {
            System.err.println("Trebuie 2 argumente! Exemplu: java GraphGenerator <n> <m>");
            return;
        }

        //parsez argumentele date
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        //verific conditiile specifice pt clica si multime stabila
        if (n < k || n < 2*k) {
            System.err.println("Trebuie ca n >= k si n >= 2*k!");
            return;
        }

        //incep calcularea timpului
        long startTime = System.nanoTime();

        //creez (random) un graf (matrice de adiacenta)
        int[][] graf = new int[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //generam un bool random si pe baza lui punem 1 sau 0 in matrice
                graf[i][j] = graf[j][i] = rand.nextBoolean() ? 1 : 0;
            }
        }

        //fortam existenta unei clici
        //luam primele k noduri si le conectam
        for (int i = 0; i < k; i++) {
            for (int j = i+1; j < k; j++) {
                graf[i][j] = graf[j][i] = 1;
            }
        }

        //fortam existenta unei multimi stabile
        //luam urmatoarele k noduri si le deconectam
        for (int i = k; i < 2*k; i++) {
            for (int j = i+1; j < 2*k; j++) {
                graf[i][j] = graf[j][i] = 0;
            }
        }

        //asigur ca am 0 pe diagonala
        for (int i = 0; i < n; i++) {
            graf[i][i] = 0;
        }

        //imi iau variabilele necesare
        ArrayList<Integer> gradele = gradeleGrafului(graf); //vectorul de grade
        long m = numarMuchii(graf); //numarul de muchii
        long sumaGradele = sumaGradele(gradele); //suma gradelor
        long endTime = System.nanoTime(); //inchei cronometrarea
        long duration = endTime - startTime; //calculez durata

        if (n < 100) { //daca n este "mic", pot afisa matricea
            String afisareMatrice = matriceMisto(graf);
            System.out.println(afisareMatrice);
        }

        else { //pentru n mare, afisez doar timpul
            System.out.println("Timp de executie: " +  duration / 1_000_000 + " ms");
        }

        //afisez ce se cere
        System.out.println("Numarul de muchii: " + m);
        System.out.println("\u0394: " + Collections.max(gradele)); //delta mare
        System.out.println("\u03B4: " + Collections.min(gradele)); //delta mic
        StringBuilder sg = new StringBuilder("Suma gradelor == 2m: ");
        if (sumaGradele == 2*m) {
            sg.append("DA");
        }
        else {
            sg.append("NU");
        }
        System.out.println(sg.toString());
        System.out.println();

    }
}