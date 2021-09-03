import java.sql.Timestamp;
import java.util.Random;

public abstract class StorageItem {
    String name;
    boolean isFile;
    private Long dateCreated;
    int size = 0;

    StorageItem(String name) {
        this.name = name;

        // randomly choosing a date in required range given in assignment
        long min_milliseconds = 1483221600000L;
        long max_milliseconds = 1640987999999L;
        long range_required = (max_milliseconds - min_milliseconds);
        // randomly generating a Long num
        long rand_date = Main.rnd.nextLong();
        // normalizing to absolute value
        long abs_rand_date = Math.abs(rand_date);
        // module will assure range
        long module_rand_date = abs_rand_date % range_required;
        // normalizing to the range in between min max values
        long rand_date_milliseconds = max_milliseconds - module_rand_date;
        this.dateCreated = rand_date_milliseconds;

        /* if you want to see the date as timestamp uncomment
        Timestamp timestamp = new Timestamp(this.dateCreated);
        System.out.println(timestamp);
         */
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getDateCreated() {
        Timestamp timestamp = new Timestamp(this.dateCreated);
        return timestamp;
    }

    public abstract int getSize();

    // returns extension if it the item is a file, null otherwise
    public abstract String getFileExtension();

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printTree(SortingField field) {
        printAid(field,0);
        // not sorting between folders and files under the same folder

    }

    public void printAid(SortingField field, int count) {
        if (this.isFile) System.out.println(this.getName());
        else {
            Folder currentFolder = (Folder) this;
            switch (field) {
                case NAME:
                    currentFolder.itemsInFolder.sort(new sortByName()); //override compare by type of class
                    break;

                case SIZE:
                    currentFolder.itemsInFolder.sort(new sortBySize()); //override compare by type of class
                    break;

                case DATE:
                    currentFolder.itemsInFolder.sort(new sortByCreation()); //override compare by type of class
                    break;
            }
//            for (StorageItem i : currentFolder.itemsInFolder)
//                System.out.println(i.getName());
            for (int i = 0; i < count; i++) {
                System.out.print("|    ");
            }
            System.out.println(currentFolder.getName());
            count += 1;
            for (StorageItem item : currentFolder.itemsInFolder) {
                if (item.isFile) {
                    for (int i = 0; i < count; i++) { // printing too much |   sometimes
                        System.out.print("|    ");
                    }
                    System.out.println(item.getName()); // final print of the file
                } else {
                    item.printAid(field, count); // "Recursively" look for other files in item folder
                }
            }
        }
    }
}

//    public void printAid(SortingField field, int count) {
//
//        // works for single folder, fix the printings to be recursive
//        for (int i = 0; i < count; i++) {
//            System.out.print("|    ");
//        }
//        System.out.println(this.getName());
//        for (StorageItem item : this.itemsInFolder) {
//            if (item.isFile) {
//                for (int i = 0; i < count; i++) { // printing too much |   sometimes
//                    System.out.print("|    ");
//                }
//                System.out.println("|    " + item.getName()); // final print of the file
//            } else {
//                count += 1;
//                item.printAid(field, count); // "Recursively" look for other files in item folder
//            }
//        }
//    }
//}
