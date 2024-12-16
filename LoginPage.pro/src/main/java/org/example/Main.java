package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.displayLoginPage();  // 调用LoginPage展示登录界面

        LoginPage.saveData();
    }
}

// 学生类，存储学生信息
class Student implements Serializable {
    private String id;
    private String password;
    private String name;
    private String email;
    private String courses;
    private String clubs;

    public Student(String id, String password, String name, String email, String courses, String clubs) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.courses = courses;
        this.clubs = clubs;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourses() {
        return courses;
    }

    public String getClubs() {
        return clubs;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// 登录页面，处理学生登录
class LoginPage {

    private static Map<String, Student> studentDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    // 保存学生数据到文件
    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentData.txt"))) {
            oos.writeObject(studentDatabase);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // 从文件加载学生数据
    public static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentData.txt"))) {
            studentDatabase = (Map<String, Student>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        }

    }

    public LoginPage() {
        loadData();  // 在构造函数中加载数据
    }

    public void displayLoginPage() {
        System.out.println("Welcome to the Student Portal");
        System.out.println("Do you have an account? (yes/no)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            startLogin();  // 登录
        } else if (answer.equalsIgnoreCase("no")) {
            StudentRegistration.registerStudent();  // 注册新账户
        } else {
            System.out.println("Invalid choice. Please try again.");
            displayLoginPage();
        }
    }

    public static void startLogin() {
        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (studentDatabase.containsKey(studentID)) {
            Student student = studentDatabase.get(studentID);
            if (student.getPassword().equals(password)) {
                System.out.println("Login successful!");
                startNextPage(student);
            } else {
                System.out.println("Incorrect password!");
                handleRetry();
            }
        } else {
            System.out.println("Incorrect Student ID!");
            handleRetry();
        }
    }

    private static void handleRetry() {
        System.out.println("Do you want to try again or reset your password? (retry/reset/exit)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("retry")) {
            startLogin();
        } else if (choice.equalsIgnoreCase("reset")) {
            ResetPassword.resetPassword();
        } else if (choice.equalsIgnoreCase("exit")) {
            saveData();  // 保存数据
            System.out.println("Goodbye!");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Invalid choice, try again.");
            handleRetry();
        }
    }

    static void startNextPage(Student student) {
        System.out.println("Welcome to the next page, " + student.getName() + "!");
        System.out.println("Your courses: " + student.getCourses());
        System.out.println("Your clubs: " + student.getClubs());
        // 其他页面逻辑可以添加在这里

    }

    public static void updatePassword(String studentID, String newPassword) {
        if (studentDatabase.containsKey(studentID)) {
            studentDatabase.get(studentID).setPassword(newPassword);
            System.out.println("Password updated successfully.");
        }
    }

    public static Map<String, Student> getStudentDatabase() {
        return studentDatabase;
    }
}


//————————————————————————————————————————————————————————————————————————-


// 注册页面，处理学生注册
class StudentRegistration {
    private static Scanner scanner = new Scanner(System.in);

    public static void registerStudent() {
        System.out.println("Create your Student Account");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Input for courses
        String courses = "";
        String quit = "";
        while (true) {
            System.out.print("Enter your courses (comma-separated) [\"next\" to next step]: ");
            quit = scanner.nextLine();
            if (quit.equalsIgnoreCase("next")) {
                break;
            }
            if (courses.isEmpty()) {
                courses = quit;
            } else {
                courses += "," + quit;
            }
        }

        // Input for clubs
        String clubs = "";
        while (true) {
            System.out.print("Enter your clubs(comma-separated) [\"quit\" o quit]: ");
            quit = scanner.nextLine();
            if (quit.equalsIgnoreCase("quit")) {
                break;
            }
            if (clubs.isEmpty()) {
                clubs = quit;
            } else {
                clubs += "," + quit;
            }
        }

        // Output the entered data
        System.out.println("\nStudent Registration Complete!");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Student ID: " + id);
        System.out.println("Password: " + password);
        System.out.println("Courses: " + courses);
        System.out.println("Clubs: " + clubs);
        String studentInfo = String.format("Name: %s\nEmail: %s\nStudent ID: %s\nCourses: %s\nClubs: %s",
                name, email, id, courses, clubs);
        String pdfPath = PdfGenerator.generateStudentPdf(studentInfo);
        EmailService.sendEmailWithAttachment(email, "Student Registration Details",
                "Your registration details are attached as a PDF.", pdfPath);

        System.out.println("Your registration details have been emailed to you.");
    }

    public static void main(String[] args) {
        registerStudent();
    }
}

// 重置密码功能
class ResetPassword {
    private static Scanner scanner = new Scanner(System.in);

    private static Map<String, String> verificationCodes = new HashMap<>();
    private static Map<String, Long> verificationCodeTimestamps = new HashMap<>();

    public static void resetPassword() {
        System.out.print("Please enter your email address: ");
        String email = scanner.nextLine();

        Student student = findStudentByEmail(email);
        if (student == null) {
            System.out.println("Student with the provided email not found.");
            return;
        }

        System.out.println("Sending a verification code to your email...");
        if (!sendVerificationCode(email)) {
            System.out.println("Failed to send verification code. Please try again later.");
            return;
        }

        System.out.print("Enter the verification code: ");
        String code = scanner.nextLine();
        while(true) {
            if (verifyCode(email, code)) {
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                LoginPage.updatePassword(student.getId(), newPassword); // 更新密码
                LoginPage.saveData();  // 保存数据到文件
                // 生成 PDF 并发送邮件
                String studentInfo = String.format("Name: %s\nEmail: %s\nStudent ID: %s\nNew Password: %s",
                        student.getName(), email, student.getId(), newPassword);
                String pdfPath = PdfGenerator.generateStudentPdf(studentInfo);
                EmailService.sendEmailWithAttachment(email, "Password Reset Confirmation",
                        "Your new password details are attached as a PDF.", pdfPath);

                System.out.println("Password reset successful. Please log in with your new password.");
////////
                return;
            } else {
                System.out.println("Verification failed. Please try again.");
                code = scanner.nextLine();
            }

        }
    }


    // 查找学生对象
    public static Student findStudentByEmail(String email) {
        for (Student student : LoginPage.getStudentDatabase().values()) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                return student;
            }
        }
        return null;
    }

    public static boolean sendVerificationCode(String email) {
        String code = EmailService.generateCode();
        verificationCodes.put(email, code);
        verificationCodeTimestamps.put(email, System.currentTimeMillis());
        return EmailService.sendEmail(email, code);
    }

    public static boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        Long timestamp = verificationCodeTimestamps.get(email);

        if (storedCode == null || timestamp == null) return false;

        long currentTime = System.currentTimeMillis();
        if (currentTime - timestamp > 5 * 60 * 1000) { // 5分钟超时
            System.out.println("Verification code expired.");
            return false;
        }

        return storedCode.equals(code);
    }
}

