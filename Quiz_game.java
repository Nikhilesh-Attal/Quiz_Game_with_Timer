import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz_game {
     
    Timer t;
    public  Quiz_game(int seconds){
        t=new Timer();
        t.schedule(new rt(), seconds*1000);
    }
    class rt extends TimerTask{
        public void run(){
            
            t.cancel();
        }
    }

    public static void main(String[] args) {
        int marks = 0;
        AtomicBoolean inputReceived = new AtomicBoolean(false);
        String[] ans = new String[1];
        Scanner a = new Scanner(System.in);

        System.out.println("\tWelcome to Quiz Game");
        System.out.println("You have 20 seconds to answer each question\n");

        // List of questions and answers
        String[][] questions = {
            {"1. What is the largest planet in our solar system?", "A. Earth", "B. Jupiter", "C. Saturn", "D. Mars", "B"},
            {"2. Which element has the chemical symbol 'O'?", "A. Gold", "B. Oxygen", "C. Osmium", "D. Oganesson", "B"},
            {"3. Who is known as the 'Father of Computers'?", "A. Alan Turing", "B. Bill Gates", "C. Charles Babbage", "D. Steve Jobs", "C"},
            {"4. In which year did the Titanic sink?", "A. 1905", "B. 1912", "C. 1920", "D. 1918", "B"},
            {"5. What is the capital city of Australia?", "A. Sydney", "B. Melbourne", "C. Canberra", "D. Brisbane", "C"},
            {"6. Which author wrote 'Pride and Prejudice'?", "A. Charlotte Bronte", "B. Jane Austen", "C. Emily Bronte", "D. Mary Shelley", "B"},
            {"7. What is the chemical formula for water?", "A. CO2", "B. H2O", "C. NaCl", "D. O2", "B"},
            {"8. Who painted the Mona Lisa?", "A. Vincent van Gogh", "B. Pablo Picasso", "C. Leonardo da Vinci", "D. Michelangelo", "C"},
            {"9. Which planet is known as the Red Planet?", "A. Venus", "B. Saturn", "C. Mars", "D. Mercury", "C"},
            {"10. What is the smallest unit of life?", "A. Atom", "B. Molecule", "C. Cell", "D. Organism", "C"}
        };

        // Loop through questions
        for (String[] quest : questions) {
           
            System.out.println(quest[0]);
            System.out.println(quest[1] + "\t" + quest[2] + "\t" + quest[3] + "\t" + quest[4]);

            new Quiz_game(20000);

            System.out.print("Enter your input:- ");
            Thread inputThread = new Thread(() -> {
                if (a.hasNext()) {
                    ans[0] = a.nextLine();
                    inputReceived.set(true);
                }
            });

           inputThread.start();

            try {
                // Wait for a maximum of 20 seconds for user input
                inputThread.join(20000);
            } 
            catch (InterruptedException e) {
                System.out.println("\n\tTime out");
            }

            if (!inputReceived.get()) {
                System.out.println("No input received within the timeout period.");
                break;
            } 
            else if (ans[0].equalsIgnoreCase(quest[5])) {
                marks++;
            }
            System.out.println("\tNext Question\n");
        }

        System.out.println("You scored: " + marks + " out of " + questions.length);
    }
}