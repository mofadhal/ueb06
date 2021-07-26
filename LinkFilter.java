/**
 *
** Eine Klasse zur Verwaltung eines Artikels
 *
 * @author (Mofadhal Al-Manari und Leen Alkhadraa)
 * @version (12.01.2021)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class LinkFilter {
    /**
     *main-Funktion führt den  Programm .  
     **/
    public static void main(String[] args) {
       
        if (args.length <= 0){
            System.out.println("java Main.java arg1.thml [arg2.html ... ]");
            return;
        }
        //RP Pattern pattern = Pattern.compile("<a.* href=\"([^\"]*)\".*>(.*)</a>"); /**Diese Metode lässt Alphapet oder Nummern in einer File zu suchen*/
        Pattern pattern = Pattern.compile("<a.* href=\"([^\"]*)\".*?>(.*)</a>"); /**Diese Metode lässt Alphapet oder Nummern in einer File zu suchen*/
        String next;
        Scanner input;

        for (String file_name : args) {/**Diese for-Funktion um mehrere file zu lesen*/
            try {// 
                input = new Scanner(new File(file_name));
            } catch (FileNotFoundException e) {
                System.out.println("invalid input "+ file_name );
                continue;
            }
            do {
                next = input.findWithinHorizon(pattern, 0);
                if (next != null) { 
                    MatchResult result = input.match();
                    for (int i = 1; i <= result.groupCount(); i += 2)/**Diese for-Funktion drückt die result aus*/
                        System.out.println(result.group(i+1) + ":\t" + result.group(i));
                }
            }
            while (next != null);
        }
    }
}
