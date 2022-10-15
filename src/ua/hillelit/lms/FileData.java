package ua.hillelit.lms;

import java.util.Objects;

public class FileData implements Comparable<FileData> {

  private final String path;
  private final String name;
  private final Long size;

  public FileData(String path, String name, Long size) {
    this.path = path;
    this.name = name;
    this.size = size;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getPath() {
    return path;
  }

  public Long getSize() {
    return size;
  }

  @Override
  public int compareTo(FileData o) {
    return (int) (size - o.size);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FileData fileData = (FileData) o;

    if (!Objects.equals(path, fileData.path)) {
      return false;
    }
    if (!Objects.equals(name, fileData.name)) {
      return false;
    }
    return Objects.equals(size, fileData.size);
  }

  @Override
  public int hashCode() {
    int result = path != null ? path.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (size != null ? size.hashCode() : 0);
    return result;
  }

}
