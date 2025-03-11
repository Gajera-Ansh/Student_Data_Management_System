
import java.util.*;

public class Student {
    static Scanner sc = new Scanner(System.in);
    String enrollNo;
    String f, m, l, name, branch, email;
    float maths, java, phy, se;
    String[] nameArray;
    char mGrade, jGrade, pGrade, sGrade;
    float total;
    boolean marksStatus = false;

    static
    {
        System.out.println("\n---------------------------------------------------------\n" +
                "Welcome to Student Management System\n---------------------------------------------------------\n");
    }

    boolean resetPass() {
        boolean exit = true;
        while (exit) {
            boolean isValid = false;
            System.out.println("1. Reset Password");
            System.out.println("2. Continue with default password");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.print("\n" +
                        "---------------------------------------------------------\nEnter new 4 digit password: ");
                String newPass = sc.next();
                if (newPass.length() == 4) {
                    for (int i = 0; i < newPass.length(); i++) {
                        if (newPass.charAt(i) < '0' || newPass.charAt(i) > '9') {
                            System.out.println("\n---------------------------------------------------------\n" +
                                    "Only digits are allowed\nPlease try again...\n---------------------------------------------------------\n");
                            isValid = true;
                            break;
                        }
                    }
                } else if (newPass.length() != 4) {
                    System.out.println("\n---------------------------------------------------------\n" +
                            "Password should be of 4 digits\nPlease try again...\n---------------------------------------------------------\n");
                    continue;
                }
                if (isValid) {
                    continue;
                } else {
                    System.out.println("\n---------------------------------------------------------\n" +
                            "Password change successfully\n---------------------------------------------------------\n");
                    exit = false;
                    break;
                }
            } else if (choice == 2) {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Continuing with default password\n---------------------------------------------------------\n");
                exit = false;
            } else {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Invalid Input\nPlease try again...\n---------------------------------------------------------\n");
                continue;
            }
        }
        if (!exit) {
            return true;
        } else {
            return false;
        }
    }

    boolean data(Student[] s) {
        boolean isValid = true;
        while (isValid) {
            boolean isValid1 = true;
            System.out.print("Enter 10 digit Enrollment Number: ");
            enrollNo = sc.next();
            if (enrollNo.length() != 10) {
                System.out.println(
                        "\n---------------------------------------------------------\nEnrollment Number should be of 10 digits\nPlease try again..."
                                +
                                "\n---------------------------------------------------------\n");
                continue;
            } else if (enrollNo.length() == 10) {
                for (int i = 0; i < enrollNo.length(); i++) {
                    if (enrollNo.charAt(i) < '0' || enrollNo.charAt(i) > '9') {
                        System.out.println("\n---------------------------------------------------------\n" +
                                "Only digits are allowed\nPlease try again...\n---------------------------------------------------------\n");
                        isValid1 = false;
                        break;
                    } else {
                        for (int j = 0; j < (s.length - 1); j++) {
                            if (s[s.length - 1].enrollNo.equalsIgnoreCase(s[j].enrollNo)) {
                                System.out.println("\n" +
                                        "---------------------------------------------------------\nEnrollment Number already exists\nPlease try again...\n---------------------------------------------------------\n");
                                s = Arrays.copyOf(s, s.length - 1);
                                isValid1 = false;
                                break;
                            }
                        }
                    }
                }
                if (isValid1 == false) {
                    continue;
                }
            }
            if (isValid) {
                isValid = false;
            }
        }
        System.out.print("Enter First Name: ");
        f = sc.next().trim().toUpperCase();
        System.out.print("Enter Middle Name: ");
        m = sc.next().trim().toUpperCase();
        System.out.print("Enter last Name: ");
        l = sc.next().trim().toUpperCase();
        name = f + " " + m + " " + l;
        System.out.print("Enter Branch: ");
        branch = sc.next().trim().toUpperCase();
        return false;
    }

    char grade(float n) {
        char Grade = ' ';
        if (n >= 0 && n <= 25) {
            marksStatus = true;
            if (n <= 25 && n > 20) {
                Grade = 'A';
            } else if (n <= 20 && n > 15) {
                Grade = 'B';
            } else if (n <= 15 && n > 10) {
                Grade = 'C';
            } else if (n <= 10) {
                if (n < 9) {
                    Grade = 'F';
                } else {
                    Grade = 'D';
                }
            }
        }
        return Grade;
    }

    void enterMarks() {
        boolean exit = true;
        Student st = new Student();
        while (exit) {
            System.out.print("Enter Maths marks (0 to 25): ");
            maths = sc.nextFloat();
            if (maths >= 0 && maths <= 25) {
                marksStatus = true;
                mGrade = st.grade(maths);
            } else {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Invalid Input\nPlease try again...\n---------------------------------------------------------\n");
                continue;
            }
            System.out.print("Enter Java marks (0 to 25): ");
            java = sc.nextFloat();
            if (java >= 0 && java <= 25) {
                marksStatus = true;
                jGrade = st.grade(java);
            } else {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Invalid Input\nPlease try again...\n---------------------------------------------------------\n");
                continue;
            }
            System.out.print("Enter Physics makrs (0 to 25): ");
            phy = sc.nextFloat();
            if (phy >= 0 && phy <= 25) {
                marksStatus = true;
                pGrade = st.grade(phy);
            } else {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Invalid Input\nPlease try again...\n---------------------------------------------------------\n");
                continue;
            }
            System.out.print("Enter S.E. marks (0 to 25): ");
            se = sc.nextFloat();
            if (se >= 0 && se <= 25) {
                marksStatus = true;
                sGrade = st.grade(se);
            } else {
                System.out.println("\n---------------------------------------------------------\n" +
                        "Invalid Input\nPlease try again...\n---------------------------------------------------------\n");
                continue;
            }
            if (marksStatus) {
                exit = false;
            }
            total = maths + java + phy + se;
        }
    }

    void result(String search, Student[] s) {
        boolean searchStatus = true;
        for (int i = 0; i < s.length; i++) {
            if (s[i].enrollNo.equals(search)) {
                s[i].display();
                searchStatus = false;
                int totalGrade = 0;
                char aveGrade = ' ';
                for (char j = 'A', k = 1; j <= 'F'; j++, k++) {
                    if (s[i].mGrade == j) {
                        totalGrade += k;
                        break;
                    }
                }
                for (char j = 'A', k = 1; j <= 'F'; j++, k++) {
                    if (s[i].jGrade == j) {
                        totalGrade += k;
                        break;
                    }
                }
                for (char j = 'A', k = 1; j <= 'F'; j++, k++) {
                    if (s[i].pGrade == j) {
                        totalGrade += k;
                        break;
                    }
                }
                for (char j = 'A', k = 1; j <= 'F'; j++, k++) {
                    if (s[i].sGrade == j) {
                        totalGrade += k;
                        break;
                    }
                }
                int gradeAve = totalGrade / 4;
                switch (gradeAve) {
                    case 1:
                        aveGrade = 'A';
                        break;
                    case 2:
                        aveGrade = 'B';
                        break;
                    case 3:
                        aveGrade = 'C';
                        break;
                    case 4:
                        aveGrade = 'D';
                        break;
                    case 6:
                        aveGrade = 'F';
                        break;
                    default:
                        break;
                }
                System.out.println();
                System.out.println("------------------------------");
                System.out.println("| Maths     |  25 | " + s[i].maths + " | " + s[i].mGrade + " |");
                System.out.println("------------------------------");
                System.out.println("| Java      |  25 | " + s[i].java + " | " + s[i].jGrade + " |");
                System.out.println("------------------------------");
                System.out.println("| Physics   |  25 | " + s[i].phy + " | " + s[i].pGrade + " |");
                System.out.println("------------------------------");
                System.out.println("| S.E.      |  25 | " + s[i].se + " | " + s[i].sGrade + " |");
                System.out.println("------------------------------\n------------------------------");
                System.out.println("| Total     | 100 | " + s[i].total + " | " + aveGrade + " |");
                System.out.println("------------------------------");
                System.out.println("Percentage: " + s[i].total + "%");
                System.out.println("------------------------------\n");
            }
        }
        if (searchStatus) {
            System.out.println("No data found");
        }
    }

    void sortByMaths(Student[] s) {
        Student[] s1 = s;
        for (int i = 0; i < s1.length; i++) {
            for (int j = i + 1; j < s1.length; j++) {
                if (s1[i].maths < s1[j].maths) {
                    Student temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
        for (int i = 0; i < s1.length; i++) {
            s1[i].display();
            System.out.println("-----------\nMaths: " + s1[i].maths);
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    void sortByJava(Student[] s) {
        Student[] s1 = s;
        for (int i = 0; i < s1.length; i++) {
            for (int j = i + 1; j < s1.length; j++) {
                if (s1[i].java < s1[j].java) {
                    Student temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
        for (int i = 0; i < s1.length; i++) {
            s1[i].display();
            System.out.println("-----------\nJava: " + s1[i].java);
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    void sortByPhysics(Student[] s) {
        Student[] s1 = s;
        for (int i = 0; i < s1.length; i++) {
            for (int j = i + 1; j < s1.length; j++) {
                if (s1[i].phy < s1[j].phy) {
                    Student temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
        for (int i = 0; i < s1.length; i++) {
            s1[i].display();
            System.out.println("-----------\nPhysics: " + s1[i].phy);
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    void sortBySE(Student[] s) {
        Student[] s1 = s;
        for (int i = 0; i < s1.length; i++) {
            for (int j = i + 1; j < s1.length; j++) {
                if (s1[i].se < s1[j].se) {
                    Student temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
        for (int i = 0; i < s1.length; i++) {
            s1[i].display();
            System.out.println("-----------\nS.E.: " + s1[i].se);
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    void sortByTotal(Student[] s) {
        Student[] s1 = s;
        for (int i = 0; i < s1.length; i++) {
            for (int j = i + 1; j < s1.length; j++) {
                if (s1[i].total < s1[j].total) {
                    Student temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
        for (int i = 0; i < s1.length; i++) {
            s1[i].display();
            System.out.println("-----------\nTotal: " + s1[i].total);
            System.out.println("\n----------------------------------------------------------------");
        }
    }

    void display() {
        System.out.println("Enrollment Number: " + enrollNo);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch);
    }

    /*-------------------------------------------Main Method-------------------------------------------*/
    public static void main(String[] args) {
        Student st = new Student();
        Student[] stArray = new Student[0];
        boolean exit = true;
        boolean status = true;
        boolean pass = true;
        boolean marksStatus = false;
        String password = "1234";
        int attempt = 0;

        while (pass) {
            attempt++;
            System.out.print("\nEnter 4 digit password: ");
            String passInput = sc.next();
            if (password.equals(passInput)) {
                pass = false;
                System.out.println("\n---------------------------------------------------------\n" +
                        "Access Granted\n---------------------------------------------------------\n");
                exit = st.resetPass();
            } else {
                if (attempt == 2) {
                    System.out.println("\n---------------------------------------------------------\n" +
                            "Maximum attempts reached\n---------------------------------------------------------\n\nExiting...\n");
                    exit = false;
                    break;
                } else {
                    System.out.println("\n---------------------------------------------------------\n" +
                            "Invalid Password\nPlease try again...\n---------------------------------------------------------\n");
                    exit = false;
                }
            }
        }
        while (exit) {
            System.out.println("\n----------------------------------------------------------------");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Student data");
            System.out.println("3. Search Student");
            System.out.println("4. Enter Student Marks");
            System.out.println("5. Display Marksheet");
            System.out.println("6. Sort Student By there Subject Marks");
            System.out.println("7. Remove Student");
            System.out.println("8. Exit");
            int choice = sc.nextInt();

            /*--------------------------------Add Student------------------------------ */
            if (choice == 1) {
                stArray = Arrays.copyOf(stArray, stArray.length + 1);
                stArray[stArray.length - 1] = new Student();
                System.out.println("\n----------------------------------------------------------------");
                status = stArray[stArray.length - 1].data(stArray);
            }

            /*--------------------------------Display All Student data------------------------------ */
            else if (choice == 2) {
                System.out.println("\n----------------------------------------------------------------");
                if (!status) {
                    for (int i = 0; i < stArray.length; i++) {
                        stArray[i].display();
                        System.out.println("----------------------------------------------------------------");
                    }
                } else {
                    System.out.println(
                            "No data found");
                }
            }

            /*--------------------------------Search Student------------------------------ */
            else if (choice == 3) {
                if (!status) {
                    boolean exit1 = true;
                    while (exit1) {
                        System.out.println("\n----------------------------------------------------------------");
                        System.out.println("1. Search by Enrollment Number");
                        System.out.println("2. Search by First Name");
                        int choiceSearch = sc.nextInt();
                        switch (choiceSearch) {

                            /*--------------------------------Search by Enrollment Number------------------------------ */
                            case 1:
                                System.out
                                        .println("\n----------------------------------------------------------------");
                                System.out.print("Enter Enrollment Number: ");
                                String searchEnrollNo = sc.next();
                                boolean searchEnrollNoStatus = true;
                                System.out
                                        .println("\n----------------------------------------------------------------");
                                for (int i = 0; i < stArray.length; i++) {
                                    if (stArray[i].enrollNo.equals(searchEnrollNo)) {
                                        stArray[i].display();
                                        searchEnrollNoStatus = false;
                                        exit1 = false;
                                    }
                                }
                                if (searchEnrollNoStatus) {
                                    System.out.println("No data found");
                                }
                                break;

                            /*--------------------------------Search by First Name------------------------------ */
                            case 2:
                                System.out
                                        .println("\n----------------------------------------------------------------");
                                System.out.print("Enter First Name: ");
                                String searchFirstName = sc.next();
                                boolean searchNameStatus = true;
                                System.out
                                        .println("\n----------------------------------------------------------------");
                                for (int j = 0; j < stArray.length; j++) {
                                    if (stArray[j].f.equalsIgnoreCase(searchFirstName)) {
                                        stArray[j].display();
                                        System.out
                                                .println(
                                                        "----------------------------------------------------------------");
                                        System.out.println();
                                        searchNameStatus = false;
                                        exit1 = false;
                                    }
                                }
                                if (searchNameStatus) {
                                    System.out.println("No data found");
                                }
                                break;
                            default:
                                System.out.println(
                                        "\n-------------------\nInvalid Input\nPlease try again...\n-------------------\n");
                                break;
                        }
                    }
                } else {
                    System.out.println("\n" + //
                            "----------------------------------------------------------------\nNo data found");
                }
            }

            /*--------------------------------Enter Marks------------------------------ */
            else if (choice == 4) {
                System.out.println("\n----------------------------------------------------------------");
                if (!status) {
                    for (int i = 0; i < stArray.length; i++) {
                        System.out.println("Enter Marks for Student " + stArray[i].enrollNo + "\n");
                        stArray[i].enterMarks();
                        System.out.println("\n----------------------------------------------------------------");
                        marksStatus = true;
                    }
                } else {
                    System.out.println("No data found");
                }
            }

            /*--------------------------------Display Marksheet------------------------------ */
            else if (choice == 5) {
                System.out.println("\n----------------------------------------------------------------");
                if (marksStatus) {
                    System.out.print("Enter Enrollment Number to see that student result: ");
                    String searchEnrollNo = sc.next();
                    System.out.println("----------------------------------------------------------------\n");
                    st.result(searchEnrollNo, stArray);
                } else {
                    System.out.println("No data found");
                }
            }

            /*--------------------------------Sort Student By there Subject Marks------------------------------ */
            else if (choice == 6) {
                if (marksStatus) {
                    System.out.println("\n----------------------------------------------------------------");
                    System.out.println("1. Sort by Maths marks");
                    System.out.println("2. Sort by Java marks");
                    System.out.println("3. Sort by Physics marks");
                    System.out.println("4. Sort by S.E. marks");
                    System.out.println("5. Sort by Total Marks");
                    int sortChoice = sc.nextInt();
                    System.out.println("----------------------------------------------------------------\n");
                    switch (sortChoice) {
                        case 1: {
                            st.sortByMaths(stArray);
                            break;
                        }
                        case 2: {
                            st.sortByJava(stArray);
                            break;
                        }
                        case 3: {
                            st.sortByPhysics(stArray);
                            break;
                        }
                        case 4: {
                            st.sortBySE(stArray);
                            break;
                        }
                        case 5: {
                            st.sortByTotal(stArray);
                            break;
                        }
                        default: {
                            System.out.println(
                                    "\n-------------------\nInvalid Input\nPlease try again...\n-------------------\n");
                        }
                    }
                } else {
                    System.out.println(
                            "\n----------------------------------------------------------------\nNo data found");
                }
            }

            /*--------------------------------Remove Student------------------------------ */
            else if (choice == 7) {
                System.out.println("\n----------------------------------------------------------------");
                if (!status) {
                    System.out.print("Enter Enrollment Number to remove: ");
                    String removeEnrollNo = sc.next();
                    System.out.println("----------------------------------------------------------------\n");
                    boolean removeStatus = true;
                    for (int i = 0; i < stArray.length; i++) {
                        if (stArray[i].enrollNo.equals(removeEnrollNo)) {
                            for (int j = i; j < stArray.length - 1; j++) {
                                stArray[j] = stArray[j + 1];
                            }
                            stArray = Arrays.copyOf(stArray, stArray.length - 1);
                            removeStatus = false;
                        }
                    }
                    if (removeStatus) {
                        System.out.println("No data found");
                    } else {
                        System.out.println("Student data removed successfully");
                    }
                } else {
                    System.out.println("No data found");
                }
            }

            /*--------------------------------Exit------------------------------ */
            else if (choice == 8) {
                exit = false;
                System.out.println("\nExiting...\n");
            } else {
                System.out.println("\n-------------------\nInvalid Input\nPlease try again...\n-------------------\n");
            }
            if (stArray.length == 0) {
                status = true;
                marksStatus = false;
            }
        }
    }
}
