package ch.hearc.ig.asi.exercice3.services;

import ch.admin.uid.xmlns.uid_wse.ArrayOfOrganisationType;
import ch.ech.xmlns.ech_0097_f._2.UidOrganisationIdCategorieType;
import ch.ech.xmlns.ech_0097_f._2.UidStructureType;
import ch.ech.xmlns.ech_0098_f._3.OrganisationType;
import ch.hearc.ig.asi.exercice3.utilitaire.Utilitaire;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tempuri.IPublicServicesGetByUIDBusinessFaultFaultFaultMessage;
import org.tempuri.IPublicServicesGetByUIDInfrastructureFaultFaultFaultMessage;
import org.tempuri.IPublicServicesGetByUIDSecurityFaultFaultFaultMessage;
import org.tempuri.IPublicServicesValidateUIDBusinessFaultFaultFaultMessage;
import org.tempuri.IPublicServicesValidateUIDInfrastructureFaultFaultFaultMessage;
import org.tempuri.IPublicServicesValidateUIDSecurityFaultFaultFaultMessage;

/**
 *
 * @author dimitri.mella Classe de services qui va appeller les web-services
 */
public class Services {

    /**
     * Méthode qui permet de savoir si l'IDE saisie est valide
     *
     * @param ide l'IDE dont on souhaite savoir la validité
     * @return un boolean, true si l'IDE est valide, false si l'IDE est invalide
     */
    public static boolean checkIDE(String ide) {
        try {
            org.tempuri.PublicServices service = new org.tempuri.PublicServices();
            org.tempuri.IPublicServices port = service.getBasicHttpBindingIPublicServices();

            return port.validateUID(ide);
        } catch (IPublicServicesValidateUIDBusinessFaultFaultFaultMessage | IPublicServicesValidateUIDInfrastructureFaultFaultFaultMessage | IPublicServicesValidateUIDSecurityFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Méthode permettant d'appeller les webServices afin d'avoir les informations à propos d'un IDE
     * @param ide l'IDE pour lequel l'on souhaite avoir des informations
     * @return un ArrayOfOrganisationType
     */
    public static String getIDEDetails(String ide) {
        try {
            StringBuilder resultat = new StringBuilder();
            org.tempuri.PublicServices service = new org.tempuri.PublicServices();
            org.tempuri.IPublicServices port = service.getBasicHttpBindingIPublicServices();
            UidStructureType uid = new UidStructureType();
            uid.setUidOrganisationIdCategorie(UidOrganisationIdCategorieType.valueOf(ide.substring(0, 3)));
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(ide);
            while(m.find()){
                resultat.append(m.group());
            }
            uid.setUidOrganisationId(new BigInteger(resultat.toString()));
            return Utilitaire.formatIDEDetails(port.getByUID(uid));
        } catch (IPublicServicesGetByUIDBusinessFaultFaultFaultMessage | IPublicServicesGetByUIDInfrastructureFaultFaultFaultMessage | IPublicServicesGetByUIDSecurityFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
