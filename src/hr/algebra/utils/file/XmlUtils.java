package hr.algebra.utils.file;

import com.sun.corba.se.impl.orbutil.ORBUtility;
import hr.algebra.sharedModel.ChatServerSettings;
import hr.algebra.utils.AlertUtil;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {

    private static final String CHAT_SETTINGS_FILEPATH = FileUtils.getSettingsDir() + File.separator + "ServerChatSettings.xml";

    private XmlUtils() {
    }

    public static ChatServerSettings loadRmiSetting() {
        List<String> clients = new ArrayList<>();
        clients.add("client");
        String serverName = "server";
        int remotePort = 1099;
        int randomPort = 0;

        try {
            File file = new File(CHAT_SETTINGS_FILEPATH);
            Document document = createDocument(file);

            clients = getClients(document.getElementsByTagName("clients"));
            serverName = document.getElementsByTagName("serverName").item(0).getTextContent();
            remotePort = Integer.parseInt(document.getElementsByTagName("remotePort").item(0).getTextContent());
            randomPort = Integer.parseInt(document.getElementsByTagName("randomPortHint").item(0).getTextContent());

        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Error while loading chat settings", e.getMessage());
        }

        return new ChatServerSettings(
                clients,
                serverName,
                remotePort,
                randomPort
        );
    }

    private static List<String> getClients(NodeList clients) {
        List<String> clientsList = new ArrayList<>();
        NodeList clientElements = ((Element) clients.item(0)).getElementsByTagName("client");
        for (int i = 0; i < clientElements.getLength(); i++) {
            clientsList.add(clientElements.item(i).getTextContent());
        }
        return clientsList;
    }

    private static Document createDocument(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }
}
