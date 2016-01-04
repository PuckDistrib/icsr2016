package fileSystem.fileKinds;

import fileSystem.FileAbstraction;

public class File implements FileAbstraction{
   public File( String name ) {
      this.name = name;
   }
   public void display(String path){
	   System.out.println(path+name);
   }
   public void ls() { display(""); }
   public void add(FileAbstraction f) {
	   assert false : "A file cannot store others files!";
   }
   private String name;
}