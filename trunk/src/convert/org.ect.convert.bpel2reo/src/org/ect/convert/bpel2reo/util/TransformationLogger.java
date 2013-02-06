/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.convert.bpel2reo.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransformationLogger {
	Logger logger = null;
  public TransformationLogger(String logpath) {

    logger = Logger.getLogger("BPEL2ReoLog");
    FileHandler fh;

    try {

      // This block configure the logger with handler and formatter
      fh = new FileHandler(logpath, true);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);

      // the following statement is used to log any messages   
      log(Level.INFO, "Logger Initiated");

    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  
  }
  
  private String getDateTime() {
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
      Date date = new Date();
      return dateFormat.format(date);
  }
  
  public void log(Level level, String msg)
  {
	  logger.log(level, getDateTime().concat(msg));
	//  if (true)
		//  System.out.println(level.toString() + " " +msg);
  }

}