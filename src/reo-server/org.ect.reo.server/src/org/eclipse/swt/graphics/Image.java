package org.eclipse.swt.graphics;

import java.io.*;


public final class Image extends Resource implements Drawable {

	public int type;
	public int handle;

	ImageData data;

	int width = -1;
	int height = -1;

	Image() {
	}
	
	public Image(Device device, int width, int height) {
	}

	public Image(Device device, Rectangle bounds) {
	}

	public Image(Device device, Image srcImage, int flag) {
	}

	public Image(Device device, ImageData data) {
	}

	public Image(Device device, ImageData source, ImageData mask) {
	}

	public Image(Device device, InputStream stream) {
	}

	public Image(Device device, String filename) {
	}


	public void dispose() {
		handle = 0;
		device = null;
	}

	public boolean isDisposed() {
		return handle == 0;
	}

	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (!(object instanceof Image))
			return false;
		Image image = (Image) object;
		return device == image.device && handle == image.handle;
	}

	public Color getBackground() {
		return null;
	}

	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}

	public ImageData getImageData() {
		return null;
	}

	public int hashCode() {
		return handle;
	}


	public void setBackground(Color color) {
	}

	public String toString() {
		if (isDisposed())
			return "Image {*DISPOSED*}";
		return "Image {" + handle + "}";
	}

}
