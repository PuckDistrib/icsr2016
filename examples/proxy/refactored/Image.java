package proxy.refactored;

import proxy.refactored.image.Image;



class Main{

	public static void drawingApp(ImageProxy [] images){
		for(int i=-1; true;){
			System.out.println("Exit[0], Image[1-5]");
			i = Integer.parseInt(System.console().readLine());
			if(i==0)
				break;

			images[i-1].draw();
		}
	}

	public static void main(String[] args){
		ImageProxy[] images = new ImageProxy[5];
		for(int i =0; i<5; i++){
			images[i] = new Image();
		}

		drawingApp(images);
	}
}