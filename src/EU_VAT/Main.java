package EU_VAT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int LIMIT_VAT = 20;
    static String INPUT_FILE = "data/vat-eu.csv";
    static String OUTPUT_FILE = "";

    public static void main(String[] args) {

        MethodsBasis euStates = new MethodsBasis();

        euStates.importFromCSVFileGiven(INPUT_FILE);

        doTask06();
        printTask01(euStates);
        printTask02(euStates);
        printTask03(euStates);
        printTask04(euStates);

    }

    private static void doTask06() {
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Please enter a positive number or just press ENTER!");
            String input = bis.readLine();
            if (input.equals("")) {
                LIMIT_VAT = 20;
                OUTPUT_FILE = "data/vat-over-"+ LIMIT_VAT +".txt";
            } else LIMIT_VAT = Integer.parseInt(input);
                OUTPUT_FILE = "data/vat-over-"+ LIMIT_VAT +".txt";
        } catch (IOException e) {
            System.err.println("Error, please enter a positive number or press ENTER!");;
            }
    }

    private static void printTask01(MethodsBasis euStates) {
        System.out.println("\n"+ "Vypiš seznam všech států a u každého uveď základní sazbu daně z přidané hodnoty ve formátu:");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < euStates.size(); i++) {
            euState state = euStates.getState(i);
            System.out.println(state.getStateDescription());
        }
    }

    private static void printTask02(MethodsBasis euStates) {
        System.out.println("\n"+ "Vypište ve stejném formátu pouze státy, které mají základní sazbu daně z přidané hodnoty vyšší než "+ LIMIT_VAT +" % a přitom nepoužívají speciální sazbu daně:");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");


        for (int i = 0; i < euStates.size(); i++) {
            euState state = euStates.getState(i);
            if ((state.getVatTaxRate() > LIMIT_VAT) && (!state.hasSpecialTaxRate())) {
                System.out.println(state.getStateDescription());
            }
        }
    }

    private static void printTask03(MethodsBasis euStates) {

        ArrayList<euState> listToPrintTask05 = new ArrayList<>();

        System.out.println("\n"+ "Výpis seřaď podle výše základní sazby DPH/VAT sestupně (nejprve státy s nejvyšší sazbou):");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (euState state : euStates.getListOfStatesVATsToSortedList()) {
            if ((state.getVatTaxRate() > LIMIT_VAT) && (!state.hasSpecialTaxRate())) {
                System.out.println(state.getStateDescription());
                listToPrintTask05.add(state);
            }
        }

        euStates.exportToFile(OUTPUT_FILE, listToPrintTask05);
    }

    private static void printTask04(MethodsBasis euStates) {

        ArrayList<euState> listOfStatesNotMeetingConditionsFromTheTask02 = new ArrayList<>();
        System.out.println("=================================================================================================================================================");
        System.out.print("Sazba VAT " + LIMIT_VAT + " % nebo nižší nebo používají speciální sazbu: ");

        for (euState state : euStates.getListOfStatesVATs()) {
            if ((state.getVatTaxRate() <= LIMIT_VAT) || (state.hasSpecialTaxRate())) {
                listOfStatesNotMeetingConditionsFromTheTask02.add(state);
            }
        }

        int i = 0;
        for (euState state:listOfStatesNotMeetingConditionsFromTheTask02) {
            if (i++ == listOfStatesNotMeetingConditionsFromTheTask02.size() - 1) {
                System.out.print(state.getStateAbbreviation());
            } else System.out.print(state.getStateAbbreviation() + ", ");
        }
        euStates.exportToFile2(OUTPUT_FILE, listOfStatesNotMeetingConditionsFromTheTask02);

    }
}
