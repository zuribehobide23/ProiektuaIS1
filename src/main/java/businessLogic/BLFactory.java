package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import gui.MainGUI;

public class BLFactory {

	public static BLFacade getBusinessLogicFactory(boolean isLocal) {
		if (isLocal) {
			DataAccess da = new DataAccess();
			return new BLFacadeImplementation(da);
		} else {
			try {
				ConfigXML c = ConfigXML.getInstance();
				String serviceName = "http://" + c.getBusinessLogicNode() + ":" + c.getBusinessLogicPort() + "/ws/"
						+ c.getBusinessLogicName() + "?wsdl";
				URL url = new URL(serviceName);
				// 1st argument refers to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

				Service service = Service.create(url, qname);

				return service.getPort(BLFacade.class);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	}

}
