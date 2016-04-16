package com.pabula.fw.utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

final public class ObjectCreator {
  public ObjectCreator() {
  }

  public static Object createObject(String className) throws Exception{
    return createObject(Class.forName(className));
  }

  public static Object createObject(Class classObject) throws Exception{
    //Object object = null;
    return classObject.newInstance();
  }

  public static Object createObject(String className, Object[] params) throws
      Exception {
    return createObject(Class.forName(className), params);
  }

  public static Object createObject(Class classObject, Object[] params) throws
      Exception {
    Constructor[] constructors = classObject.getConstructors();
    Object object = null;
    for (int counter = 0; counter < constructors.length; counter++) {
      try {
        object = constructors[counter].newInstance(params);
      }
      catch (Exception e) {
        if (e instanceof InvocationTargetException)
          ( (InvocationTargetException) e).getTargetException().printStackTrace();
        //do nothing, try the next constructor
      }
    }
    if (object == null)
      throw new InstantiationException();
    return object;
  }


}
