package org.eclipse.swt.graphics;

import org.eclipse.swt.*;


public final class Color extends Resource {
	
	public int handle;

	Color() {	
	}

	public Color (Device device, int red, int green, int blue) {
		init(device, red, green, blue);
	}

	public Color (Device device, RGB rgb) {
		if (rgb == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
		init(device, rgb.red, rgb.green, rgb.blue);
	}

	public void dispose() {
		handle = -1;
		device = null;
	}

	public boolean equals (Object object) {
		if (object == this) return true;
		if (!(object instanceof Color)) return false;
		Color color = (Color) object;
		return device == color.device && (handle & 0xFFFFFF) == (color.handle & 0xFFFFFF);
	}
	
	public int getBlue () {
		return (handle & 0xFF0000) >> 16;
	}

	public int getGreen () {
		return (handle & 0xFF00) >> 8 ;
	}

	public int getRed () {
		return handle & 0xFF;
	}

	public RGB getRGB () {
		return new RGB(handle & 0xFF, (handle & 0xFF00) >> 8, (handle & 0xFF0000) >> 16);
	}

	public int hashCode () {
		return handle;
	}

	void init(Device device, int red, int green, int blue) {
		if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
			SWT.error(SWT.ERROR_INVALID_ARGUMENT);
		}
		this.device = device;
		handle = (red & 0xFF) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 16);
	}
	
	public boolean isDisposed() {
		return handle == -1;
	}

	public String toString () {
		if (isDisposed()) return "Color {*DISPOSED*}"; //$NON-NLS-1$
		return "Color {" + getRed() + ", " + getGreen() + ", " + getBlue() + "}"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
}
