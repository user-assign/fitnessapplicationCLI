import java.util.*;

public class FitnessClubSystem2 {
    static String[] days = {"Saturday", "Sunday"};
    static String[] lessons = {"Spin", "Yoga", "Bodysculpt", "Zumba", "Aquacise", "Box Fit"};
    static int[][] bookings = new int[2][12]; //  5 customers per lesson This array refers to 2 days of the classes and 2 lessons per day
    static int[][] ratings = new int[6][2]; // 6 lesson types, 2 ratings (sum and count)  This array is used to store the 6 types of lesson and sum and count ratings
    static double[] incomes = new double[6]; // This array is used for storing teh income data from the lessons
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {         //This is the main function which is used to set the interactive interafce using CLI. This takes the input from users.
        System.out.println(" WFC Fitness Club Welcomes you!");  
        while (true) {
            System.out.println("Choose and Option from the following menu:");
            System.out.println("1. Display the lesson's' timetable");
            System.out.println("2. Book a lesson for me");
            System.out.println("3. Cancel my booking");
            System.out.println("4. Give review for lessons");
            System.out.println("5. Create Reports");
            System.out.println("6. Exit from the application");
            
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    viewTimetable();  //This function will display the timetable
                    break;
                case 2:
                    makeBooking();    //This function will book the lesson
                    break;
                case 3:
                    cancelBooking();   //This function will bcancel the booked lesson
                    break;
                case 4:
                    writeReview();    //This function will allow the user to write the review for the lessons
                    break;
                    case 5:
                    generateReport();  //This function will generate the result
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("You chose and Invalid option! Please choose option 1-5");
            }
        }
    }

    public static void viewTimetable() {  //function for managing timetable
        System.out.println("Choose the format you would like to view the timetable");
        System.out.println("1. Provide timetable by day");
        System.out.println("2. Provide timetable by type of lessons");
        int choice = input.nextInt();
        switch (choice) {  //allow the users to choose the option 
            case 1:  // thsi will provide the timetable by day
                System.out.println("Which day you want to take lesson on: Saturday or Sunday?");
                String day = input.next();
                int index = Arrays.asList(days).indexOf(day);
                if (index < 0) {
                    System.out.println("You have entered wrong day");
                    return;
                }
                System.out.println("Here is the Timetable for " + day + ":");
                for (int i = 0; i < 2; i++) {
                    System.out.println("Lesson " + (i + 1) + ": " + lessons[i + index * 2] + " (" + bookings[index][i * 2] + "/" + 5 + " booked, " + bookings[index][i * 2 + 1] + "/" + 5 + " booked)");
                }
                break;
            case 2:
                System.out.println("Choose lesson type you want to check timetable for");
                String lesson = input.next();
                int i = Arrays.asList(lessons).indexOf(lesson);
                if (i < 0) {
                    System.out.println("You have entered wromg type of lesson..Please check it again...");
                    return;
                }
                System.out.println("Here is the Timetable for " + lesson + ":");
                for (int j = 0; j < 2; j++) {
                    System.out.println("Lesson " + (j + 1) + " on " + days[j] + ": " + bookings[j][i * 2] + "/" + 5 + " booked, " + bookings[j][i * 2 + 1] + "/" + 5 + " booked");
                }
                break;
            default:
                System.out.println("You have made an Invalid choice of lesson !!!!");
        }
    }

    public static void makeBooking() { //This will manage the booking of lesson
        System.out.println("Please provide your name:"); //take username as input 
            String name = input.next();
        
        
        System.out.println("Please provide the type of lesson:"); //take lesson type as input 
        String lesson = input.next();
        int i = Arrays.asList(lessons).indexOf(lesson);
        if (i < 0) {
        System.out.println("You have entered the wrong type!!!");  //error handling
        return;
        }
        System.out.println("On which day you want to do lessons: Saturday or Sunday:");
        String day = input.next();
        int j = Arrays.asList(days).indexOf(day);
        if (j < 0) {
        System.out.println("You have entered the wrong day!!!");  //error handling
        return;
        }
        if (bookings[j][i * 2] == 5 && bookings[j][i * 2 + 1] == 5) {
        System.out.println("Sorry, no more seats available for this lesson! Better luck next time");
        return;
        }
        if (bookings[j][i * 2] < 5) {
        bookings[j][i * 2]++;
        } else {
        bookings[j][i * 2 + 1]++;
        }
        System.out.println("Hurray... You have successfully booked the lesson");
        }
        public static void cancelBooking() {  //This function will handle the cancellation of bookings
            System.out.println("Please provide your name:"); //take username as input 
            String name = input.next();
            
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 6; i++) {
                    if (bookings[j][i * 2] > 0 && name.equals(bookings[j][i * 2 + 1])) {
                        bookings[j][i * 2]--;
                        System.out.println("Your booking is cancelled..");
                        return;
                    }
                    if (bookings[j][i * 2 + 1] > 0 && name.equals(bookings[j][i * 2 + 1])) {
                        bookings[j][i * 2 + 1]--;
                        System.out.println("Your booking is cancelled.");
                        return;
                    }
                }
            }
            System.out.println("I am sorry..No booking found for you!");
        }
        
        
        public static void writeReview() { //this function will handle the review of the ratings
            System.out.println("Please provide your name:");  //take username as input 
            String name = input.next();
            System.out.println("Please provide the type of lesson:"); //take lesson type as input 
            String lesson = input.next();
            int i = Arrays.asList(lessons).indexOf(lesson);
            if (i < 0) {
                System.out.println("You have entered Invalid lesson type!");
                return;
            }
            System.out.println("Kindly provide rating the lesson on a scale of 1 to 5:"); ////take rating as input 
            int rating = input.nextInt();
            ratings[i][0] += rating;
            ratings[i][1]++;
            System.out.println("Thank you for reviewing the lesson!");
        }
        
        public static void generateReport() {  //this function will generate the report
            System.out.println("Here are the Number of customers per lessons:");
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 6; i++) {
                    int count = bookings[j][i * 2] + bookings[j][i * 2 + 1];
                    System.out.println(days[j] + " " + lessons[i] + ": " + count);
                    double rating = (double) ratings[i][0] / ratings[i][1];
                    System.out.println("Average rating of the lesson is : " + rating);
                }
            }
            System.out.println(" Highest income generating fitness lessons:"); //this will print the name of the lessons
            double maxIncome = 0;
            int maxIndex = -1;
            for (int i = 0; i < 6; i++) {
            incomes[i] = bookings[0][i * 2] * 10 + bookings[0][i * 2 + 1] * 10;
            incomes[i] += bookings[1][i * 2] * 10 + bookings[1][i * 2 + 1] * 10;
            if (incomes[i] > maxIncome) {
            maxIncome = incomes[i];
            maxIndex = i;
            }
            System.out.println(lessons[i] + ": " + incomes[i]);
            }
            System.out.println("Lesson that generated highest income are: " + lessons[maxIndex]);
            }
            
        }            