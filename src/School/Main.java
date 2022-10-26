package School;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    // assumption that a user has already been created
    // create teacher arraylist
    Teacher EnglishListening = new Teacher(1, "1", "Francois", "Polo", 100);
    Teacher EnglishGrammar = new Teacher(2, "password2", "Mina", "Ansari", 101);
    Teacher EnglishSpeaking = new Teacher(3, "password3", "Gizem", "Phece", 102);

    ArrayList<Teacher> teacherList = new ArrayList<>();

    teacherList.add(EnglishListening);
    teacherList.add(EnglishGrammar);
    teacherList.add(EnglishSpeaking);

    Student student1 = new Student(1, "pass1", 100, 100, "S", "Alex", "David");
    Student student4 = new Student(1, "pass1", 101, 100, "S", "Alex", "David");
    Student student2 = new Student(2, "pass2", 100, 70, "B", "Seven", "Guilty");
    Student student3 = new Student(3, "pass3", 101, 50, "C", "Java", "Jr");

    ArrayList<Student> studentList = new ArrayList<>();

    studentList.add(student1);
    studentList.add(student2);
    studentList.add(student3);
    studentList.add(student4);

    topMenu(teacherList, studentList);
  }

  public static void topMenu(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {
    char value;
    clrscr();
    try (Scanner input = new Scanner(System.in)) {

      do {

        System.out.println(
            "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n" +
                "Select your account type\n" +
                "A: Teacher\n" +
                "B: Student\n" +
                "Q: Quit\n" +
                "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");

        value = input.next().charAt(0);

        switch (Character.toLowerCase(value)) {
          case 'a':
            loginTeacher(teacherList, studentList);
            break;

          case 'b':
            loginStudent(teacherList, studentList);
            break;

          case 'q':
            break;

          default:
            System.out.println("Invalid value");
            break;
        }
      } while (Character.toLowerCase(value) != 'q');

      System.out.println("Thank you for using School Management System!");
      System.out.println("see you next time...");
      System.exit(0);
    }
  }

  public static void loginTeacher(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {

    try (Scanner inputStr = new Scanner(System.in);
        Scanner inputInt = new Scanner(System.in)) {

      while (true) {
        try {
          clrscr();
          System.out.println("=================================");
          System.out.println("Welcome to School Management System for Teacher!");
          System.out.println("=================================");
          System.out.println("Please enter your id: ");
          int user = inputInt.nextInt();
          System.out.println("Please enter your password: ");
          String pass = inputStr.next();

          // teacher login
          Teacher currentTeacher = Teacher.checkUser(user, pass, teacherList);
          clrscr();

          currentTeacher.showTeacherMenu(teacherList, studentList); // focus on login teacher class
        } catch (Exception e) {
          System.out.println("Incorrect ID or Password");
          System.out.println("DO you want to try again?");
          System.out.println("Yes or No");
          String Flg = inputStr.next().toLowerCase();
          if (Flg.equals("no")) {
            System.out.println("See you again!");
            System.exit(0);
          } else if (Flg.equals("yes")) {
            continue;
          } else {
            System.out.println("Invalid value. ");
            System.out.println("Exit the system....");
            System.exit(0);
          }
        }
      }
    }
  }

  public static void loginStudent(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {
    try (Scanner inputStr = new Scanner(System.in);
        Scanner inputInt = new Scanner(System.in)) {

      while (true) {
        try {
          clrscr();
          System.out.println("=================================");
          System.out.println("Welcome to School Management System for Student!");
          System.out.println("=================================");
          System.out.println("Please enter your id: ");
          int user = inputInt.nextInt();
          System.out.println("Please enter your password: ");
          String pass = inputStr.next();

          // student login
          Student currentStudent = Student.checkUser(user, pass, studentList);
          clrscr();

          currentStudent.showStudentMenu(teacherList, studentList);
        } catch (Exception e) {
          System.out.println("Incorrect ID or Password");
          System.out.println("DO you want to try again?");
          System.out.println("Yes or No");
          String Flg = inputStr.next().toLowerCase();
          if (Flg.equals("no")) {
            System.out.println("See you again!");
            System.exit(0);
          } else if (Flg.equals("yes")) {
            continue;
          } else {
            System.out.println("Invalid value. ");
            System.out.println("Exit the system....");
            System.exit(0);
          }
        }
      }
    }
  }

  public static void clrscr() {
    // Clears Screen in java
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
      System.out.print("\033\143");
    } catch (IOException | InterruptedException ex) {
    }
  }
}
