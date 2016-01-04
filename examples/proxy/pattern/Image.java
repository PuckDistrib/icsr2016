package proxy.pattern;

class Image{ // RealImage in huston example
        int m_id;
    public Image(int i){
	m_id = i;
	System.out.println(" $$ ctor : " + m_id);
	
    }

    protected void finalize(){
	System.out.println("dtor: " + m_id);
    }
    
    void draw(){
	System.out.println("drawing image" + m_id);
    }
    
    
}

class ImageProxy{ // Image in huston example
    Image m_the_real_thing;
    int m_id;
    static int s_next = 1;
    
    public ImageProxy(){
	m_id = s_next++;
	m_the_real_thing=null;
    }
    void draw(){
	if(m_the_real_thing == null)
	    m_the_real_thing = new Image(m_id);
	
	m_the_real_thing.draw();
    }
    
}

class Main{
    public static void main(String[] args){
	ImageProxy[] images = new ImageProxy[5];
	for(int i =0; i<5; i++){
	    images[i] = new ImageProxy();
	}

	for(int i=-1; true;){
	    System.out.println("Exit[0], Image[1-5]");
	    i = Integer.parseInt(System.console().readLine());
	    if(i==0)
		break;
	    
	    images[i-1].draw();
	}
    }
}