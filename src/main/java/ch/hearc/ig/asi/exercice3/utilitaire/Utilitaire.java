/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.asi.exercice3.utilitaire;

import ch.admin.uid.xmlns.uid_wse.ArrayOfOrganisationType;
import ch.ech.xmlns.ech_0108_f._3.OrganisationType;

/**
 *
 * @author Dimitri
 * classe d'Utilitaire fournissant des services de mise en forme de l'information
 */
public class Utilitaire {
    
    /**
     * Méthode qui récupère les informations à partir d'un tableau de OrganisationType et qui les met en forme
     * @param array Un tableau de OrganisationType
     * @return un String formaté avec les informations suivantes : NomOrganisation,Adresse,CodePostal et NomMunicipalite
     */
    public static String formatIDEDetails(ArrayOfOrganisationType array){
        StringBuilder result = new StringBuilder();
        OrganisationType org = array.getOrganisationType().get(0);
        result.append(org.getOrganisation().getOrganisationIdentification().getOrganisationName()); //NomOrganisation
        result.append("\n");
        result.append(org.getOrganisation().getContact().getAddress().get(0).getPostalAddress().getAddressInformation().getStreet()); //Adresse
        result.append("\n");
        result.append(org.getOrganisationMunicipality().getMunicipalityId()); //CodePostal
        result.append(" ");
        result.append(org.getOrganisationMunicipality().getMunicipalityName()); //NomMunicipalite
        
        return result.toString();
    }
}
