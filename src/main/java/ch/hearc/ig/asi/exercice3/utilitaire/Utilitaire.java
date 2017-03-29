/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.asi.exercice3.utilitaire;

import ch.admin.uid.xmlns.uid_wse.ArrayOfOrganisationType;

/**
 *
 * @author Dimitri
 */
public class Utilitaire {
    
    
    public static String formatIDEDetails(ArrayOfOrganisationType array){
        StringBuilder result = new StringBuilder();
        ch.ech.xmlns.ech_0108_f._3.OrganisationType org = array.getOrganisationType().get(0);
        result.append(org.getOrganisation().getOrganisationIdentification().getOrganisationName());
        result.append("\n");
        result.append(org.getOrganisation().getContact().getAddress().get(0).getPostalAddress().getAddressInformation().getStreet());
        result.append("\n");
        result.append(org.getOrganisationMunicipality().getMunicipalityId());
        result.append(" ");
        result.append(org.getOrganisationMunicipality().getMunicipalityName());
        
        return result.toString();
    }
}
