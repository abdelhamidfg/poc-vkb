package org.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redhat.ContactInfo;
@Component
public class LocationRoute extends RouteBuilder{

	@Autowired
    private CamelContext camelContext;
    
    
	@Override
	public void configure() throws Exception {
		
		
		restConfiguration()
		.component("undertow")
		.bindingMode(RestBindingMode.json)
	    .host("0.0.0.0").port(8080)
			.enableCORS(true)
	    	
			.contextPath("/")
	    	.dataFormatProperty("prettyPrint", "true")
	    	.enableCORS(true)
	    	.apiContextPath("/api-doc")
	    	.apiProperty("api.title", "Location API")
	    	.apiProperty("api.version", "1.0.0")
	    ;
        
        rest("/location").description("Location information")
         .consumes("application/json").produces("application/json")
         .get("/contact/{id}").description("Location Contact Info")
             .responseMessage().code(200).message("Data successfully returned").endResponseMessage()
             .to("direct:getalllocationphone")

        ;

        from("direct:getalllocationphone")
         .setBody().simple("${headers.id}")
            
            .unmarshal().json(JsonLibrary.Jackson)
            .to("cxf://{{soap.endpoint}}?serviceClass=com.redhat.LocationDetailServicePortType&defaultOperationName=contact")
 //http://dayinthelife-integration-fuse79.apps.cluster.ocp-hamid.com/ws/location
            //http://location-soap-location-soap.apps.cluster.ocp-hamid.com:80/ws
            .process(
                    new Processor(){

                        @Override
                        public void process(Exchange exchange) throws Exception {

                            MessageContentsList list = (MessageContentsList)exchange.getIn().getBody();

                            exchange.getOut().setBody((ContactInfo)list.get(0));
                        }

						
                    }
            )

        ;

     }}

		
	


