import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class person {
	private String name;
	private int age;
	private char gender;
	private int height;
	private double weight;

	public person(String name, int age, char gender, int height, double weight) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public char getGender() {
		return gender;
	}

	public int getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void displayInfo() {
		System.out.println("Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nHeight(cm): " + height
				+ "\nWeight(kg): " + weight);
	}
}

class member extends person {
	private String ID;

	public member(String name, int age, char gender, int height, double weight, String ID) {
		super(name, age, gender, height, weight);
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	@Override
	public void displayInfo() {
		System.out.println("Name: " + getName() + "\nAge: " + getAge() + "\nGender: " + getGender() + "\nHeight(cm): "
				+ getHeight() + "\nWeight(Kg): " + getWeight() + "\nID:" + ID);
	}
}

enum activityLevel {
	SEDENTARY(1.2),
	LIGHTLY_ACTIVE(1.375),
	MODERATELY_ACTIVE(1.55),
	VERY_ACTIVE(1.725),
	EXTRA_ACTIVE(1.9);

	private final double factor;

	private activityLevel(double factor) {
		this.factor = factor;
	}

	public double getFactor() {
		return factor;
	}
}

enum Goal {
	MAINTAIN(0),
	LOSE(-400),
	GAIN(400);

	private final int calorie;

	private Goal(int calorie) {
		this.calorie = calorie;
	}

	public int getCalorie() {
		return calorie;
	}
}

public class calorie_counter {
    // list to store the profiles created in the app
    private ArrayList<person> members = new ArrayList<>();

    public void menu(Scanner sc) {
        loadProfiles();

        while (true) {
            System.out.println("\n\t\t--- Calorie Counter Menu ---");
            System.out.println("1. Create a profile");
            System.out.println("2. Remove a profile");
            System.out.println("3. Track your weight");
            System.out.println("4. Calculate your BMI");
            System.out.println("5. Calculate daily calories");
            System.out.println("6. Calculate your macros");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    createProfile(sc);
                    break;
                case 2:
                    System.out.println("Remove profile feature coming soon.");
                    break;
                case 3:
                    trackWeight(members, sc);
                    break;
                case 4:
                    calculateBMI(members, sc);
                    break;
                case 5:
                    calculateCalories(members, sc);
                    break;
                case 6:
                    calculateMacros(members, sc);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return; 
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void createProfile(Scanner sc) {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Gender (M/F): ");
            char gender = sc.nextLine().toUpperCase().charAt(0);
            System.out.print("Height (cm): ");
            int height = Integer.parseInt(sc.nextLine());
            System.out.print("Weight (kg): ");
            double weight = Double.parseDouble(sc.nextLine());
            System.out.print("Enter a unique Member ID: ");
            String id = sc.nextLine();

            member newMember = new member(name, age, gender, height, weight, id);
            members.add(newMember);
            
            saveProfiles();
            
            System.out.println("Profile created successfully for " + name + "!");

        } catch (Exception e) {
            System.out.println("Error creating profile. Please enter valid numbers.");
        }
    }

    public void trackWeight(ArrayList<person> members, Scanner sc) {
        System.out.print("Enter Member ID to update weight: ");
        String id = sc.nextLine();
        boolean found = false;

        for (person p : members) {
            if (p instanceof member) {
                member m = (member) p;
                if (m.getID().equals(id)) {
                    found = true;
                    System.out.println("Current Weight: " + m.getWeight() + " kg");
                    System.out.print("Enter New Weight (kg): ");
                    try {
                        double newWeight = Double.parseDouble(sc.nextLine());
                        m.setWeight(newWeight); 
                        
                        // 3. SAVE DATA IMMEDIATELY AFTER UPDATING WEIGHT
                        saveProfiles();
                        
                        System.out.println("Weight updated successfully to " + newWeight + " kg!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid weight entered.");
                    }
                }
            }
        }
        if (!found) System.out.println("Member not found.");
    }

    private void saveProfiles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profiles.txt"))) {
            for (person p : members) {
                if (p instanceof member) {
                    member m = (member) p;
                    // Format: Name,Age,Gender,Height,Weight,ID
                    String line = m.getName() + "," + m.getAge() + "," + m.getGender() + "," +
                                  m.getHeight() + "," + m.getWeight() + "," + m.getID();
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving profiles to file.");
        }
    }

    private void loadProfiles() {
        File file = new File("profiles.txt");
        if (!file.exists()) return; // If file doesn't exist yet, just return

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            members.clear(); // Clear list to avoid duplicates if called multiple times
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    char gender = parts[2].charAt(0);
                    int height = Integer.parseInt(parts[3]);
                    double weight = Double.parseDouble(parts[4]);
                    String id = parts[5];
                    
                    members.add(new member(name, age, gender, height, weight, id));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading profiles.");
        }
    }

    public void calculateCalories(ArrayList<person> members, Scanner sc) {
        if (members.isEmpty()) {
            System.out.println("No profiles found. Please create a profile first.");
            return;
        }
        System.out.println("\n--- Available Profiles ---");
        for (person p : members) {
            if (p instanceof member) {
                System.out.println("Name: " + p.getName() + " | ID: " + ((member) p).getID());
            }
        }
        System.out.println("--------------------------");

        System.out.print("Enter ID of member to calculate BMR for: ");
        String id = sc.nextLine();
        boolean found = false;

        for (person p : members) {
            if (p instanceof member) {
                member m = (member) p;
                if (m.getID().equals(id)) {
                    found = true;
                    double bmr;
                    if (m.getGender() == 'M') bmr = (m.getWeight() * 10) + (m.getHeight() * 6.25) - (m.getAge() * 5) + 5;
                    else bmr = (m.getWeight() * 10) + (m.getHeight() * 6.25) - (m.getAge() * 5) - 161;

                    System.out.println("\nSelect Activity Level (Enter 1-5):");
                    System.out.println("1. Sedentary");
                    System.out.println("2. Lightly active");
                    System.out.println("3. Moderately active");
                    System.out.println("4. Very active");
                    System.out.println("5. Extra active");
                    System.out.print("Choice: ");
                    int actChoice = 1;
                    try { actChoice = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}
                    activityLevel activity = activityLevel.SEDENTARY;
                    if (actChoice == 2) activity = activityLevel.LIGHTLY_ACTIVE;
                    else if (actChoice == 3) activity = activityLevel.MODERATELY_ACTIVE;
                    else if (actChoice == 4) activity = activityLevel.VERY_ACTIVE;
                    else if (actChoice == 5) activity = activityLevel.EXTRA_ACTIVE;

                    System.out.println("\nSelect Goal (Enter 1-3):");
                    System.out.println("1. Maintain Weight");
                    System.out.println("2. Lose Weight");
                    System.out.println("3. Gain Weight");
                    System.out.print("Choice: ");
                    int goalChoice = 1;
                    try { goalChoice = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}
                    Goal goal = Goal.MAINTAIN;
                    if (goalChoice == 2) goal = Goal.LOSE;
                    else if (goalChoice == 3) goal = Goal.GAIN;

                    double totalCalories = (bmr * activity.getFactor()) + goal.getCalorie();
                    System.out.println("\n************************************************");
                    System.out.printf("  BMR: %.2f\n", bmr);
                    System.out.printf("  Daily Calories Needed: %.2f\n", totalCalories);
                    System.out.println("************************************************\n");
                }
            }
        }
        if (!found) System.out.println("Member not found.");
    }

    public void calculateMacros(ArrayList<person> members, Scanner sc) {
        if (members.isEmpty()) {
            System.out.println("No profiles found. Please create a profile first.");
            return;
        }
        System.out.println("\n--- Available Profiles ---");
        for (person p : members) {
            if (p instanceof member) {
                System.out.println("Name: " + p.getName() + " | ID: " + ((member) p).getID());
            }
        }
        System.out.println("--------------------------");

        System.out.print("Enter ID of member to calculate Macros for: ");
        String id = sc.nextLine();
        boolean found = false;

        for (person p : members) {
            if (p instanceof member) {
                member m = (member) p;
                if (m.getID().equals(id)) {
                    found = true;
                    double bmr;
                    if (m.getGender() == 'M') bmr = (m.getWeight() * 10) + (m.getHeight() * 6.25) - (m.getAge() * 5) + 5;
                    else bmr = (m.getWeight() * 10) + (m.getHeight() * 6.25) - (m.getAge() * 5) - 161;

                    System.out.println("\nSelect Activity Level: 1.Sedentary 2.Light 3.Moderate 4.Very 5.Extra");
                    int actChoice = 1;
                    try { actChoice = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}
                    activityLevel activity = activityLevel.SEDENTARY;
                    if (actChoice == 2) activity = activityLevel.LIGHTLY_ACTIVE;
                    else if (actChoice == 3) activity = activityLevel.MODERATELY_ACTIVE;
                    else if (actChoice == 4) activity = activityLevel.VERY_ACTIVE;
                    else if (actChoice == 5) activity = activityLevel.EXTRA_ACTIVE;

                    System.out.println("Select Goal: 1.Maintain 2.Lose 3.Gain");
                    int goalChoice = 1;
                    try { goalChoice = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}
                    Goal goal = Goal.MAINTAIN;
                    if (goalChoice == 2) goal = Goal.LOSE;
                    else if (goalChoice == 3) goal = Goal.GAIN;

                    double totalCalories = (bmr * activity.getFactor()) + goal.getCalorie();
                    double proteinGrams = (totalCalories * 0.30) / 4;
                    double carbGrams = (totalCalories * 0.40) / 4;
                    double fatGrams = (totalCalories * 0.30) / 9;

                    System.out.println("\n************************************************");
                    System.out.printf("  Total Calories: %.0f\n", totalCalories);
                    System.out.println("  --------------------------------------------");
                    System.out.printf("  Protein: %.0f g  (30%%)\n", proteinGrams);
                    System.out.printf("  Carbs:   %.0f g  (40%%)\n", carbGrams);
                    System.out.printf("  Fats:    %.0f g  (30%%)\n", fatGrams);
                    System.out.println("************************************************\n");
                }
            }
        }
        if (!found) System.out.println("Member not found.");
    }

    public void calculateBMI(ArrayList<person> members, Scanner sc) {
        System.out.print("Enter Member ID to calculate BMI: ");
        String id = sc.nextLine();
        boolean found = false;
        for (person p : members) {
            if (p instanceof member) {
                member m = (member) p;
                if (m.getID().equals(id)) {
                    found = true;
                    double heightInMeters = m.getHeight() / 100.0;
                    double bmi = m.getWeight() / (heightInMeters * heightInMeters);
                    System.out.println("\n*********************************");
                    System.out.printf("  Your BMI is: %.2f\n", bmi);
                    String category;
                    if (bmi < 18.5) category = "Underweight";
                    else if (bmi < 24.9) category = "Normal Weight";
                    else if (bmi < 29.9) category = "Overweight";
                    else category = "Obese";
                    System.out.println("  Category: " + category);
                    System.out.println("*********************************\n");
                }
            }
        }
        if (!found) System.out.println("Member not found.");
    }
}
