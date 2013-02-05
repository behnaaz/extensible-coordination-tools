package org.eclipse.swt.graphics;

import org.eclipse.swt.*;


public abstract class Device implements Drawable {
	
	boolean disposed;
	
	public Device() {
		this(null);
	}
	
	public Device(DeviceData data) {
	}
	
	public void dispose () {
	}
	
	public DeviceData getDeviceData () {
		return null;
	}

	public Rectangle getBounds () {
		return new Rectangle (0, 0, 0,0);
	}
	
	public Rectangle getClientArea () {
		return getBounds ();
	}

	public Color getSystemColor (int id) {
		int pixel = 0x00000000;
		switch (id) {
			case SWT.COLOR_WHITE:				pixel = 0x00FFFFFF;  break;
			case SWT.COLOR_BLACK:				pixel = 0x00000000;  break;
			case SWT.COLOR_RED:					pixel = 0x000000FF;  break;
			case SWT.COLOR_DARK_RED:			pixel = 0x00000080;  break;
			case SWT.COLOR_GREEN:				pixel = 0x0000FF00;  break;
			case SWT.COLOR_DARK_GREEN:			pixel = 0x00008000;  break;
			case SWT.COLOR_YELLOW:				pixel = 0x0000FFFF;  break;
			case SWT.COLOR_DARK_YELLOW:			pixel = 0x00008080;  break;
			case SWT.COLOR_BLUE:				pixel = 0x00FF0000;  break;
			case SWT.COLOR_DARK_BLUE:			pixel = 0x00800000;  break;
			case SWT.COLOR_MAGENTA:				pixel = 0x00FF00FF;  break;
			case SWT.COLOR_DARK_MAGENTA:		pixel = 0x00800080;  break;
			case SWT.COLOR_CYAN:				pixel = 0x00FFFF00;  break;
			case SWT.COLOR_DARK_CYAN:			pixel = 0x00808000;  break;
			case SWT.COLOR_GRAY:				pixel = 0x00C0C0C0;  break;
			case SWT.COLOR_DARK_GRAY:			pixel = 0x00808080;  break;
		}
		return new Color(this, pixel & 0xFF, (pixel & 0xFF00) >> 8, (pixel & 0xFF0000) >> 16);
	}

	public boolean isDisposed () {
		return disposed;
	}

}
