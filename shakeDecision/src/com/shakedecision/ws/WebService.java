package com.shakedecision.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.shakedecision.app.Yelp;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class WebService {
    private String consumerKey = "twzbfIQ2kQTabM5ClFBrbg";
    private String consumerSecret = "qXDWXe7IdevjgfS2x3-xYwF4oxE";
    private String token = "I4JqXnIc6-4gEBL1ZWlt7m7pt0TkJHHI";
    private String tokenSecret = "lOHpYUFJaBlgZSxZ_P9QZS2UG-Q";

    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String search(@QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude) throws ParseException {
        if (latitude == null || longitude == null) return "Please specify geolocation infomation.";
        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String response = yelp.search("food", Double.parseDouble(latitude), Double.parseDouble(longitude));

        JSONObject json = (JSONObject) new JSONParser().parse(response);
        JSONArray business = (JSONArray) json.get("businesses");
        JSONObject pickedout = null;

        while (pickedout == null || (boolean) pickedout.get("is_closed")) {
            int i = (int) (Math.random() * business.size());
            pickedout = (JSONObject) business.get(i);
        }

        return pickedout.toJSONString();
    }

    @Path("/{order}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("order") String order) {

        return null;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String intro() {

        return "Hello World";

    }

}
