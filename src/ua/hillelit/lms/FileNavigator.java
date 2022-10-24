package ua.hillelit.lms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.hillelit.lms.exceptions.FileExistsException;

public class FileNavigator {

  private final Map<String, List<FileData>> fileMap = new HashMap<>();

  public void add(FileData file) {
    try {
      createFile(file);
      fileMap.putIfAbsent(file.getPath(), new ArrayList<>());
      fileMap.get(file.getPath()).add(file);
    } catch (FileExistsException e) {
      e.printStackTrace();
    }
  }

  private void createFile(FileData file) throws FileExistsException {
    File newFile = new File(file.getPath(), file.getName());
    directoryException(newFile);
    try {
      if (newFile.createNewFile()) {
        System.out.println("new File [" + file.getName() + "] created");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void directoryException(File file) throws FileExistsException {
    if (file.exists()) {
      String massage = "\nFile in this directory [" + file.getPath() + "]" + " is already exist!";
      throw new FileExistsException(massage);
    }
  }

  public List<FileData> find(String path) {
    return fileMap.get(path);
  }

  public List<FileData> findAll() {
    List<FileData> files = new ArrayList<>();

    for (List<FileData> list : fileMap.values()) {
      files.addAll(list);
    }
    return files;
  }

  public List<FileData> filterBySize(Long size) {
    List<FileData> files = new ArrayList<>();

    for (FileData file : findAll()) {
      if (file.getSize() <= size) {
        files.add(file);
      }
    }
    return files;
  }

  public String remove(String path) {
    fileMap.remove(path);
    return path;
  }

  public List<FileData> sortBySize() {
    List<FileData> fileDataList = findAll();
    Collections.sort(fileDataList);

    return fileDataList;
  }

}
