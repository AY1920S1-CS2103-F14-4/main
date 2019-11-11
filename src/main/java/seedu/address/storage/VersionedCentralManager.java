package seedu.address.storage;

import java.util.ArrayList;

/**
 * A (@code CentralManager) with an undo/redo history
 */
public class VersionedCentralManager extends CentralManager {

    private static ArrayList<CentralManager> centralManagerStateList;
    private static int currentStatePointer;

    public VersionedCentralManager(CentralManager centralManager) {
        super();
        centralManagerStateList = new ArrayList<>();
        currentStatePointer = 0;
        System.out.println(currentStatePointer);
        centralManagerStateList.add(currentStatePointer, centralManager);
    }

    /**
     * Returns the stored history of (@code CentralManager)s.
     *
     * @return stored history of (@code CentralManager)s.
     */
    public static ArrayList<CentralManager> getCentralManagerStateList() {
        return centralManagerStateList;
    }

    /**
     * Returns the index of the current state pointer.
     *
     * @return index of current state pointer.
     */
    public static int getCurrentStatePointer() {
        return currentStatePointer;
    }

    /**
     * Saves the current CentralManager state in (@code centralManagerStateList).
     */
    public static void commit(CentralManager centralManager) {
        currentStatePointer++;
        System.out.println(currentStatePointer);
        centralManagerStateList.add(currentStatePointer, centralManager);
    }

    /**
     * Returns the undone version of the (@code CentralManager).
     *
     * @return undone version of (@code CentralManager).
     */
    public static CentralManager undo() {
        currentStatePointer--;
        System.out.println(currentStatePointer);
        CentralManager previousVersion = centralManagerStateList.get(currentStatePointer);
        return previousVersion;
    }

    /**
     * Returns the redone version of the (@code CentralManager).
     *
     * @return redone version of (@code CentralManager).
     */
    public static CentralManager redo() {
        currentStatePointer++;
        System.out.println(currentStatePointer);
        CentralManager nextVersion = centralManagerStateList.get(currentStatePointer);
        return nextVersion;
    }

    /**
     * Shortens the centralManagerStateList
     */
    public static void truncateList() {
        int cutoff = currentStatePointer;
        for (int i = centralManagerStateList.size() - 1; i > cutoff; i--) {
            centralManagerStateList.remove(i);
        }
    }

}
