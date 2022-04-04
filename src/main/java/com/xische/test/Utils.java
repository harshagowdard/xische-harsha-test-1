package com.xische.test;

import org.omg.DynamicAny.NameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;

@SuppressWarnings({"deprecation", "unused"})
public class Utils {
    protected static Properties props = PropertyLoader.loadProperties();
    protected static ArrayList<NameValuePair> arguments = new ArrayList<NameValuePair>();

    private static String microservice = null;
    private static String protocol = props.getProperty("protocol", "http");
    private static String host = props.getProperty("host", "localhost");
    private static String mode = props.getProperty("mode", "DEV");
    private static String oAuthCliendId = props.getProperty("oAuthCliendId", "SkyFall-app");
    private static String oAuthClientPassword = props.getProperty("oAuthClientPassword", "2rmkDpR9da");

    //private static Base64Encoder base64Encoder = new Base64Encoder();

    public static URI getURI(String protocol, String host, int port, String path, String queryString) {
        try {
            if("http".equalsIgnoreCase(protocol)) return new URI(protocol, null, host, getPort(path), path, queryString, null);
            else if("https".equalsIgnoreCase(protocol)) return new URI(protocol, null, host, -1, path, queryString, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URI getURI(String protocol, String host, String path, String queryString) {
        return getURI(protocol, host, getPort(path), path, queryString);
    }

    public static URI getURI(String protocol, String host, int port, String path) {
        return getURI(protocol, host, port, path, null);
    }

    public static URI getURI_UI(String protocol, String host, int port) {
        String path = "";
        if("https".equals(protocol)) {
            path = "/#/login";
        }
        else if("http".equals(protocol)) {
            path = "/#/login";
        }
        if("localhost".equals(host) || "http".equals(protocol)) {
            return getURI(protocol, host, port, path);
        }
        else return getURI(protocol, host, -1, path);
    }

    public static URI getURI(String protocol, String host, String path) {
        return getURI(protocol, host, getPort(path), path);
    }


    public static int getPort(String path){
        String defaultMSPort = props.getProperty("port","-1");
        String port = defaultMSPort;
        if(!path.isEmpty() && path.split("/").length > 0){
            microservice  = path.split("/")[1];
            if("#".equalsIgnoreCase(microservice)) {
                // Special case, we were passed a UI path lookup, so we need to look up the
                // UI port, not port_<microservice>
                port = props.getProperty("uiport", "-1");
            }else {
                port = props.getProperty("port_" + microservice, defaultMSPort);
            }
        }
        return Integer.parseInt(port);
    }
}
