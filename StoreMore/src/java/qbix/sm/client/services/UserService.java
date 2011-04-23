/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.workingonit.gwtbridge.GwtRemoteService;

/**
 *
 * @author iliax
 */
@RemoteServiceRelativePath("userservice.rpc")
@GwtRemoteService("userservice.rpc")
public interface UserService extends RemoteService {
    
}
