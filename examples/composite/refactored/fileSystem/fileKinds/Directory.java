package fileSystem.fileKinds;

import java.util.ArrayList;
import java.util.List;

import fileSystem.FileAbstraction;

public class Directory implements FileAbstraction {
   public Directory( String name ) {
      this.name = name;
   }
   public void add( FileAbstraction f ) {
      files.add( f );
   }
   public void display(String path) {
      System.out.println(path + name);
      String npath = path + name +"/";
      for(FileAbstraction f: files)
         f.display(npath);
   }
   public void ls(){ display(""); }
   private String    name;
   private List<FileAbstraction> files = new ArrayList<FileAbstraction>();
}