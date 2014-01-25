/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.irs.ives;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
/**
 *
 * @author Administrator
 */
public class Login {
    	protected IDfSessionManager execute(IDfClient dfClient, String docBase,
			String userName, String password, String domain) throws DfException {

		if (docBase == null || userName == null)

			return null;

		if (dfClient != null)

		{
			IDfLoginInfo li = new DfLoginInfo();

			li.setUser(userName);
			li.setPassword(password);
			li.setDomain(domain);

			IDfSessionManager sessionMgr = dfClient.newSessionManager();

			sessionMgr.setIdentity(docBase, li);

			return sessionMgr;
		}

		return null;

	}

}
