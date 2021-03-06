package ch.hearc.ig.asi.exercice3.services;

import ch.ech.xmlns.ech_0097_f._2.UidOrganisationIdCategorieType;
import ch.ech.xmlns.ech_0097_f._2.UidStructureType;
import ch.hearc.ig.asi.exercice3.utilitaire.Utilitaire;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            org.tempuri.PublicServices service = new org.tempuri.PublicServices();
            org.tempuri.IPublicServices port = service.getBasicHttpBindingIPublicServices();
            UidStructureType uid = new UidStructureType();
            // Récuperer l'organisationType en prenant que les 3 premières lettres
            uid.setUidOrganisationIdCategorie(UidOrganisationIdCategorieType.valueOf(ide.substring(0, 3)));
            // Supprimer tout ce qui n'est pas un numéro
            String resultat = ide.replaceAll("[^0-9]", "");
            uid.setUidOrganisationId(new BigInteger(resultat));
            
            // Appelle de la méthode pour retourner le String formaté
            return Utilitaire.formatIDEDetails(port.getByUID(uid));
            
        } catch (IPublicServicesGetByUIDBusinessFaultFaultFaultMessage | IPublicServicesGetByUIDInfrastructureFaultFaultFaultMessage | IPublicServicesGetByUIDSecurityFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
