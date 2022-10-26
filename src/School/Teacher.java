package School;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Teacher {
  private int id;
  private String password;
  private String firstName;
  private String lastName;
  private int classNumber;

  // getMethod
  public int getId() {
    return this.id;
  }

  public String getPassword() {
    return this.password;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public int getClassNumber() {
    return this.classNumber;
  }

  // constructor
  public Teacher(int id, String password, String firstName, String lastName, int classNumber) {
    this.id = id;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.classNumber = classNumber;
  }

  // login method
  static Teacher checkUser(int user, String pass, ArrayList<Teacher> teacherList) {
    for (Teacher teacher : teacherList) {
      if (teacher.getId() == user) {
        if (pass.equals(teacher.getPassword())) {
          return teacher;
        }
      }
    }
    return null;
  }

  void showTeacherMenu(ArrayList<Teacher> teacherList, ArrayList<Student> studentList) {
    char option;
    try (Scanner input1 = new Scanner(System.in)) {
      do {

        System.out.println(
            "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n" +
                "Select an option from the menu\n" +
                "A: Show your class students\n" +
                "B: Add student in your class\n" +
                "C: Set student Score\n" +
                "D: Delete student in your class\n" +
                "Q: Logout\n" +
                "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        option = input1.next().charAt(0);

        switch (Character.toLowerCase(option)) {
          case 'a':
            showClass(studentList);
            break;

          case 'b':

            try {
              System.out.println("Choose add student ID");
              showStudents(studentList);
              System.out.println("type [0] to make new student");
              String inputValue1 = input1.next();
              int inputValue2 = Integer.parseInt(inputValue1);

              if (inputValue2 == 0) {
                // create a new student
                System.out.println("type student First Name");
                String newFirstName = input1.next();

                System.out.println("type student Last Name");
                String newLastName = input1.next();

                addStudent(0, this.classNumber, newFirstName, newLastName, studentList); // create a new student
              } else {
                addStudent(inputValue2, this.classNumber, "", "", studentList); // add student in my class
              }

            } catch (Exception e) {
              System.out.println("type value error. check again");
            }
            break;

          case 'c':
            showClass(studentList);

            String tmp = "";

            System.out.println("choose student ID");
            System.out.println("==========================================");
            tmp = input1.next();

            try {
              int chooseId = Integer.parseInt(tmp);

              if (checkStudentClass(chooseId, this.classNumber, studentList)) {
                System.out.println("What's score?");
                System.out.println("==========================================");

                String score1 = input1.next();
                int score2 = Integer.parseInt(score1);

                if (score2 > 100 || score2 < 0) {
                  System.out.println("type error! Please enter a number between 0 and 100.");
                } else {
                  setScore(chooseId, score2, this.classNumber, studentList);
                }
              } else {
                errorMessage("This student is not in your class");
              }
            } catch (Exception e) {
              errorMessage("type error! check again.");
            }

            break;

          case 'd':
            showClass(studentList);
            System.out.println("choose student ID");
            System.out.println("==========================================");

            try {
              String tmpDele = input1.next();
              int selectedId = Integer.parseInt(tmpDele);
              if (checkStudentClass(selectedId, this.classNumber, studentList)) {
                deleteStudent(selectedId, studentList);
              } else {
                errorMessage("type error! check again.");
              }
            } catch (Exception e) {
              errorMessage("type error! check again.");
            }

            break;

          default:
            errorMessage("Invalid option. Please try again");
            break;
        }
      } while (Character.toLowerCase(option) != 'q');
      Main.topMenu(teacherList, studentList);
    }
  }

  void showStudents(ArrayList<Student> studentList) {
    Main.clrscr();

    String check = "";
    int count = 0;

    System.out.println("==========================================");
    System.out.println("==List of students not taking your class==");
    System.out.println("==========================================");

    for (Student student : studentList) {
      if (student.getClassNumber() != this.classNumber) {
        for (Student studentB : studentList) {
          if (student.getStudentId() == studentB.getStudentId() && studentB.getClassNumber() == this.classNumber) {
            ++count; // if this student has already reg, count up
          }
        }
        if (count == 0) { // this student hasn't reg , it would be reg
          System.out
              .println(
                  check + "ID:" + student.getStudentId() + " : " + student.getFirstName() + " " + student.getLastName()
                      + "   classNumber:" + student.getClassNumber());
        }
        count = 0;
      }
    }
    System.out.println("==========================================");
  }

  void showClass(ArrayList<Student> studentList) {
    Main.clrscr();
    int count = 0;
    System.out.println("=================================================");
    System.out.println("==" + this.getFirstName() + "'s Class Students list==");
    System.out.println("=================================================");
    for (Student student : studentList) {
      if (this.classNumber == student.getClassNumber()) {
        System.out
            .println("ID:" + student.getStudentId() + " : " + student.getFirstName() + " " + student.getLastName());
        ++count;
      }
    }
    if (count == 0) {
      errorMessage("no students are in your class");
    }

    System.out.println("=================================================\n");
  }

  void addStudent(int id, Integer newClassNumber, String newFirstName, String newLastName,
      ArrayList<Student> studentList) {

    Main.clrscr();
    Integer newStudentId = 0; // key value
    String newPassword = "";
    boolean regFlg = false;

    if (id == 0) {

      for (Student student : studentList) {
        if (newStudentId <= student.getStudentId()) {
          newStudentId = student.getStudentId();
        }
      }

      ++newStudentId;

      newPassword = "pass" + newStudentId.toString();
      Student NewStudent = new Student(newStudentId, newPassword, newClassNumber, 0, "", newFirstName, newLastName);
      studentList.add(NewStudent);
      System.out.println("====================Create a new Student=================\n");
      NewStudent.showMyInfo();
      System.out.println("=====Please share the information with your students=====");

    } else {

      // check key value
      for (Student student : studentList) {
        if (id == student.getStudentId()) { // student id has already reg?
          if (newClassNumber == student.getClassNumber()) {
            errorMessage("This student has already registered your class!!");
            return;
          } else {
            newStudentId = student.getStudentId();
            newPassword = student.getPassword();
            newFirstName = student.getFirstName();
            newLastName = student.getLastName();
            regFlg = true;
          }
        }
      }

      System.out.println(regFlg);
      if (regFlg == true) {
        Student NewStudent = new Student(newStudentId, newPassword, newClassNumber, 0, "", newFirstName, newLastName);
        studentList.add(NewStudent);
        System.out.println("=========Add a new Student in your class=========\n");
        NewStudent.showMyInfo();
        System.out.println("=================================================");

        Collections.sort(studentList, new Comparator<Student>() {
          @Override
          public int compare(Student idFirst, Student idSecond) {
            return Integer.compare(idFirst.getStudentId(), idSecond.getStudentId());
          }
        });
      }
    }
  }

  void setScore(int chooseId, int score, int classNumber, ArrayList<Student> studentList) {
    Main.clrscr();
    String grade = "F";
    for (Student student : studentList) {
      if (student.getClassNumber() == classNumber && student.getStudentId() == chooseId) {
        student.setFinalScore(score);
        if (score == 100) {
          grade = "S";
        } else if (score >= 80) {
          grade = "A";
        } else if (score >= 60) {
          grade = "B";
        } else {
          grade = "F";
        }
        student.setFinalGrade(grade);
        System.out.println("=====Delete succeeded.=====");
        System.out.println(student.getFirstName() + " " + student.getLastName());
        System.out.println("Grade: " + student.getFinalGrade() + " (" + student.getFinalScore() + ")");
        break;
      }
    }
  }

  void deleteStudent(int selectedID, ArrayList<Student> studentList) {
    Main.clrscr();
    try {
      for (Student student : studentList) {
        if (student.getStudentId() == selectedID) {
          System.out.println("=================================================");
          student.showMyInfo();
          selectedID = studentList.indexOf(student);
        }
      }
      studentList.remove(selectedID);
      System.out.println("===== Delete success =====");
    } catch (Exception e) {
      errorMessage("The student could not be found.");
    }
  }

  boolean checkStudentClass(int id, int classNumber, ArrayList<Student> studentList) {
    boolean checkId = false;
    try {
      for (Student student : studentList) {
        if (student.getStudentId() == id) {
          if (student.getClassNumber() == classNumber) {
            checkId = true;
          }
        }
      }
      return checkId;
    } catch (Exception e) {
      errorMessage("type error! check again.");
    }
    return checkId;
  }

  void errorMessage(String message) {
    System.out.println(message);
  }

}