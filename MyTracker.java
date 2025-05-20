package monthTrack;
import java.io.*;
import java.util.*;

class Thing {
    String m;
    String t;
    String cat;
    double amt;

    Thing(String m, String t, String cat, double amt) {
        this.m = m;
        this.t = t;
        this.cat = cat;
        this.amt = amt;
    }
}

public class MyTracker {
    static ArrayList<Thing> list = new ArrayList<>();
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add new");
            System.out.println("2. Load from file");
            System.out.println("3. Show summary");
            System.out.println("4. Quit");
            System.out.print("Pick: ");
            int x = s.nextInt();
            s.nextLine(); // skip line

            if (x == 1) {
                addNew();
            } else if (x == 2) {
                loadIt();
            } else if (x == 3) {
                showIt();
            } else if (x == 4) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Wrong number!");
            }
        }
    }

    static void addNew() {
        System.out.print("Month (yyyy-MM): ");
        String a = s.nextLine();
        System.out.print("Type (income/expense): ");
        String b = s.nextLine();
        System.out.print("Category (like food/rent/salary): ");
        String c = s.nextLine();
        System.out.print("Amount: ");
        double d = s.nextDouble();
        s.nextLine();

        Thing z = new Thing(a, b, c, d);
        list.add(z);
        System.out.println("Added.");
    }

    static void loadIt() {
        System.out.print("File name: ");
        String f = s.nextLine();

        try {
            BufferedReader r = new BufferedReader(new FileReader(f));
            String l;
            int howmany = 0;
            while ((l = r.readLine()) != null) {
                String[] p = l.split(",");
                if (p.length != 4) continue;

                String mm = p[0];
                String tt = p[1];
                String cc = p[2];
                double aa = Double.parseDouble(p[3]);

                Thing t = new Thing(mm, tt, cc, aa);
                list.add(t);
                howmany++;
            }
            r.close();
            System.out.println("Loaded " + howmany + " lines.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    static void showIt() {
        System.out.print("Month to see (yyyy-MM): ");
        String mm = s.nextLine();

        double inc = 0;
        double exp = 0;

        HashMap<String, Double> map1 = new HashMap<>();
        HashMap<String, Double> map2 = new HashMap<>();

        for (Thing t : list) {
            if (t.m.equals(mm)) {
                if (t.t.equals("income")) {
                    inc += t.amt;
                    map1.put(t.cat, map1.getOrDefault(t.cat, 0.0) + t.amt);
                } else {
                    exp += t.amt;
                    map2.put(t.cat, map2.getOrDefault(t.cat, 0.0) + t.amt);
                }
            }
        }

        System.out.println("Month: " + mm);
        System.out.println("Income: " + inc);
        for (String k : map1.keySet()) {
            System.out.println("  " + k + ": " + map1.get(k));
        }

        System.out.println("Expense: " + exp);
        for (String k : map2.keySet()) {
            System.out.println("  " + k + ": " + map2.get(k));
        }

        System.out.println("Saved: " + (inc - exp));
    }
}
