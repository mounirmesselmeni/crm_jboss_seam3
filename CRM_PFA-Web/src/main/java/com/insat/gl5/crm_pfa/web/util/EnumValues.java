package com.insat.gl5.crm_pfa.web.util;

import com.insat.gl5.crm_pfa.enumeration.AddressType;
import com.insat.gl5.crm_pfa.enumeration.CustomerType;
import com.insat.gl5.crm_pfa.enumeration.Gouvernment;
import com.insat.gl5.crm_pfa.enumeration.OpportunityType;
import com.insat.gl5.crm_pfa.enumeration.PhoneNumberType;
import com.insat.gl5.crm_pfa.enumeration.PriorityType;
import com.insat.gl5.crm_pfa.enumeration.Salutation;
import com.insat.gl5.crm_pfa.enumeration.TaskType;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@ApplicationScoped
public class EnumValues {

    public List<AddressType> getAddressTypes() {
        return Arrays.asList(AddressType.values());
    }
    
    public List<CustomerType> getCustomerTypes() {
        return Arrays.asList(CustomerType.values());
    }

    public List<Salutation> getSalutations() {
        return Arrays.asList(Salutation.values());
    }

    public List<Gouvernment> getGouvernments() {
        return Arrays.asList(Gouvernment.values());
    }

    public List<OpportunityType> getOpportunityTypes() {
        return Arrays.asList(OpportunityType.values());
    }
    
    public List<PhoneNumberType> getPhoneNumberTypes() {
        return Arrays.asList(PhoneNumberType.values());
    }
    
    public List<PriorityType> getPriorityTypes() {
        return Arrays.asList(PriorityType.values());
    }
    
    public List<TaskType> getTaskTypes() {
        return Arrays.asList(TaskType.values());
    }
}
