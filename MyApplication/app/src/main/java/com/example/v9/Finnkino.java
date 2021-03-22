package com.example.v9;

import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Finnkino {
    //Create singleton
    private static Finnkino finnkino = new Finnkino();

    private Finnkino() {
    };

    public static Finnkino getInstance() {
        return finnkino;
    }

    private ArrayList<FinnkinoTheater> theaterslist = new ArrayList<>();
    private ArrayList<FinnkinoMovie> movieslist = new ArrayList<>();

    private String theaters[] = {""};


    public ArrayList<FinnkinoTheater> getTheatersList() {
        return theaterslist;
    }

    public ArrayList<FinnkinoMovie> getMovieslist() {
        //TODO
        return null;
    }
    public void readScheduleXML(Spinner spinner, String date) {
        try {
            FinnkinoTheater theater = (FinnkinoTheater) spinner.getSelectedItem();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/?area=1016&dt=22.03.2021";
            urlString = urlString + ("?area=" + theater.getTheaterID() + "&dt=" + date );
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                //System.out.println("Element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    System.out.print("id: ");
                    System.out.println(id);
                    String title = element.getElementsByTagName("Title").item(0).getTextContent();
                    System.out.print("Title: ");
                    System.out.println(title);
                    String showStart = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    System.out.print("Starts: ");
                    System.out.println(showStart);
                    movieslist.add(new FinnkinoMovie(id,title,showStart));
                }

            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("###############¤###################");
        }
    }


    public void readAreasXML() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                //System.out.println("Element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    System.out.print("Area ID: ");
                    System.out.println(id);
                    String area = element.getElementsByTagName("Name").item(0).getTextContent();
                    System.out.print("Area Name: ");
                    System.out.println(area);
                    theaterslist.add(new FinnkinoTheater(id,area));
                }


            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("###############¤###################");
        }
    }
}
