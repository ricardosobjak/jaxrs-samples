package br.edu.utfpr.jaxrs.httpserver.recursos;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data")
public class DataResource {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getDate() {
    	Calendar calendar = Calendar.getInstance();
    	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
    			"<data>" +
    			"<dia>" + calendar.get(Calendar.DAY_OF_MONTH) + "</dia>" +
    			"<mes>" + (calendar.get(Calendar.MONTH)+1) + "</mes>" +
    			"<ano>" + calendar.get(Calendar.YEAR) + "</ano> " + 
    			"</data>";
        return xml;
    }
}
