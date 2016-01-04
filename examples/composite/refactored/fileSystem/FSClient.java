package fileSystem;

import fileSystem.fileKinds.Directory;
import fileSystem.fileKinds.File;

public class FSClient {
   public static void main( java.lang.String[] args ) {
	      FileAbstraction d = new Directory("dir1");
	      FileAbstraction f = new File("a");
	      d.add(f);
	      f.ls();
	      d.ls();
   }
}
