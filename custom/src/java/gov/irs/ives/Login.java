/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.irs.ives;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;

/**
 *
 * @author Administrator
 */
public class Login {

    IDfSession getSession(IDfSessionManager manager, IDfLoginInfo loginInfo, String docBase) throws DfException {

        // now handle login info
        DfClientX clientx = new DfClientX();
        IDfClient dfClient = clientx.getLocalClient();
        if (dfClient != null) {

            manager = dfClient.newSessionManager();

            manager.setIdentity(docBase, loginInfo);
        }
        if (manager == null) {
            return null;
        }
        return manager.getSession(docBase);

    }
}
