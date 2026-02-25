//javac -encoding UTF-8 BVRF.java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BVRF{
	
    // ===================== UI DESIGN =====================
    static class UI {

        static final String RESET = "\u001B[0m";
        static final String CYAN  = "\u001B[36m";
        static final String GREEN = "\u001B[32m";
	static final String PINK = "\u001B[35m";
	static final String BLUE = "\u001B[34m";
        static final String YELLOW = "\u001B[33m";
        static final String BOLD  = "\u001B[1m";
        static final String BLINK = "\u001B[5m";
	static final String RED = "\u001B[31m";
	static final String BG_WHITE = "\u001B[47m";
       static final String FG_BLACK = "\u001B[30m";


        static void clear() 
	{
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        static void sleep(int ms) 
	{
            try { Thread.sleep(ms); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        static void center(String text) 
	{
    		int consoleWidth = 150; // adjust if CMD size changes
		// remove ANSI color codes before measuring length
    		String plain = text.replaceAll("\u001B\\[[;\\d]*m", "");
    		int pad = (consoleWidth - plain.length()) / 2;
    		if (pad < 0) pad = 0;
    		for (int i = 0; i < pad; i++) 
		{
        		System.out.print(" ");
   		 }
   		 System.out.println(text);
	}
	
	// helper method to generate spaces (Java 7 safe)
	static String spaces(int count) 
	{
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < count; i++) {
        	sb.append(" ");
    		}
    		return sb.toString();
	}

        static void logo() {
    	clear();
    	String[] art = {
        "██████╗ ██╗   ██╗███╗   ███╗██████╗     ███████╗ ██████╗  █████╗  █████╗  ██╗   ██╗",
            "██╔══██╗██║   ██║████╗ ████║██╔══██╗    ██╔════╝██╔════╝ ██╔══██╗██╔══██╗ ██║   ██║",
            "██████╔╝██║   ██║██╔████╔██║██████╔╝    ███████╗██║      ███████║███████║ ██║   ██║",
            "██╔══██╗██║   ██║██║╚██╔╝██║██╔═══╝     ╚════██║██║      ██╔══██║██╔══██║ ╚██╗ ██╔╝",
            "██████╔╝╚██████╔╝██║ ╚═╝ ██║██║         ███████║╚██████╗ ██║  ██║██║  ██║  ╚████╔╝",
            "╚═════╝  ╚═════╝ ╚═╝     ╚═╝╚═╝         ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═══╝"
        };
		for (String line : art) 
		{
        		UI.center(UI.CYAN + UI.BOLD + line + UI.RESET);
        		try { Thread.sleep(300); } catch (InterruptedException e) {}
    		}
    		System.out.println();
	}

     	static void welcome()
	{
    		String msg = "WELCOME TO BUMP SCAAV VEHICLE RENTAL SYSTEM";
    		String color = BOLD + CYAN;
    		System.out.println();
    		int width = 140;
    		int pad = (width - msg.length()) / 2;
    		if (pad < 0) pad = 0;
    		// move cursor to center start
    		for (int i = 0; i < pad; i++) System.out.print(" ");
		// LEFT → RIGHT typing
    		for (int i = 0; i < msg.length(); i++) 
		{
        	System.out.print(color + msg.charAt(i) + RESET);
        	System.out.flush();
        	sleep(60);
		}
		System.out.println("\n");
	}

	static void solidMenuBox() 
	{
   		 int consoleWidth = 150;
    		int innerWidth = 26;           // width for menu text
    		int boxWidth = innerWidth + 4; // ██ + text + ██
    		int padding = (consoleWidth - boxWidth) / 2;
    		if (padding < 0) padding = 0;
    		String pad = spaces(padding);
    		String border = BLUE + BOLD;                  // solid box
    		String blink = BLINK + YELLOW + BOLD;  // blinking menu text
    		String reset = RESET;
    		// build horizontal border (Java 7 safe)
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < boxWidth; i++) sb.append("█");
    		String horizontal = sb.toString();
    		System.out.println();
    // top border
    System.out.println(pad + border + horizontal + reset);

    // empty line
    System.out.println(pad + border + "██" + spaces(innerWidth) + "██" + reset);

    // menu lines (CENTERED + BLINKING)
    BlinkMenu(pad, "  1. Signup", innerWidth, border, blink, reset);
    BlinkMenu(pad, "2. Login",  innerWidth, border, blink, reset);
    BlinkMenu(pad, "3. Exit",   innerWidth, border, blink, reset);

    // empty line
    System.out.println(pad + border + "██" + spaces(innerWidth) + "██" + reset);

    // bottom border
    System.out.println(pad + border + horizontal + reset);

    System.out.println();
}

	static void BlinkMenu(String pad, String text, int innerWidth,  String border, String blink, String reset) 
	{
    		int left = (innerWidth - text.length()) / 2;
    		int right = innerWidth - left - text.length();
   		 System.out.println(
        	pad + border + "██" + reset +
        	spaces(left) +
        	blink + text + reset +
        	spaces(right) +
        	border + "██" + reset
    		);
	}
private static void CenteredBoxLine(
        String pad, String text, int innerWidth,
        String border, String color, String reset) {

    int left = (innerWidth - text.length()) / 2;
    int right = innerWidth - left - text.length();

    System.out.println(
        pad + border + "██" + reset +
        spaces(left) +
        color + text + reset +
        spaces(right) +
        border + "██" + reset
    );
}

static void TwoOptionBox(String opt1, String opt2) {

    int consoleWidth = 150;
    int innerWidth = 26;
    int boxWidth = innerWidth + 4;

    int padding = (consoleWidth - boxWidth) / 2;
    if (padding < 0) padding = 0;

    String pad = spaces(padding);
    String border = BLUE + BOLD;
    String blink  = BLINK + YELLOW + BOLD;
    String reset  = RESET;

    // build horizontal border
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < boxWidth; i++) sb.append("█");
    String horizontal = sb.toString();

    System.out.println();

    // ─── TOP BORDER ───
    System.out.println(pad + border + horizontal + reset);

    // empty padding line
    System.out.println(pad + border + "██" + spaces(innerWidth) + "██" + reset);

    // ─── OPTIONS (using BlinkMenu) ───
    BlinkMenu(pad, opt1, innerWidth, border, blink, reset);
    BlinkMenu(pad, opt2, innerWidth, border, blink, reset);

    // empty padding line
    System.out.println(pad + border + "██" + spaces(innerWidth) + "██" + reset);

    // ─── BOTTOM BORDER ───
    System.out.println(pad + border + horizontal + reset);

    System.out.println();

    // allow blink to be visible once
    sleep(300);
}
static void solidAH(String title) {

    int consoleWidth = 150;
    int innerWidth = title.length() + 10;
    int boxWidth = innerWidth + 4;

    int pad = (consoleWidth - boxWidth) / 2;
    if (pad < 0) pad = 0;

    String space = spaces(pad);

    String border = BLUE + BOLD;
    String steadyText = YELLOW + BOLD;
    String blinkText = BLINK + CYAN + BOLD;
    String reset = RESET;

    // build solid border line
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < boxWidth; i++) sb.append("█");
    String solidLine = sb.toString();

    System.out.println();
    // top border
    System.out.println(space + border + solidLine + reset);

    // empty padding line
    System.out.println(space + border + "██" + spaces(innerWidth) + "██" + reset);

    // text line
    System.out.print(space + border + "██" + reset);

    int leftPad = (innerWidth - title.length()) / 2;
    if (leftPad < 0) leftPad = 0;

    System.out.print(spaces(leftPad));

    // LETTER BY LETTER + BLINK ONCE
    for (int i = 0; i < title.length(); i++) {
        char ch = title.charAt(i);

        // blink once
        System.out.print(blinkText + ch + reset);
        System.out.flush();
        sleep(120);

        // replace with steady color
        System.out.print("\b" + steadyText + ch + reset);
        System.out.flush();
        sleep(80);
    }

    int rightPad = innerWidth - leftPad - title.length();
    System.out.print(spaces(rightPad));

    System.out.println(border + "██" + reset);

    // empty padding line
    System.out.println(space + border + "██" + spaces(innerWidth) + "██" + reset);

    // bottom border
    System.out.println(space + border + solidLine + reset);
    System.out.println();
}
static void error(String msg) {
    System.out.println();
    String text = "-------------- " + msg.toUpperCase() + " --------------";
    center(BOLD + RED + text + RESET);
    System.out.println();
}

static void otp(String msg) {
    System.out.println();
    String text = "-------------- " + msg.toUpperCase() + " --------------";
    center(BOLD + CYAN + text + RESET);
    System.out.println();
}
static void success(String msg) {
    System.out.println();
    String text = ">>>>>>>>>>>> " + msg.toUpperCase() + " <<<<<<<<<<<<";
    center(BOLD + GREEN + text + RESET);
    System.out.println();
}
static void info(String msg) {
    System.out.println();
    center(BOLD + YELLOW + msg + RESET);
    System.out.println();
}
static void promptMid(String text) {
    System.out.println();
    center(BOLD + CYAN + ">> " + text + " :" + RESET);
    System.out.println();
}
static void optionMid(String text) {
    center(YELLOW + text + RESET);
}
static void otpMid(String msg) {
    System.out.println();
    center(BOLD + BLUE +
        "---------------- " + msg + " ----------------"
        + RESET
    );
  System.out.println();
}
static void successMid(String msg) {
    System.out.println();
    center(GREEN + BOLD +
        ">>>>>>>>>>>>>> " + msg + " <<<<<<<<<<<<<<"
        + RESET
    );
   System.out.println();
}
static void infoMid(String msg) {
    System.out.println();
    center(CYAN + msg + RESET);
    System.out.println();
}
static void menuMid(String[] options) {
    int consoleWidth = 150;

    // find longest option length
    int maxLen = 0;
    for (String s : options) {
        if (s.length() > maxLen) maxLen = s.length();
    }

    int padLeft = (consoleWidth - maxLen) / 2;
    if (padLeft < 0) padLeft = 0;

    System.out.println();

    for (String opt : options) {
        StringBuilder line = new StringBuilder();

        // left padding
        for (int i = 0; i < padLeft; i++) line.append(" ");

        // option text padded to same width
        line.append(YELLOW)
            .append(opt)
            .append(spaces(maxLen - opt.length()))
            .append(RESET);

        System.out.println(line.toString());
    }

    System.out.println();
}
// Adjust once if needed
static final int CONSOLE_WIDTH = 150;
static final int FORM_WIDTH = 60;

// start column for centered block
static final int START_COL = (CONSOLE_WIDTH - FORM_WIDTH) / 2;

// prints a straight centered line
static void midLine(String text, String color) {
    System.out.println(
        spaces(START_COL) +
        color + text + RESET
    );
}

// prints aligned input label + keeps cursor on same line
static void midInput(String label) {
    int labelWidth = 22; // fixed width for alignment
    String paddedLabel = String.format("%-" + labelWidth + "s", label);
    System.out.println();
    System.out.print(
        spaces(START_COL) +
        CYAN + BOLD +
        paddedLabel + " : " +
        RESET
    );
   
}
static void passwordRules() {

    midLine("Password must contain", RED + BOLD);
    midLine("---------------------", BLUE);

    midLine("1. At least one digit", YELLOW);
    midLine("2. At least one lowercase letter", YELLOW);
    midLine("3. At least one uppercase letter", YELLOW);
    midLine("4. At least one special character [@#$%^&+=]", YELLOW);
    midLine("5. Length between 8 to 20 characters", YELLOW);

    System.out.println(); // space after block
}
static void carAnimation() {

    // BIG LEFT-FACING car (front on LEFT)
    String[][] carFrames = {

        // FRAME 1
        {
            "<<<<_____________                ",
            "___/_____||____\\\\______________ ",
            "/__    _          _      _   __ |",
            "| |___| |________| |____| |__|_||",
            "'--(_)--------------------------(_)-'"
        },

        // FRAME 2 (wheel rotation)
        {
            "<<<<_____________                ",
            "___/_____||____\\\\______________ ",
            "/__    _          _      _   __ |",
            "| |___| |________| |____| |__|_||",
            "'--(/)--------------------------(\\)-'"
        }
    };

    int consoleWidth = 150;
    int carWidth = 75;
    int verticalOffset = 6;
    int frame = 0;

    // LEFT ➜ RIGHT movement
    for (int pos = 0; pos <= consoleWidth - carWidth; pos += 2) {
        UI.clear();

        // vertical spacing (center height)
        for (int i = 0; i < verticalOffset; i++) {
            System.out.println();
        }

        // draw car
        for (String line : carFrames[frame]) {
            System.out.println(
                UI.spaces(pos) + UI.GREEN + UI.BOLD + line + UI.RESET
            );
        }

        frame = (frame + 1) % carFrames.length; // rotate wheels
        UI.sleep(90);
    }

    UI.clear();
}


static void animatedCenterMenu(String... options) {
    for (String opt : options) {
        midLine(opt, YELLOW + BOLD);
        sleep(400);     // delay between options
        System.out.println();
    }
}
static void paymentSummary(
        String vehicleName,
        String vehicleNumber,
        int hours,
        double costPerHour,
        double amount,
        double grandTotal
) {
    int labelWidth = 22;
    int blockWidth = 55;
    int start = (CONSOLE_WIDTH - blockWidth) / 2;

    String labelC = CYAN;
    String valueC = YELLOW + BOLD;
    String moneyC = GREEN + BOLD;

    System.out.println();

    System.out.println(spaces(start) + labelC +
        String.format("%-" + labelWidth + "s : ", "Selected Vehicle Name") +
        valueC + vehicleName + RESET);

    System.out.println(spaces(start) + labelC +
        String.format("%-" + labelWidth + "s : ", "Vehicle Number") +
        valueC + vehicleNumber + RESET);

    System.out.println(spaces(start) + labelC +
        String.format("%-" + labelWidth + "s : ", "Hours") +
        valueC + hours + RESET);

    System.out.println(spaces(start) + labelC +
        String.format("%-" + labelWidth + "s : ", "Cost per Hour") +
        moneyC + "Rs. " + costPerHour + RESET);

    System.out.println(spaces(start) + labelC +
        String.format("%-" + labelWidth + "s : ", "Amount") +
        moneyC + "Rs. " + amount + RESET);

    System.out.println();
    System.out.println(spaces(start) + labelC + "------------------------------------------" + RESET);
    System.out.println(spaces(start) + moneyC +
        String.format("%-" + labelWidth + "s : Rs. %.2f", "GRAND TOTAL AMOUNT", grandTotal) +
        RESET);
    System.out.println(spaces(start) + labelC + "------------------------------------------" + RESET);
    System.out.println();
}
static String repeat(char c, int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) sb.append(c);
    return sb.toString();
}

static String padRight(String text, int width) {
    if (text.length() >= width) return text;
    return text + repeat(' ', width - text.length());
}
static void whitePaperReceipt(
        String invoiceNo,
        String vehicleName,
        String vehicleNo,
        int hours,
        double costPerHour,
        double amount,
        double total
) {
    int paperWidth = 60;        // receipt width
    int consoleWidth = 150;
    int leftPad = (consoleWidth - paperWidth) / 2;
    if (leftPad < 0) leftPad = 0;

    String pad = spaces(leftPad);
    String bg = BG_WHITE + FG_BLACK;
    String reset = RESET;

    System.out.println();

    // top edge
    System.out.println(pad + bg + repeat(' ', paperWidth) + reset);

    printPaperLine(pad, "INVOICE NO : " + invoiceNo, paperWidth, bg);
    printPaperLine(pad, repeat('-', paperWidth), paperWidth, bg);
    printPaperLine(pad, "PAYMENT RECEIPT", paperWidth, bg);
    printPaperLine(pad, repeat('-', paperWidth), paperWidth, bg);

    printPaperKV(pad, "Vehicle Name", vehicleName, paperWidth, bg);
    printPaperKV(pad, "Vehicle Number", vehicleNo, paperWidth, bg);
    printPaperKV(pad, "Hours", String.valueOf(hours), paperWidth, bg);
    printPaperKV(pad, "Cost / Hour", "Rs. " + costPerHour, paperWidth, bg);
    printPaperKV(pad, "Amount", "Rs. " + amount, paperWidth, bg);

    printPaperLine(pad, repeat('-', paperWidth), paperWidth, bg);
    printPaperKV(pad, "TOTAL PAID", "Rs. " + total, paperWidth, bg);
    printPaperLine(pad, repeat('-', paperWidth), paperWidth, bg);

    // bottom edge
    System.out.println(pad + bg + repeat(' ', paperWidth) + reset);
    System.out.println();
}
static void printPaperLine(String pad, String text, int width, String bg) {
    String line = padRight(text, width);
    System.out.println(pad + bg + line + RESET);
}

static void printPaperKV(
        String pad,
        String key,
        String value,
        int width,
        String bg
) {
    String line = padRight(key, 18) + ": " + value;
    line = padRight(line, width);
    System.out.println(pad + bg + line + RESET);
}
static String generateInvoiceNumber() {
    java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("yyyyMMdd");
    String date = sdf.format(new java.util.Date());
    int rand = 1000 + new java.util.Random().nextInt(9000);
    return "INV-" + date + "-" + rand;
}
static void blockLine(int leftPad, String label, String value, String color) {
    int labelWidth = 12; // FIXED width for all labels
    System.out.println(
        spaces(leftPad) +
        color +
        String.format("%-" + labelWidth + "s : %s", label, value) +
        RESET
    );
}
static void thankYouAnimation() {

    String[] THANK_YOU = {
        "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗",
        "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝",
        "   ██║   ███████║███████║██╔██╗ ██║█████╔╝",
        "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗",
        "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗",
        "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝",
        "",
        "██╗   ██╗ ██████╗ ██╗   ██╗",
        "╚██╗ ██╔╝██╔═══██╗██║   ██║",
        " ╚████╔╝ ██║   ██║██║   ██║",
        "  ╚██╔╝  ██║   ██║██║   ██║",
        "   ██║   ╚██████╔╝╚██████╔╝",
        "   ╚═╝    ╚═════╝  ╚═════╝"
    };

    int consoleWidth = 150;

    // ---------- TYPING EFFECT ----------
    for (String line :THANK_YOU ) {
        int pad = (consoleWidth - line.length()) / 2;
        if (pad < 0) pad = 0;

        System.out.print(spaces(pad));
        for (int i = 0; i < line.length(); i++) {
            System.out.print("\u001B[33m" + line.charAt(i) + "\u001B[0m");
            System.out.flush();
            sleep(6);
        }
        System.out.println();
        sleep(80);
    }

 // ---------- FADE ----------
    String[] fade = { YELLOW, CYAN, BLUE };
    for (String c : fade) {
        clear();
        for (String line : THANK_YOU) {
            center(c + line + RESET);
        }
        sleep(350);
    }

    // ---------- FOOTER ----------
    System.out.println();
    center(GREEN + BOLD + "THANK YOU FOR USING BUMP SCAAV" + RESET);
    center(YELLOW + "DRIVE SAFE  |  RIDE SMART  |  SEE YOU AGAIN" + RESET);

    System.out.println();
    sleep(900);
    center(GREEN + "Logging out... Please visit again!" + RESET);
    sleep(1200);
}

}
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
	UI.logo();
	UI.welcome();
        initVehicles();
        runApplication();
    }

    // ---------------------- Initialization ----------------------
    private static void initVehicles() {
        // Two Wheeler EV
        twoWheelerEV.add(new Bike(1, "LogicSpark Gayatri Apti EV Pro", "EV-2023", "120 km/charge", "TS09 EV 0001", 120));
        twoWheelerEV.add(new Bike(2, "SilentJava Krishna Code EV 1.0", "EV-2022", "110 km/charge", "TS09 EV 0002", 100));
        twoWheelerEV.add(new Bike(3, "MainCharacter Pavani Prime EV Plus", "EV-2024", "130 km/charge", "TS09 EV 0003", 150));
        twoWheelerEV.add(new Bike(4, "TechBug Sai Gopi Byte EV Lite", "EV-2021", "100 km/charge", "TS09 EV 0004", 90));
        twoWheelerEV.add(new Bike(5, "PositiveCharge Srivani Smile EV 2.0", "EV-2023", "140 km/charge", "TS09 EV 0005", 160));

        // Two Wheeler Petrol
        twoWheelerPetrol.add(new Bike(1, "NoFilter Phani RawRide 350R", "PT-2019", "45 kmpl", "TS09 PT 1001", 80));
        twoWheelerPetrol.add(new Bike(2, "FullEnergy Vamsi Boost RS", "PT-2020", "50 kmpl", "TS09 PT 1002", 90));
        twoWheelerPetrol.add(new Bike(3, "JokeSprint Umar Laugh Street", "PT-2018", "60 kmpl", "TS09 PT 1003", 70));
        twoWheelerPetrol.add(new Bike(4, "CricketMode Bhargav Pitch GT", "PT-2021", "55 kmpl", "TS09 PT 1004", 85));
        twoWheelerPetrol.add(new Bike(5, "FastTalk Charan Speedster X", "PT-2022", "52 kmpl", "TS09 PT 1005", 95));

        // Four Wheeler EV
        fourWheelerEV.add(new Car(1, "SoftHeart Sneha Care EV Prime", "EV-SD-2024", 5, "300 km/charge", "TS09 EV 2001", 400));
        fourWheelerEV.add(new Car(2, "PerfectControl Praveena Elite EV Auto", "EV-SUV-2023", 7, "320 km/charge", "TS09 EV 2002", 450));
        fourWheelerEV.add(new Car(3, "HummingDrive Sravani Tune EV Plus", "EV-HB-2022", 5, "280 km/charge", "TS09 EV 2003", 350));
        fourWheelerEV.add(new Car(4, "CuteButCrazy Shriya Mini Fun EV", "EV-HB-2021", 4, "260 km/charge", "TS09 EV 2004", 320));
        fourWheelerEV.add(new Car(5, "CreativeSoul Sucharita Studio EV", "EV-SD-2024", 5, "310 km/charge", "TS09 EV 2005", 380));

        // Four Wheeler Petrol/Diesel
        fourWheelerPetrolDiesel.add(new Car(1, "JoyRide Chaitanya Vaddi Leader Executive", "PD-HB-2019", 5, "18 kmpl", "TS09 PD 3001", 250));
        fourWheelerPetrolDiesel.add(new Car(2, "OverThinker Chaitanya Brain XL", "PD-HB-2020", 5, "20 kmpl", "TS09 PD 3002", 270));
        fourWheelerPetrolDiesel.add(new Car(3, "FocusDrive Sai Teja Sport Pro", "PD-MPV-2018", 7, "15 kmpl", "TS09 PD 3003", 350));
        fourWheelerPetrolDiesel.add(new Car(4, "SavageMode Afrid Turbo X", "PD-SUV-2022", 5, "14 kmpl", "TS09 PD 3004", 400));
        fourWheelerPetrolDiesel.add(new Car(5, "VolumeMax Pawan Loud Edition", "PD-SD-2021", 5, "17 kmpl", "TS09 PD 3005", 300));

        // Four Wheeler CNG
        fourWheelerCng.add(new Car(1, "SilentBeast Mansoor Eco Force", "CNG-HB-2021", 5, "26 km/kg", "TS09 CNG 4001", 220));
        fourWheelerCng.add(new Car(2, "ExamFear Naresh Pressure Plus", "CNG-HB-2020", 4, "28 km/kg", "TS09 CNG 4002", 200));
        fourWheelerCng.add(new Car(3, "AttitudeSport Harshitha Blackline", "CNG-MPV-2022", 7, "24 km/kg", "TS09 CNG 4003", 280));
        fourWheelerCng.add(new Car(4, "InnocentLook Swathi City Comfort", "CNG-SD-2019", 5, "25 km/kg", "TS09 CNG 4004", 240));
        fourWheelerCng.add(new Car(5, "SmileEdition Pravalleka Happy Drive", "CNG-VAN-2023", 8, "23 km/kg", "TS09 CNG 4005", 300));
    }

    // ---------------------- Application Flow ----------------------
    private static void runApplication() {
        while (true) {
            UI.solidMenuBox();
            UI.midInput("Enter your choice");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handleSignup(); break;
                case 2: handleLogin(); break;
                case 3: UI.info("Thank you for using Bump Scaav Vehicle Rental System"); return;
                default: UI.error("Invalid choice, please select correct one");

            }
        }
    }

    // ---------------------- Signup ----------------------
    private static void handleSignup() {
        UI.solidAH("SIGNUP");
        UI.midInput("Enter Name");
        String name = sc.nextLine().trim();
        while (!name.matches("(?=.*[A-Za-z])[A-Za-z ]{3,}")) {
            UI.error("Name must contain at least 3 alphabetic characters");
            UI.midInput("Enter Name");
            name = sc.nextLine().trim();
        }

        String email;
        while (true) {
            UI.midInput("Enter Email");
            email = sc.nextLine().trim();
            if (validateEmail(email)) break;
            else 
	    {
		System.out.println();
		UI.error("Invalid Email, must end with @gmail.com");
	   }
        }

        String password;
        while (true) {
            UI.midInput("Enter Password");
            password = sc.nextLine();
            if (validatePassword(password)) break;
            else UI.passwordRules();

        }

        String phone;
        while (true) {
            UI.midInput("Enter Phone Number");
            phone = sc.nextLine().trim();
            if (validatePhone(phone)) break;
            else 
		{
		
		UI.error("Invalid phone number! Must start with 6,7,8,9 and contain 10 digits");
		
		}
        }

        registeredUser = new User(name, email, password, phone);
	System.out.println();
        UI.success("Registered successfully");
        while (true) {
            UI.TwoOptionBox(
        "1. Login",
        "2. Exit"
	);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();
            if (choice == 1) { handleLogin(); return; }
            else if (choice == 2) { UI.info("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0); }
            else UI.error("Invalid choice, please select correct one");

        }
    }

    // ---------------------- Login ----------------------
    private static void handleLogin() {
        if (registeredUser == null) {
            UI.error("No user found. Please signup first");
            while (true) {
            			UI.TwoOptionBox(
				"1. Signup", 
			      "2. Exit"
				);
				UI.midInput("Enter your choice");
                		int choice = readIntSafe();
                		if (choice == 1) { handleSignup(); return; }
                		else if (choice == 2) { UI.info("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0); }
                		else UI.error("Invalid choice, please select correct one");

            			}
        }

        while (true) {
		                System.out.println();
           			UI.animatedCenterMenu(    					
					"1. Login with Phone Number",
    					"2. Login with Email",
    					"3. Exit"
				);
			UI.midInput("Enter your choice");
           		 int choice = readIntSafe();
            		switch (choice) {
                	case 1: loginWithPhoneNumber(); if (isLoggedIn) servicesMenu(); break;
                	case 2: loginWithEmail(); if (isLoggedIn) servicesMenu(); break;
                	case 3: UI.info("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0);
                	default: UI.error("Invalid choice, please select correct one");

            }
        }
    }

    // ---------------------- Services Menu ----------------------
    private static void servicesMenu() {
        while (true) {
            UI.solidAH("OUR SERVICES");
	    UI.carAnimation();
           UI.animatedCenterMenu(
    			"1. Two Wheeler",
    			"2. Four Wheeler",
    			"3. Exit"
		);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handleTwoWheeler(); break;
                case 2: handleFourWheeler(); break;
                case 3: UI.info("Thank you for using Bump Scaav Vehicle Rental System"); System.exit(0);
                default: UI.error("Invalid choice, please select correct one");

            }
        }
    }

    // ---------------------- Two Wheeler ----------------------
    private static void handleTwoWheeler() {
        while (true) {
	    System.out.println();
	    UI.animatedCenterMenu(
			"1. EV",
			"2. Petrol"
		);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();
            List<Bike> selectedList = null;
            switch (choice) {
                case 1: selectedList = twoWheelerEV; break;
                case 2: selectedList = twoWheelerPetrol; break;
                default: UI.error("Invalid choice, please select correct one");
; continue;
            }
            List<RentalItem> rentals = selectMultipleVehicles(selectedList);
            if (!rentals.isEmpty()) printRentalSummary(rentals, selectedList);
            return;
        }
    }

    // ---------------------- Four Wheeler ----------------------
    private static void handleFourWheeler() {
        while (true) {
            System.out.println();
	    UI.animatedCenterMenu(
			"1. EV",
			"2. Petrol/Diesel",
			"3. CNG"
		);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();
            List<Car> selectedList = null;
            switch (choice) {
                case 1: selectedList = fourWheelerEV; break;
                case 2: selectedList = fourWheelerPetrolDiesel; break;
                case 3: selectedList = fourWheelerCng; break;
                default: UI.error("Invalid choice, please select correct one"); continue;
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
            System.out.println();

int blockWidth = 60;
int consoleWidth = 150;
int leftPad = (consoleWidth - blockWidth) / 2;

// HEADER
System.out.println();
System.out.println(UI.spaces(leftPad) + UI.BLUE + UI.BOLD +
        "============================================================" +
        UI.RESET);
System.out.println(UI.spaces(leftPad) + UI.YELLOW + UI.BOLD +
        "                    VEHICLE RENTAL LIST" +
        UI.RESET);
System.out.println(UI.spaces(leftPad) + UI.BLUE + UI.BOLD +
        "============================================================" +
        UI.RESET);

for (T v : vehicleList) {

    System.out.println(UI.spaces(leftPad) + UI.BLUE +
            "------------------------------------------------------------" +
            UI.RESET);

    UI.blockLine(leftPad, "ID", String.valueOf(v.getId()), UI.CYAN + UI.BOLD);
    UI.blockLine(leftPad, "Name", v.getName(), UI.YELLOW);
    UI.blockLine(leftPad, "Model", v.getModel(), UI.YELLOW);
    UI.blockLine(leftPad, "Number", v.getNumber(), UI.YELLOW);
    UI.blockLine(leftPad, "Cost/Hour", "Rs. " + v.getCostPerHour(), UI.GREEN);

    if (v instanceof Bike) {
        UI.blockLine(leftPad, "Mileage",
                ((Bike) v).getMileage(), UI.CYAN);
    } else if (v instanceof Car) {
        UI.blockLine(leftPad, "Seats",
                String.valueOf(((Car) v).getSeats()), UI.CYAN);
        UI.blockLine(leftPad, "Mileage",
                ((Car) v).getMileage(), UI.CYAN);
    }
}

System.out.println(UI.spaces(leftPad) + UI.BLUE + UI.BOLD +
        "============================================================" +
        UI.RESET);

System.out.println();
//UI.center(UI.BOLD + UI.CYAN + "Select Vehicle Id :" + UI.RESET);


            UI.midInput("Select Vehicle Id ");
            int id = readIntSafe();

            T selectedVehicle = null;
            for (T v : vehicleList) {
                if (v.getId() == id) {
                    selectedVehicle = v;
                    break;
                }
            }

            if (selectedVehicle == null) {
                UI.error("Invalid choice, please select correct one");
                continue;
            }

            int hours = readRentalHours();
            double amount = hours * selectedVehicle.getCostPerHour();
            rentals.add(new RentalItem(selectedVehicle, hours, amount));

            while (true) {
    UI.midInput("Do you want to add another vehicle? (y/n) ");
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
        UI.error("Invalid choice, please try again.");
    }
}
        }
        return rentals;
    }

    private static int readRentalHours() {
        while (true) {
            UI.midInput("Enter Hours (Rentals only in hours) ");
            int hours = readIntSafe();
            if (hours >= 1) return hours;
            System.out.println("Invalid Input, must be at least 1 hour.");
        }
    }

    // ---------------------- Rental Summary ----------------------
    private static <T extends Vehicle> void printRentalSummary(List<RentalItem> rentals, List<T> selectedList) {
        UI.solidAH("RENTAL SUMMARY");
        double grandTotal = 0;
        for (RentalItem item : allRentals) {
            Vehicle v = item.getVehicle();
	    grandTotal += item.getAmount();
            UI.paymentSummary(
    v.getName(),
    v.getNumber(),
    item.getHours(),
    v.getCostPerHour(),
    item.getAmount(),
    grandTotal
);
//grandTotal += item.getAmount();
        }
        postSummaryMenu(allRentals, selectedList);
    }

    private static <T extends Vehicle> void postSummaryMenu(List<RentalItem> rentals, List<T> selectedList) {
        while (true) {
            System.out.println();
	    UI.animatedCenterMenu(
			"1. Proceed to Payment",
			"2. Add More Vehicles",
			"3. Exit"
		);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();
            switch (choice) {
                case 1: handlePayment(rentals); return;
                case 2:
                    servicesMenu();
                    return;
                case 3: servicesMenu(); return;
                default: UI.error("Invalid choice, please select correct one");

            }
        }
    }

    // ---------------------- Payment Module ----------------------
    private static void handlePayment(List<RentalItem> rentals) {
        double grandTotal = 0;
        for (RentalItem item : rentals) grandTotal += item.getAmount();

        while (true) {
            UI.solidAH("PAYMENT OPTIONS");
            System.out.println();
	    UI.animatedCenterMenu(
			"1. GPay",
			"2. PhonePe",
			"3. Paytm"
		);
            UI.midInput("Enter your choice ");
            int choice = readIntSafe();

            if (choice == 1 || choice == 2 || choice == 3) {
    if (paymentAuthentication()) {
/*
        printPaymentReceipt(rentals, grandTotal);
        // After successful payment, go back to Services Menu
        servicesMenu();
*/
String invoiceNo = UI.generateInvoiceNumber();

    for (RentalItem item : rentals) {
        Vehicle v = item.getVehicle();

        UI.whitePaperReceipt(
            invoiceNo,
            v.getName(),
            v.getNumber(),
            item.getHours(),
            v.getCostPerHour(),
            item.getAmount(),
            grandTotal
        );
    }
 UI.successMid("PAYMENT COMPLETED SUCCESSFULLY");
    postPaymentMenu();
}
    return;
}
 else {
                UI.error("Invalid choice, please select correct one");

            }
        }
    }

    private static boolean paymentAuthentication() {
        int attempts = 3;
        while (attempts > 0) {
            UI.midInput("Enter Registered Phone Number ");
            String phone = sc.nextLine().trim();
            if (registeredUser != null && registeredUser.getPhone().equals(phone)) {
                return paymentOtpFlow();
            } else {
                attempts--;
                if (attempts > 0) System.out.println("Wrong number, chances left: " + attempts);
            }
        }

        UI.error("Not found user with this phone number!");

showLoading("Moving to Services Menu",5, 800); // 5 dots, 0.8s each (~4s)
try { Thread.sleep(3000); } catch (InterruptedException e) {}
servicesMenu();
return false;


    }

    private static boolean paymentOtpFlow() {
        while (true) {
            
showLoading( "Generating OTP, please wait", 3, 700);
try { Thread.sleep(2000); } catch (InterruptedException e) {}
int otp = generateOtp();
UI.otp("Your OTP is : " + otp);
            int attempts = 2;
            while (attempts > 0) {
                UI.midInput("Enter OTP ");
                int userOtp = readIntSafe();
                if (userOtp == otp) {
                    UI.success("Payment Successful");
                    return true;
                } else {
                    attempts--;
                    if (attempts > 0) UI.otp("Wrong OTP, chances left: " + attempts);
                }
            }
            UI.error("Payment Failed. Resending OTP...");
        }
    }

    private static void printPaymentReceipt(List<RentalItem> rentals, double grandTotal) {
    UI.solidAH("PAYMENT RECEIPT");
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
    UI.success("Payment Done Successfully. Thank you!");
    System.out.println("---------------------------");

    // NEW: Show Drive Safely banner after 2 seconds
    showDriveSafelyMessage();
}


    // ---------------------- Helper Methods ----------------------
    private static boolean validateEmail(String email) {
    return email.matches("^[a-z][a-z0-9._-]*@gmail\\.com$");
}
    private static void postPaymentMenu() {
    while (true) {
        System.out.println();
        UI.animatedCenterMenu(
            "1. Our Services",
            "2. Logout"
        );

        UI.midInput("Enter your choice ");
        int choice = readIntSafe();

        switch (choice) {
            case 1:
                servicesMenu();
                return;

            case 2:
                logoutFlow();
                return;

            default:
                UI.error("Invalid choice, please select correct one");
        }
    }
}
     private static void logoutFlow() {
    UI.infoMid("Logging you out from this application");
    showLoading("", 5, 700);   // animation dots
    try { Thread.sleep(2000); } catch (InterruptedException e) {}

    //UI.successMid("You have been logged out successfully");
    UI.thankYouAnimation();
    System.exit(0);
}

    // ---------------------- Drive Safely Banner ----------------------
private static void showDriveSafelyMessage() {
    try {
        Thread.sleep(2000); // wait 2 seconds after payment
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    System.out.println("\n===============================================================");
    System.out.println(" 							DRIVE SAFELY ALWAYS!   					          ");
    System.out.println("  ============================================================== \n");
}

    private static boolean validatePassword(String pass) {
        return pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$");
    }

    private static boolean validatePhone(String phone) {
        return phone.matches("^[6-9][0-9]{9}$");
    }

    private static void loginWithPhoneNumber() {
        int attempts = 3;
        while (attempts > 0) {
            UI.midInput("Enter your registered Phone Number");
            String phone = sc.nextLine().trim();
            if (registeredUser.getPhone().equals(phone)) {
                if (otpFlow()) {
                    isLoggedIn = true;
                    UI.success("Login Successful!");
                    return;
                }
            } else {
                attempts--;
                if (attempts > 0) System.out.println("Wrong phone, chances left: " + attempts);
            }
        }
        System.out.println("Entered phone number does not match registered phone number.");
showLoading("Moving to Login Page", 3, 600);
try { Thread.sleep(2000); } catch (InterruptedException e) {}
showHomeOptions();

    }

    private static void loginWithEmail() {
    int attempts = 3;
    while (attempts > 0) {
        UI.midInput("Enter your registered Email ");
        String email = sc.nextLine().trim();
        if (registeredUser.getEmail().equals(email)) {
            int passAttempts = 3;
            while (passAttempts > 0) {
                UI.midInput("Enter Password ");
                String enteredPass = sc.nextLine();
                if (registeredUser.password.equals(enteredPass)) {
                    isLoggedIn = true;
		    System.out.println();
                    UI.success("Login Successful!");
                    return;
                } else {
                    passAttempts--;
                    if (passAttempts > 0)
                        UI.error("Incorrect password, chances left: " + passAttempts);
                }
            }
            handlePasswordFailure(); // after 3 wrong passwords
            return;
        } else {
            attempts--;
            if (attempts > 0) UI.error("WRONG EMAIL, CHANCES LEFT : " + attempts);

        }
    }
    UI.error("USER NOT FOUND WITH THIS EMAIL");
	UI.otpMid("MOVING TO HOME PAGE");
      showLoading("", 3, 600);
      try { Thread.sleep(2000); } catch (InterruptedException e) {}
    showHomeOptions();

}


    private static boolean otpFlow() {
    UI.otpMid("GENERATING OTP, PLEASE WAIT");
    showLoading("", 5, 800);
    try { Thread.sleep(3000); } catch (InterruptedException e) {} // extra 3s pause
    int otp = generateOtp();
    UI.otp("Your OTP is : " + otp);
    int attempts = 3;
    while (attempts > 0) {
        UI.midInput("Enter OTP ");
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
    // ---------------------- Loading Animation ----------------------
private static void showLoading(String message, int dotCount, int delayMillis) {
    System.out.print(message);
    for (int i = 0; i < dotCount; i++) {
        try {
            Thread.sleep(delayMillis); // pause before next dot
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print(".");
    }
    System.out.println(); // move to next line
}

    private static void handlePasswordFailure() {
    while (true) {
		System.out.println();
        	UI.animatedCenterMenu(
    					"1. Reset Password",
    					"2. Login",
    					"3. Exit"
				);
        UI.midInput("Enter your choice ");
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
        UI.midInput("Enter Registered Phone Number ");
        String phone = sc.nextLine().trim();
        if (registeredUser.getPhone().equals(phone)) {
            if (resetOtpFlow()) {
                while (true) {
                    UI.midInput("Enter New Password ");
                    String newPass = sc.nextLine();
                    if (newPass.equals(registeredUser.password)) {
                        UI.error("NEW PASSWORD SHOULD NOT BE SAME AS OLD PASSWORD");
                    } else if (validatePassword(newPass)) {
                        registeredUser = new User(registeredUser.name, registeredUser.email, newPass, registeredUser.phone);
                        UI.successMid("PASSWORD RESET SUCCESSFUL");
                        handleLogin();
                        return;
                    } else {
                        UI.passwordRules();
                    }
                }
            }
            return;
        } else {
            phoneAttempts--;
            if (phoneAttempts > 0)
                UI.error("Wrong phone number, chances left: " + phoneAttempts);
        }
    }
    System.out.println("Your chances are over.");
showLoading("Redirecting to Login Page", 3, 600);
try { Thread.sleep(2000); } catch (InterruptedException e) {}
showHomeOptions();

}
    private static boolean resetOtpFlow() {
    while (true) {
        showLoading("Generating OTP, please wait", 3, 700);
try { Thread.sleep(2000); } catch (InterruptedException e) {}
int otp = generateOtp();
UI.otp("Your OTP is : " + otp);
        int attempts = 3;
        while (attempts > 0) {
            UI.midInput("Enter OTP ");
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
    UI.infoMid("Redirecting to Login Page...");
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
    public double getCostPerHour() { return costPerHour; }  // ✅ closed properly
    public String getModel() { return model; }             // ✅ separate method

        @Override
public String toString() {
    return id + ". " + name + " | Model: " + model + " | Cost/hour: " + costPerHour;
}
}

    static class Bike extends Vehicle {
        private final String mileage;

        public Bike(int id, String name, String model, String mileage, String number, double costPerHour) {
            super(id, name, model, number, costPerHour);
            this.mileage = mileage;
        }
	public String getMileage() { return mileage; }


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
	public int getSeats() { return seats; }
public String getMileage() { return mileage; }


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
