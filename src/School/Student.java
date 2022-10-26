package School;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
  private int studentId;
  private String password;
  private int classNumber;
  private int finalScore;
  private String finalGrade;
  private String firstName;
  private String lastName;

  public Student(int studentId, String password, int classNumber, int finalScore, String finalGrade, String firstName,
      String lastName) {
    this.studentId = studentId;
    this.password = password;
    this.classNumber = classNumber;
    this.finalScore = finalScore;
    this.finalGrade = finalGrade;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public int getStudentId() {
    return this.studentId;
  }

  public String getPassword() {
    return this.password;
  }

  public int getClassNumber() {
    return this.classNumber;
  }

  public int getFinalScore() {
    return this.finalScore;
  }

  public String getFinalGrade() {
    return this.finalGrade;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setFinalScore(int finalScore) {
    this.finalScore = finalScore;
  }

  public void setFinalGrade(String finalGrade) {
    this.finalGrade = finalGrade;
  }

  // login method
  static Student checkUser(int user, String pass, ArrayList<Student> studentList) {
    for (Student student : studentList) {
      if (student.getStudentId() == user) {
        if (pass.equals(student.getPassword())) {
          return student;
        } else {
          System.out.println("▼▼▼login failed▼▼▼");
        }
      }
    }
    return null;
  }

  void showStudentMenu(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {
    char option;
    Main.clrscr();
    try (Scanner input1 = new Scanner(System.in)) {
      do {
        System.out.println(
            "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n" +
                "Select an option from the menu\n" +
                "A: Show my classes\n" +
                "B: show my info\n" +
                "C: change password\n" +
                "Q: Logout\n" +
                "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        option = input1.next().charAt(0);

        switch (Character.toLowerCase(option)) {
          case 'a':
            showMyClass(teacherList, studentList);
            break;

          case 'b':
            showMyInfo();
            break;

          case 'c':
            System.out.println("Please type new password ");
            String inputValue1 = input1.next();

            System.out.println("Confirm new password");
            String inputValue2 = input1.next();
            changePass(inputValue1, inputValue2, studentList);
            break;

          default:
            System.out.println("Invalid option. Please try again");
            break;
        }
      } while (Character.toLowerCase(option) != 'q');

      System.out.println("Thank you for using SYSTEM! See you next login....");
      Main.topMenu(teacherList, studentList);
    }
  }

  void showMyClass(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {
    Main.clrscr();
    System.out.println("===== You're taking class list =====");

    for (Student student : studentList) {
      if (student.getStudentId() == this.studentId) {
        for (Teacher teacher : teacherList) {
          if (teacher.getClassNumber() == student.getClassNumber()) {
            System.out
                .println("Class Number: " + student.getClassNumber() + " Teacher: " + teacher.getFirstName() + " "
                    + teacher.getLastName() + "\n" +
                    "FinalGrade: " + student.getFinalGrade() + "\n");
          }
        }
      }
    }
  }

  void showMyInfo() {
    System.out.println("Student ID: " + this.studentId + "\n");
    System.out.println("Student Password: " + this.password + "\n");
    System.out.println("Student Class Number: " + this.classNumber + "\n");
    System.out.println("Student Name: " + this.firstName + " " + this.lastName + "\n");
  }

  void changePass(String val1, String val2, ArrayList<Student> studentList) {
    Main.clrscr();
    if (val1.equals(val2)) {
      for (Student student : studentList) {
        if (student.getStudentId() == this.studentId) {
          this.password = val1;
        }
      }
      System.out.println("===== Changed your password =====");
      showMyInfo();
    } else {
      errorMessage("The typed value is not correct.");
    }
  }

  void errorMessage(String message) {
    System.out.println(message);
  }

}