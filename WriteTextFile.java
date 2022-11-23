public class WriteTextFile {
    public static void main(String[] args) {
        ArrayList<Strinf> records = new ArrayList(<>)();
        records.add("This is a sample data to write into our text file");
        records.add("This data will be added ti line 2 of our text");
        records.add("This is the data fir line 3 of the text");
        records.add("Sample data for line 4");
        records.add("Sample data for line 5");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\sampledata.txt");

        try(
                outputStream out = new BufferOutputStream(File.newOutputStream(file, CREATE));

                )catch(IOException ex){
            ex.printSackTrace();
        }
    }
}
