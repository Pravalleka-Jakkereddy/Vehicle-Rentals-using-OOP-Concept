/*Modifications :
1.In the Signup "Email" starts with Alphabets and may contain numbers, special characters and ends with "@gmail.com".(done)
2.In the "login with email" ask register email and password. email does not match the register email give 3 chances for enter correct email then provide 1.Login with email 2.Login with phone number 3.Exit options. If password incorrect then provide 3 chances for enter correct password after 3 chances over then provide password reset option. In that Password reset ask register phone number if user not give same phone number give 3 chances for enter same number after over then provide 1.Login with email 2.Login with phone number 3.Exit options. if enters correct one then generate OTP then give "Enter new Password:" it should not match with old password.
3. In the Vehicles selected module "Do you want to add another vehicle (y/n)" after this if user select "y" provide to user "Our Services- 1.Two Wheeler 2.Four Wheeler 3.Exit". 
4.In the Payment Module after user select "2.Add more Vehicles" option provide to user "Our Services- 1.Two Wheeler 2.Four Wheeler 3.Exit". 
5.In the Payment if user not give register phone number after he's chances are over diaplay "Not found user with this phone number! Moving to Home Page" provide 1.Signup 2.login 3.exit. 6.In the login module In the 1.login with email if user not give register email after his chances over then Diaplay "Not found user with this email" provide 1.Signup 2.login 3.exit. 
7.In the login module In the 1.login with phone number if user not give register phone number after his chances over then Diaplay "Not found user with this phone number" provide 1.Signup 2.login 3.exit. these are the modifications remaining logic was same not change it only change modify these.*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PC{

    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    private static User registeredUser = null;
    private static boolean isLoggedIn = false;

    private static final List<Bike> twoWheelerEV = new ArrayList<>();
    private static final List<Bike> twoWheelerPetrol = new ArrayList<>();
    private static final List<Car> fourWheelerEV = new ArrayList<>();
    private static final List<Car> fourWheelerPetrolDiesel = new ArrayList<>();
    private static final List<Car> fourWheelerCng = new ArrayList<>();
    private static final List<RentalItem> allRentals = new ArrayList<>();


    public static void main(String[] args) {
        initVehicles();
        runApplication();
    }

    // ---------------------- Initialization ----------------------
    private static void initVehicles() {
        // Two Wheeler EV
        twoWheelerEV.add(new Bike(1, "Flash Man Bike", "EV-2023", "120 km/charge", "TS09 EV 0001", 120));
        twoWheelerEV.add(new Bike(2, "Jathiratnalu Scooter", "EV-2022", "110 km/charge", "TS09 EV 0002", 100));
        twoWheelerEV.add(new Bike(3, "DJ Tillu Bulletu", "EV-2024", "130 km/charge", "TS09 EV 0003", 150));
        twoWheelerEV.add(new Bike(4, "Pushpa Racer", "EV-2021", "100 km/charge", "TS09 EV 0004", 90));
        twoWheelerEV.add(new Bike(5, "Rowdy Baby Ride", "EV-2023", "140 km/charge", "TS09 EV 0005", 160));

        // Two Wheeler Petrol
        twoWheelerPetrol.add(new Bike(1, "Mass Biriyani Bike", "PT-2019", "45 kmpl", "TS09 PT 1001", 80));
        twoWheelerPetrol.add(new Bike(2, "Kahani Twist Pulsar", "PT-2020", "50 kmpl", "TS09 PT 1002", 90));
        twoWheelerPetrol.add(new Bike(3, "Meme Master Splendor", "PT-2018", "60 kmpl", "TS09 PT 1003", 70));
        twoWheelerPetrol.add(new Bike(4, "Comedy King Activa", "PT-2021", "55 kmpl", "TS09 PT 1004", 85));
        twoWheelerPetrol.add(new Bike(5, "Pakka Local Racer", "PT-2022", "52 kmpl", "TS09 PT 1005", 95));

        // Four Wheeler EV
        fourWheelerEV.add(new Car(1, "Sarileru Sedan", "EV-SD-2024", 5, "300 km/charge", "TS09 EV 2001", 400));
        fourWheelerEV.add(new Car(2, "Ala Vaikuntha SUV", "EV-SUV-2023", 7, "320 km/charge", "TS09 EV 2002", 450));
        fourWheelerEV.add(new Car(3, "F2 Family Car", "EV-HB-2022", 5, "280 km/charge", "TS09 EV 2003", 350));
        fourWheelerEV.add(new Car(4, "Rowdy Hero Hatchback", "EV-HB-2021", 4, "260 km/charge", "TS09 EV 2004", 320));
        fourWheelerEV.add(new Car(5, "DJ Tillu Taxi", "EV-SD-2024", 5, "310 km/charge", "TS09 EV 2005", 380));

        // Four Wheeler Petrol/Diesel
        fourWheelerPetrolDiesel.add(new Car(1, "Mass Maharaja WagonR", "PD-HB-2019", 5, "18 kmpl", "TS09 PD 3001", 250));
        fourWheelerPetrolDiesel.add(new Car(2, "Gabbar Singh Swift", "PD-HB-2020", 5, "20 kmpl", "TS09 PD 3002", 270));
        fourWheelerPetrolDiesel.add(new Car(3, "Bahubali Innova", "PD-MPV-2018", 7, "15 kmpl", "TS09 PD 3003", 350));
        fourWheelerPetrolDiesel.add(new Car(4, "Arjun Reddy Thar", "PD-SUV-2022", 5, "14 kmpl", "TS09 PD 3004", 400));
        fourWheelerPetrolDiesel.add(new Car(5, "Magadheera Sedan", "PD-SD-2021", 5, "17 kmpl", "TS09 PD 3005", 300));

        // Four Wheeler CNG
        fourWheelerCng.add(new Car(1, "Chai Biscuit WagonR", "CNG-HB-2021", 5, "26 km/kg", "TS09 CNG 4001", 220));
        fourWheelerCng.add(new Car(2, "Hit Movie Alto", "CNG-HB-2020", 4, "28 km/kg", "TS09 CNG 4002", 200));
        fourWheelerCng.add(new Car(3, "Software Express Ertiga", "CNG-MPV-2022", 7, "24 km/kg", "TS09 CNG 4003", 280));
        fourWheelerCng.add(new Car(4, "Office Cab Dzire", "CNG-SD-2019", 5, "25 km/kg", "TS09 CNG 4004", 240));
        fourWheelerCng.add(new Car(5, "Weekend Trip Van", "CNG-VAN-2023", 8, "23 km/kg", "TS09 CNG 4005", 300));
    }

    // ---------------------- Application Flow ----------------------
    private static void runApplication() {
        System.out.println("Welcome To Bump Scaav Vehicle Rental System");
        while (true) {
            System.out.println("\n1. Signup\n2. Login\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handleSignup(); break;
                case 2: handleLogin(); break;
                case 3: System.out.println("Thank you for using Bump Scaav Vehicle Rental System"); return;
                default: System.out.println("Invalid choice, Please Select Correct One");
            }
        }
    }

    // ---------------------- Signup ----------------------
    private static void handleSignup() {
        System.out.println("\n-------SIGNUP-------");

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        while (!name.matches("[A-Za-z]+")) {
            System.out.println("Name should contain only alphabets.");
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
        }

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine().trim();
            if (validateEmail(email)) break;
            else System.out.println("Invalid Email, must end with @gmail.com");
        }

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = sc.nextLine();
            if (validatePassword(password)) break;
            else printPasswordRules();
        }

        String phone;
        while (true) {
            System.out.print("Enter Phone Number: ");
            phone = sc.nextLine().trim();
            if (validatePhone(phone)) break;
            else System.out.println("Invalid Phone number! It should start with 6,7,8,9 and contain 10 digits");
        }

        registeredUser = new User(name, email, password, phone);
        System.out.println("Registered Successfully");

        while (true) {
            System.out.println("\n1. Login\n2. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            if (choice == 1) { handleLogin(); return; }
            else if (choice == 2) { System.out.println("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0); }
            else System.out.println("Invalid choice, Please Select Correct One");
        }
    }

    // ---------------------- Login ----------------------
    private static void handleLogin() {
        if (registeredUser == null) {
            System.out.println("No user found. Please Signup first.");
            while (true) {
                System.out.println("\n1. Signup\n2. Exit");
                int choice = readIntSafe();
                if (choice == 1) { handleSignup(); return; }
                else if (choice == 2) { System.out.println("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0); }
                else System.out.println("Invalid choice, Please Select Correct One");
            }
        }

        while (true) {
            System.out.println("\n1. Login with Phone Number");
            System.out.println("2. Login with Email");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: loginWithPhoneNumber(); if (isLoggedIn) servicesMenu(); break;
                case 2: loginWithEmail(); if (isLoggedIn) servicesMenu(); break;
                case 3: System.out.println("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0);
                default: System.out.println("Invalid choice, Please Select Correct One");
            }
        }
    }

    // ---------------------- Services Menu ----------------------
    private static void servicesMenu() {
        while (true) {
            System.out.println("\n-------OUR SERVICES------");
            System.out.println("1. Two Wheeler");
            System.out.println("2. Four Wheeler");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handleTwoWheeler(); break;
                case 2: handleFourWheeler(); break;
                case 3: System.out.println("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0);
                default: System.out.println("Invalid choice, Please Select Correct One");
            }
        }
    }

    // ---------------------- Two Wheeler ----------------------
    private static void handleTwoWheeler() {
        while (true) {
            System.out.println("\n1. EV\n2. Petrol");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            List<Bike> selectedList = null;
            switch (choice) {
                case 1: selectedList = twoWheelerEV; break;
                case 2: selectedList = twoWheelerPetrol; break;
                default: System.out.println("Invalid choice, Please Select Correct One"); continue;
            }
            List<RentalItem> rentals = selectMultipleVehicles(selectedList);
            if (!rentals.isEmpty()) printRentalSummary(rentals, selectedList);
            return;
        }
    }

    // ---------------------- Four Wheeler ----------------------
    private static void handleFourWheeler() {
        while (true) {
            System.out.println("\n1. EV\n2. Petrol/Diesel\n3. CNG");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            List<Car> selectedList = null;
            switch (choice) {
                case 1: selectedList = fourWheelerEV; break;
                case 2: selectedList = fourWheelerPetrolDiesel; break;
                case 3: selectedList = fourWheelerCng; break;
                default: System.out.println("Invalid choice, Please Select Correct One"); continue;
            }
            List<RentalItem> rentals = selectMultipleVehicles(selectedList);
            if (!rentals.isEmpty()) printRentalSummary(rentals, selectedList);
            return;
        }
    }

    // ---------------------- Vehicle Selection ----------------------
    private static <T extends Vehicle> List<RentalItem> selectMultipleVehicles(List<T> vehicleList) {
        List<RentalItem> rentals = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("\nAvailable Vehicles:");
            for (T v : vehicleList) {
                System.out.println(v);
            }
            System.out.print("Select Vehicle Id: ");
            int id = readIntSafe();

            T selectedVehicle = null;
            for (T v : vehicleList) {
                if (v.getId() == id) {
                    selectedVehicle = v;
                    break;
                }
            }

            if (selectedVehicle == null) {
                System.out.println("Invalid choice, Please Select Correct One");
                continue;
            }

            int hours = readRentalHours();
            double amount = hours * selectedVehicle.getCostPerHour();
            rentals.add(new RentalItem(selectedVehicle, hours, amount));

            while (true) {
    System.out.print("Do you want to add another vehicle? (y/n): ");
    String ans = sc.nextLine().trim().toLowerCase();
    if (ans.equals("y")) {
        // add current rentals to global list before going back
        allRentals.addAll(rentals);
        servicesMenu();   // go back to services menu
        return rentals;
    } else if (ans.equals("n")) {
        addMore = false;
        // add current rentals to global list
        allRentals.addAll(rentals);
        // show summary for ALL rentals selected so far
        printRentalSummary(allRentals, vehicleList);
        return rentals;
    } else {
        System.out.println("Invalid choice, please try again.");
    }
}



        }
        return rentals;
    }

    private static int readRentalHours() {
        while (true) {
            System.out.print("Enter Hours (Rentals only in hours): ");
            int hours = readIntSafe();
            if (hours >= 1) return hours;
            System.out.println("Invalid Input, must be at least 1 hour.");
        }
    }

    // ---------------------- Rental Summary ----------------------
    private static <T extends Vehicle> void printRentalSummary(List<RentalItem> rentals, List<T> selectedList) {
        System.out.println("\n-------RENTAL SUMMARY------");
        double grandTotal = 0;
        for (RentalItem item : allRentals) {
            Vehicle v = item.getVehicle();
            System.out.println("---------------------------");
            System.out.println("Selected Vehicle Name : " + v.getName());
            System.out.println("Vehicle Number        : " + v.getNumber());
            System.out.println("Hours                 : " + item.getHours());
            System.out.println("Cost per hour         : " + v.getCostPerHour());
            System.out.println("Amount                : " + item.getAmount());
            grandTotal += item.getAmount();
        }
        System.out.println("---------------------------");
        System.out.println("Grand Total Amount     : " + grandTotal);
        System.out.println("---------------------------");

        postSummaryMenu(allRentals, selectedList);
    }

    private static <T extends Vehicle> void postSummaryMenu(List<RentalItem> rentals, List<T> selectedList) {
        while (true) {
            System.out.println("\n1. Proceed to Payment");
            System.out.println("2. Add More Vehicles");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handlePayment(rentals); return;
                case 2:
                    servicesMenu();
                    return;
                case 3: servicesMenu(); return;
                default: System.out.println("Invalid choice, Please Select Correct One");
            }
        }
    }

    // ---------------------- Payment Module ----------------------
    private static void handlePayment(List<RentalItem> rentals) {
        double grandTotal = 0;
        for (RentalItem item : rentals) grandTotal += item.getAmount();

        while (true) {
            System.out.println("\n-------PAYMENT OPTIONS------");
            System.out.println("1. GPay");
            System.out.println("2. PhonePe");
            System.out.println("3. Paytm");
            System.out.print("Enter your choice: ");
            int choice = readIntSafe();

            if (choice == 1 || choice == 2 || choice == 3) {
    if (paymentAuthentication()) {
        printPaymentReceipt(rentals, grandTotal);
        // After successful payment, go back to Services Menu
        servicesMenu();
    }
    return;
}
 else {
                System.out.println("Invalid choice, Please Select Correct One");
            }
        }
    }

    private static boolean paymentAuthentication() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter Registered Phone Number: ");
            String phone = sc.nextLine().trim();
            if (registeredUser != null && registeredUser.getPhone().equals(phone)) {
                return paymentOtpFlow();
            } else {
                attempts--;
                if (attempts > 0) System.out.println("Wrong number, chances left: " + attempts);
            }
        }

        System.out.println("Not found user with this phone number! Moving to Services Menu...");
	servicesMenu();
	return false;

    }

    private static boolean paymentOtpFlow() {
        while (true) {
            int otp = generateOtp();
            System.out.println("Your OTP is: " + otp);

            int attempts = 2;
            while (attempts > 0) {
                System.out.print("Enter OTP: ");
                int userOtp = readIntSafe();
                if (userOtp == otp) {
                    System.out.println("Payment Successful");
                    return true;
                } else {
                    attempts--;
                    if (attempts > 0) System.out.println("Wrong OTP, chances left: " + attempts);
                }
            }
            System.out.println("Payment Failed. Resending OTP...");
        }
    }

    private static void printPaymentReceipt(List<RentalItem> rentals, double grandTotal) {
        System.out.println("\n-------PAYMENT RECEIPT------");
        for (RentalItem item : rentals) {
            Vehicle v = item.getVehicle();
            System.out.println("---------------------------");
            System.out.println("Selected Vehicle Name : " + v.getName());
            System.out.println("Vehicle Number        : " + v.getNumber());
            System.out.println("Hours                 : " + item.getHours());
            System.out.println("Amount                : " + item.getAmount());
        }
        System.out.println("---------------------------");
        System.out.println("Total Paid Amount      : " + grandTotal);
        System.out.println("Payment Done Successfully. Thank you!");
        System.out.println("---------------------------");
    }

    // ---------------------- Helper Methods ----------------------
    private static boolean validateEmail(String email) {
    return email.matches("^[a-z][a-z0-9._-]*@gmail\\.com$");
}


    private static boolean validatePassword(String pass) {
        return pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$");
    }

    private static void printPasswordRules() {
        System.out.println("Password must contain:");
        System.out.println("1. At least one digit");
        System.out.println("2. At least one lowercase letter");
        System.out.println("3. At least one uppercase letter");
        System.out.println("4. At least one special character [@#$%^&+=]");
        System.out.println("5. Length between 8 to 20 characters");
    }

    private static boolean validatePhone(String phone) {
        return phone.matches("^[6-9][0-9]{9}$");
    }

    private static void loginWithPhoneNumber() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter your registered Phone Number: ");
            String phone = sc.nextLine().trim();
            if (registeredUser.getPhone().equals(phone)) {
                if (otpFlow()) {
                    isLoggedIn = true;
                    System.out.println("Login Successful!");
                    return;
                }
            } else {
                attempts--;
                if (attempts > 0) System.out.println("Wrong phone, chances left: " + attempts);
            }
        }
        System.out.println("Entered phone number does not match registered phone number. Moving to Home Page...");
    }

    private static void loginWithEmail() {
    int attempts = 3;
    while (attempts > 0) {
        System.out.print("Enter your registered Email: ");
        String email = sc.nextLine().trim();
        if (registeredUser.getEmail().equals(email)) {
            int passAttempts = 3;
            while (passAttempts > 0) {
                System.out.print("Enter Password: ");
                String enteredPass = sc.nextLine();
                if (registeredUser.password.equals(enteredPass)) {
                    isLoggedIn = true;
                    System.out.println("Login Successful!");
                    return;
                } else {
                    passAttempts--;
                    if (passAttempts > 0)
                        System.out.println("Incorrect password, chances left: " + passAttempts);
                }
            }
            handlePasswordFailure(); // after 3 wrong passwords
            return;
        } else {
            attempts--;
            if (attempts > 0) System.out.println("Wrong email, chances left: " + attempts);
        }
    }
    System.out.println("Not found user with this email!");
    showHomeOptions();
}


    private static boolean otpFlow() {
        int otp = generateOtp();
        System.out.println("Your OTP is: " + otp);
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter OTP: ");
            int enteredOtp = readIntSafe();
            if (enteredOtp == otp) return true;
            else {
                attempts--;
                if (attempts > 0) System.out.println("Incorrect OTP, chances left: " + attempts);
            }
        }
        System.out.println("OTP Verification Failed.");
        return false;
    }

    private static int generateOtp() {
        return 100000 + random.nextInt(900000);
    }

    private static int readIntSafe() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please enter a number: ");
            }
        }
    }
    private static void handlePasswordFailure() {
    while (true) {
        System.out.println("\n1. Reset Password\n2. Login\n3. Exit");
        System.out.print("Enter your choice: ");
        int choice = readIntSafe();
        switch (choice) {
            case 1: resetPasswordFlow(); return;
            case 2: handleLogin(); return;
            case 3: System.out.println("Thank you..."); System.exit(0);
            default: System.out.println("Invalid choice, please try again.");
        }
    }
}
    private static void resetPasswordFlow() {
    int phoneAttempts = 3;
    while (phoneAttempts > 0) {
        System.out.print("Enter Registered Phone Number: ");
        String phone = sc.nextLine().trim();
        if (registeredUser.getPhone().equals(phone)) {
            if (resetOtpFlow()) {
                while (true) {
                    System.out.print("Enter New Password: ");
                    String newPass = sc.nextLine();
                    if (newPass.equals(registeredUser.password)) {
                        System.out.println("New password should not be same as old password!");
                    } else if (validatePassword(newPass)) {
                        registeredUser = new User(registeredUser.name, registeredUser.email, newPass, registeredUser.phone);
                        System.out.println("Password Reset Successful!");
                        handleLogin();
                        return;
                    } else {
                        printPasswordRules();
                    }
                }
            }
            return;
        } else {
            phoneAttempts--;
            if (phoneAttempts > 0)
                System.out.println("Wrong phone number, chances left: " + phoneAttempts);
        }
    }
    System.out.println("Your chances are over. Moving you to login page...");
    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    showHomeOptions();
}
    private static boolean resetOtpFlow() {
    while (true) {
        int otp = generateOtp();
        System.out.println("Your OTP is: " + otp);
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter OTP: ");
            int enteredOtp = readIntSafe();
            if (enteredOtp == otp) return true;
            else {
                attempts--;
                if (attempts > 0) System.out.println("Incorrect OTP, chances left: " + attempts);
            }
        }
        System.out.println("OTP failed. Generating new OTP...");
    }
}
      private static void showHomeOptions() {
    // Empty or maybe just a message
    System.out.println("Redirecting to Home Page...");
    // Then call handleLogin()
    handleLogin();
}





    // ---------------------- Classes ----------------------
    static class User {
        private final String name;
        private final String email;
        private final String password;
        private final String phone;

        public User(String name, String email, String password, String phone) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.phone = phone;
        }

        public String getPhone() { return phone; }
        public String getEmail() { return email; }
    }

    static abstract class Vehicle {
        private final int id;
        private final String name;
        private final String model;
        private final String number;
        private final double costPerHour;

        public Vehicle(int id, String name, String model, String number, double costPerHour) {
            this.id = id;
            this.name = name;
            this.model = model;
            this.number = number;
            this.costPerHour = costPerHour;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getNumber() { return number; }
        public double getCostPerHour() { return costPerHour; }

        @Override
        public String toString() {
            return id + ". " + name + " | Model: " + model + " | Vehicle No: " + number + " | Cost/hour: " + costPerHour;
        }
    }

    static class Bike extends Vehicle {
        private final String mileage;

        public Bike(int id, String name, String model, String mileage, String number, double costPerHour) {
            super(id, name, model, number, costPerHour);
            this.mileage = mileage;
        }

        @Override
        public String toString() {
            return super.toString() + " | Mileage: " + mileage;
        }
    }

    static class Car extends Vehicle {
        private final int seats;
        private final String mileage;

        public Car(int id, String name, String model, int seats, String mileage, String number, double costPerHour) {
            super(id, name, model, number, costPerHour);
            this.seats = seats;
            this.mileage = mileage;
        }

        @Override
        public String toString() {
            return super.toString() + " | Seats: " + seats + " | Mileage: " + mileage;
        }
    }

    static class RentalItem {
        private final Vehicle vehicle;
        private final int hours;
        private final double amount;

        public RentalItem(Vehicle vehicle, int hours, double amount) {
            this.vehicle = vehicle;
            this.hours = hours;
            this.amount = amount;
        }

        public Vehicle getVehicle() { return vehicle; }
        public int getHours() { return hours; }
        public double getAmount() { return amount; }
    }
}