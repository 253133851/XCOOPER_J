package com.pabula.fw;

import com.xcooper.ENV;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.UnAcquiredCommandException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.CommandFactory;

import java.util.HashMap;

public class WebCommandFactoryImpl implements CommandFactory {

	private static HashMap commands = new HashMap();

	//  private static String actionMapping =
	//      "/WEB-INF/config/ActionMapping.properties";

	public WebCommandFactoryImpl() {
//		if (commands.isEmpty()) {
//			try {
//				Properties properties = new Properties();
//				//        properties.load(getClass().getResourceAsStream(
//				//            ACTION_MAPPING_PROPERTIES));
//				String fileName = FileConfig.getInstance().getActionMapFileName();
//				System.out.println("fileName=" + fileName);
//				System.err.println("actionName = " + FileConfig.getInstance().getActionMapFileName());
//				System.err.println("logName = " + FileConfig.getInstance().getLog4jFileName());
//				java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
//				properties.load(fis);
//				//                  properties.load(getClass().getResourceAsStream(
//				//            fileName));
//				properties.list(System.out);
//				for (Enumeration e = properties.keys(); e.hasMoreElements();) {
//					String action = (String) e.nextElement();
//					commands.put(action, ObjectCreator.createObject(properties.getProperty(action)));
//				}
//			} catch (Exception e) {
//				System.out.println("Error: " + e.toString());
//				e.printStackTrace();
//			}
//		}
	}

	/**
	 * ��̬ʵ��command
	 */
	public Command createCommand(String action) throws UnAcquiredCommandException {
		Command cmd = null;
		
			//cmd = (Command) commands.get(action);
			
			String busiName = (String)(StrUtil.split(action,'!').get(0));
			String classMainName = (String)(StrUtil.split(action,'!').get(1));
			
			String className = ENV.PACKAGE_NAME + "." + busiName.toLowerCase() + ".web.command." + classMainName + "Command";
			System.err.println("��̬command·���� " + className);

				//��̬����vo��
		try {
			cmd = (Command) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		if (cmd == null) {
				throw new UnAcquiredCommandException("���������޷����?���������action�Ƿ���ȷ " + className);
			}


		return cmd;
	}
}
