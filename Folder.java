import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Folder extends StorageItem {
    ArrayList<StorageItem> itemsInFolder;

    Folder(String name) {
        super(name);
        this.itemsInFolder = new ArrayList<>(); //List of future storage items
        this.isFile = false;
    }

    @Override
    public int getSize() {
        this.size = 0;
        for (StorageItem i : this.itemsInFolder)
            if (i.isFile)
                this.size += i.getSize();
            else
            {
                Folder currentFolder = (Folder) i;
                this.size += i.getSize();
            }
//        System.out.println("Size of " + this.getName() + " is: " + this.size);
        return this.size;
    }

    @Override
    public String getFileExtension() {
        return null;
    }

    //adds a storage item to the folder according to provided limitations
    public boolean addItem(StorageItem item) {
        for (StorageItem i : this.itemsInFolder) {
            boolean sameName = i.getName().equals(item.getName());
            boolean sameExtension = true;
            //check for same extension if the item is a file
            if (i.isFile && item.isFile)
                sameExtension = i.getFileExtension().equals(item.getFileExtension());

            //check for duplicates of a file according to requirements
            if (sameName && sameExtension) return false;
        }
        this.itemsInFolder.add(item);
        return true;
    }

//    // NEEDS TO BE FINISHED!
//    @Override
//    public void printTree(SortingField field) {
//        ArrayList<StorageItem> sortedItems = new ArrayList<>();
//        switch (field) {
//            case NAME:
//                this.itemsInFolder.sort(new sortByName()); //override compare by type of class
//
//            case SIZE:
//                this.itemsInFolder.sort(new sortBySize()); //override compare by type of class
//
//            case CREATION:
//                this.itemsInFolder.sort(new sortByCreation()); // override compare by type of class
//        }
//        // works for single folder, fix the printings to be recursive
//        System.out.print(this.getName());
//        for (StorageItem item : this.itemsInFolder)
//        {
//            if (item.isFile) System.out.println("|    " + item.getName()); // final print of the file
//            else
//            {
//                System.out.print("|    ");// + item.getName()); // "Recursively" look for other files in item folder
//                item.printTree(field);
//            }
//        }
//    }

    public File findFile(String path) {
        if (path.charAt(path.length() - 1) != '/') path = path + "/"; //add slash to end, avoid duplicates
        File wanted_file = null;
        String folderFromPath = null, restOfPath = null;
        // look for possible folders in the path
        for (int i = 0; i < path.length(); i++)
            if (path.charAt(i) == '/') //look for slash in path
            {
                folderFromPath = path.substring(0, i); //save possible folder to look
                restOfPath = path.substring(i + 1); //save rest of path
                Folder tmp_folder = null; // variable to save the possible folder if needed
                for (StorageItem item : this.itemsInFolder)
                    // if the folder from left side of path string matches to one of the
                    // storage items in the current folder
                    if (!item.isFile && folderFromPath.equals(item.getName())) {
                        tmp_folder = (Folder) item;
                        wanted_file = tmp_folder.findFile(restOfPath); // "Recursively"
                    }
                //break; - might be needed in debugging
            }

        for (StorageItem item : this.itemsInFolder) // look for the final file
            if (item.isFile && folderFromPath.equals(item.getName()))
                wanted_file = (File) item;

        return wanted_file; //returning the needed file, if not found - wanted_file = null
    }
}
