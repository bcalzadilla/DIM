package com.dim.entuizer.dim;

/**
 * Created by bcalz on 28/09/2016.
 */
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


@ThingworxPropertyDefinitions(properties = {
        @ThingworxPropertyDefinition(name="serialNumber", description="Device Serial Number", baseType="STRING", category="", aspects={"isReadOnly:false", "defaultValue:1111111"}),
        @ThingworxPropertyDefinition(name="count", description="sample number field", baseType="NUMBER", category="", aspects={"isReadOnly:false"}),
})
public class SampleThing extends VirtualThing {

    double count = 0.0;
    int scanCount = 0;

    public SampleThing(String name, String description, ConnectedThingClient client) throws Exception {
        super(name, description, client);

        // Copy all the default values from the aspects of the properties defined above to this
        // instance. This gives it an initial state but does not push it to the server.
        initializeFromAnnotations();

    }



    /**
     * The application that binds this Thing to its connection is responsible for calling this method
     * periodically to allow this Thing to generate simulated data. If your application generates
     * data instead of simulating it, your would update your properties when new data is available
     * and then call updateSubscribedProperties() to push these values to the server. This method
     * can also be used to poll your hardware if it does not deliver its own data asynchronously.
     * @throws Exception
     */
    @Override
    public void processScanRequest() throws Exception {
        count ++;
        setProperty("count", count);
        updateSubscribedProperties(15000);
    }


    /**
     * This sample method will be available to be bound and can be called from the server.
     * It adds its two parameters together and returns the result.
     * @param a the first addend
     * @param b the second addend
     * @return the sum of a + b
     * @throws Exception
     */
    @ThingworxServiceDefinition( name="AddNumbers", description="Add Two Numbers")
    @ThingworxServiceResult( name="result", description="Result", baseType="NUMBER" )
    public Double AddNumbers(
            @ThingworxServiceParameter( name="a", description="Value 1", baseType="NUMBER" ) Double a,
            @ThingworxServiceParameter( name="b", description="Value 2", baseType="NUMBER" ) Double b) throws Exception {

        return a + b;
    }

}
