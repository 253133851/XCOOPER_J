package com.pabula.fw.utility;

import com.pabula.fw.exception.UnAcquiredCommandException;

public interface CommandFactory{

  public Command createCommand(String action) throws UnAcquiredCommandException;

}
