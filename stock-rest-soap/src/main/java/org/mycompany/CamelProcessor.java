package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.*;
 
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.service.model.BindingOperationInfo;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CamelProcessor implements Processor {

	@Override
 
	
	public void process(Exchange exchange) throws Exception {
		BindingOperationInfo boi = (BindingOperationInfo) exchange.getProperty(BindingOperationInfo.class.getName());
 
        // Get the parameters list which element is the holder.
        MessageContentsList msgList = (MessageContentsList) exchange.getIn().getBody();
        
        exchange.setProperty("symbol",((InputSymbol)msgList.get(0)).getSymbol().trim());
	}
		
 

}
