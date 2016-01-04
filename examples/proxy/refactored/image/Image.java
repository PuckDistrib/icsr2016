package proxy.refactored.image;

import proxy.refactored.ImageProxy;

public class Image implements ImageProxy{
	static int s_next = 1;
	int m_id;
	public Image(){
		m_id = s_next++;
		System.out.println(" $$ ctor : " + m_id);

	}

	protected void finalize(){
		System.out.println("dtor: " + m_id);
	}

	public Image create(){
		return new Image();
	}

	/* (non-Javadoc)
	 * @see proxy.refactored.ImageProxy#draw()
	 */
	public void draw(){
		System.out.println("drawing image" + m_id);
	}

}