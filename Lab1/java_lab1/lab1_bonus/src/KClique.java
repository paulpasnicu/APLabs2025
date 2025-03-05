import java.util.*;

public class KClique {

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

    //reducere de la clique la multime stabila
    public static ArrayList<Integer> existaMultimeStabilaMacarK(int[][] param, int k) {
        //construim graful complementar
        int n = param.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (i != j) {
                    param[i][j] = param[j][i] = 1-param[i][j];
                }
            }
        }
        return existaClicaMacarK(param, k);
    }

    public static ArrayList<Integer> existaClicaMacarK(int[][] param, int k)  {
        int n = param.length;

        ArrayList<Integer> grade = gradeleGrafului(param);
        ArrayList<Integer> noduri = noduri(param);

        //sortez (descrescator) nodurile dupa grad
        Collections.sort(noduri, (n1, n2) -> Integer.compare(grade.get(n2), grade.get(n1)));

        //construiesc o clica folosind tehnica greedy
        ArrayList<Integer> clica = new ArrayList<>();
        for (int v : noduri) {
            boolean poateFiAdaugat = true;
            for (int u : clica) {
                if (param[v][u] == 0) {
                    poateFiAdaugat = false;
                    break;
                }
            }

            if (poateFiAdaugat) {
                clica.add(v);
            }
        }
        return clica;
    }

    //functie ce consruieste un vector cu gradele nodurilor (preluata din tema)
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

    //functie ce consruieste un vector cu nodurile
    public static ArrayList<Integer> noduri(int[][] param) {
        int n = param.length;
        ArrayList<Integer> noduri = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            noduri.add(i);
        }
        return noduri;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        //generare graf
        Random rand = new Random();
        int[][] graf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graf[i][j] = graf[j][i] = rand.nextBoolean() ? 1 : 0;
            }
        }

        for (int i = 0; i < n; i++) {
            graf[i][i] = 0;
        }

        //afisez matricea
        String matrice = matriceMisto(graf);
        System.out.println(matrice);

        //clica
        ArrayList<Integer> clica = existaClicaMacarK(graf, k);
        if (clica.size() < k) {
            System.out.println("\u2204 clica de dimensiune cel putin " + k + ", dimensiunea maxima a unei clici este " + clica.size());
        }
        else {
            System.out.println("\u2203 clica de dimensiune cel putin " + k + ": " + clica.subList(0, k));
        }

        //multime stabila
        ArrayList<Integer> stabile = existaMultimeStabilaMacarK(graf, k);
        if (stabile.size() < k) {
            System.out.println("\u2204 multime stabila de dimensiune cel putin " + k + ", dimensiunea maxima a unei multimi stable este " + stabile.size());
        }
        else {
            System.out.println("\u2203 multime stabila de dimensiune cel putin " + k + ": " + stabile.subList(0, k));
        }

    }
}