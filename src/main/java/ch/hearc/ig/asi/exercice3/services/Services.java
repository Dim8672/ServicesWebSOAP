package ch.hearc.ig.asi.exercice3.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.tempuri.IPublicServicesValidateUIDBusinessFaultFaultFaultMessage;
import org.tempuri.IPublicServicesValidateUIDInfrastructureFaultFaultFaultMessage;
import org.tempuri.IPublicServicesValidateUIDSecurityFaultFaultFaultMessage;

/**
 *
 * @author dimitri.mella
 * Classe de services qui va appeller les web-services
 */
public class Services {
    
    /**
     * Méthode qui permet de savoir si l'IDE saisie est valide
     * @param ide l'IDE dont on souhaite savoir la validité
     * @return un boolean, true si l'IDE est valide, false si l'IDE est invalide
     */
    public static boolean checkIDE(String ide){
        try {
            org.tempuri.PublicServices service = new org.tempuri.PublicServices();
            org.tempuri.IPublicServices port = service.getBasicHttpBindingIPublicServices();
            
            return port.validateUID(ide);
        } catch (IPublicServicesValidateUIDBusinessFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IPublicServicesValidateUIDInfrastructureFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IPublicServicesValidateUIDSecurityFaultFaultFaultMessage ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
