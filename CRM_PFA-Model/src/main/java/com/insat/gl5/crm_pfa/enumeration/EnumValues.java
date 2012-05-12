package com.insat.gl5.crm_pfa.enumeration;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


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
