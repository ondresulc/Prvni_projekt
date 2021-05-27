package EU_VAT;

import java.io.*;
import java.util.*;

public class MethodsBasis {
    public static ArrayList<euState> listOfStatesVATs = new ArrayList<>();

    public ArrayList<euState> getListOfStatesVATs() {
        return listOfStatesVATs;
    }

    public static void setListOfStatesVATs(ArrayList<euState> listOfStatesVATs) {
        MethodsBasis.listOfStatesVATs = listOfStatesVATs;
    }

    public void importFromCSVFileGiven(String filePath) {

        try (
                Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath))
                )
        ) {

            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                listOfStatesVATs.add(euState.parseData(nextLine));
            }

        } catch (FileNotFoundException e) {
            System.err.println("Read file ERROR!");;
        }
    }

    public void exportToFile(String outputFile, ArrayList<euState> listOfStatesVATsForTask05) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)))) {
            writer.println("Enum of EU states with VAT over "+ Main.LIMIT_VAT +" % (descending):");
            writer.println("-------------------------------------------------");
            for (euState state:listOfStatesVATsForTask05) {
                writer.println(state);
            }
            writer.println("=====================================================================================================================================");
        } catch (IOException e) {
            System.err.println("Chyba pri zapisu do souboru " + outputFile + e.getLocalizedMessage());
        }
    }

    public void exportToFile2(String outputFile, ArrayList<euState> listOfStatesVATsForTask05) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)))) {
            writer.print("Enum of EU states with VAT under "+ Main.LIMIT_VAT +" % or also having a special VAT (alphabetically): ");
            for (euState state:listOfStatesVATsForTask05) {
                writer.print(state.getStateAbbreviation() + ", ");
            }

        } catch (IOException e) {
            System.err.println("Chyba pri zapisu do souboru " + outputFile + e.getLocalizedMessage());
        }
    }

    public ArrayList<euState> getListOfStatesVATsToSortedList () {
        ArrayList<euState> sortedList = new ArrayList<>(listOfStatesVATs);
        Collections.sort(sortedList, Collections.reverseOrder());
        return sortedList;
    }

    public int size() {
        return listOfStatesVATs.size();
    }

    public euState getState(int index) {
        return listOfStatesVATs.get(index);
    }
}

