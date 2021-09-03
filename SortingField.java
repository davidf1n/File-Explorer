import java.util.Comparator;

public enum SortingField {
    NAME,
    SIZE,
    DATE
}

class sortByName implements Comparator<StorageItem> {
    // Used for sorting in ascending lexicographical order of the items - f.e. 1)ABC -> 2)BCA
    public int compare(StorageItem a, StorageItem b)
    {
        return a.getName().compareTo(b.getName());
    }
}

class sortBySize implements Comparator<StorageItem> {
    // Used for sorting in ascending order of item's size
    public int compare(StorageItem a, StorageItem b)
    {
        if (a.getSize() == b.getSize())
            return a.getName().compareTo(b.getName());
        return a.getSize() - b.getSize();
    }
}

class sortByCreation implements Comparator<StorageItem> {
    // Used for sorting in ascending order of creation date
    public int compare(StorageItem a, StorageItem b)
    {
        if (a.getDateCreated().equals(b.getDateCreated()))
            return a.getName().compareTo(b.getName());
        return a.getDateCreated().compareTo(b.getDateCreated());
    }
}