public class File extends StorageItem {
    String fileContent = "";
    String fileExtension;

    File(String name, String fileEnding) {
        super(name);
        this.fileExtension = fileEnding;
        this.isFile = true;
    }
    @Override
    public String getName() { return (this.name + "." + this.fileExtension);} //returns file name including extension
    @Override
    public int getSize() { return this.size;}
    @Override
    public String getFileExtension() {return this.fileExtension;} // returns extension

    public void addContent(String contentToAdd) {
        this.fileContent = this.fileContent + contentToAdd;
        this.size = fileContent.length(); // file size is the content string length
    }
    public void printContent() {
        String fullFileName = this.name + "." + this.fileExtension;
        System.out.println(fullFileName + " Size " + this.size + "MB " +
                "Created:" + this.getDateCreated());
        System.out.println(this.fileContent);
    }
}
