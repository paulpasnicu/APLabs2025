//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello World!\n"); //hello world!!! cf

        //vector de string
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        //generam n random
        int n = (int) (Math.random() * 1_000_000);

        //inmultim cu 3
        n *= 3;

        //adunam cu 10101 in binar
        n += 0b10101;

        //adunam cu FF in hexa
        n += 0xFF;

        //inmultim cu 6
        n *= 6;

        //calculam cifra de control (suma cifrelor pt suma cifrelor)
        int cifraControl;
        if (n % 9 == 0) cifraControl = 9;
        else cifraControl = n % 9;
        
        System.out.println("Willy-nilly, this semester I will learn " + languages[cifraControl]);

    }
}