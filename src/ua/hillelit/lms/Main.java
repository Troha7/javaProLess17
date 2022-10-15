package ua.hillelit.lms;

public class Main {

  public static void main(String[] args) {

    FileNavigator fileNavigator = new FileNavigator();
    fileNavigator.add(new FileData("/resources", "file1.txt", 300L));
    fileNavigator.add(new FileData("/resources", "file2.txt", 150L));
    fileNavigator.add(new FileData("/resources/new", "newFile1.txt", 100L));
    fileNavigator.add(new FileData("/resources", "file3.txt", 250L));
    fileNavigator.add(new FileData("/resources/new", "newFile2.txt", 120L));

    System.out.println("Find: " + fileNavigator.find("/resources"));
    System.out.println("Find: " + fileNavigator.find("/resources/new"));

    System.out.println("Filter by size: " + fileNavigator.filterBySize(150L));

    System.out.println("Sort by size: " + fileNavigator.sortBySize());

    System.out.println("Remove: " + fileNavigator.remove("/resources/new"));


  }

}
