/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.irs.ives;

import com.documentum.web.common.ArgumentList;
import com.documentum.web.form.Control;
import com.documentum.web.formext.component.Component;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfModule;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.methodserver.IDfMethod;

/**
 *
 * @author Administrator
 */
public class ReportHandler extends Component {
    public ReportObject oData = null;
    public void onInit(ArgumentList args)
    {
        super.onInit(args);
        
        oData = fill( "DEV01", "dmadmin", "Niksoft1", "", "090003f280005cb1" );
    }
    public void onValidate(Control control, ArgumentList args) {
    
    }
    public void onSubmit(Control control, ArgumentList args) {
    }
    IDfSessionManager manager = null;

    public ReportObject fill(String m_docBase, String m_userName, String m_password, String m_domain, String objectid) {

        IDfSession session = null;
        try {
            // now handle login info
            DfClientX clientx = new DfClientX();
            IDfClient dfClient = clientx.getLocalClient();

            manager = new Login().execute(dfClient, m_docBase, m_userName, m_password, m_domain);
            if (manager == null) {

                return null;
            }
            session = manager.getSession(m_docBase);
            if (session == null) {

                return null;
            }
        } catch (DfException ex) {
            return null;
        }
         // now handle query info

        IDfQuery docQuerySV = new DfQuery(
                String.format("SELECT r_object_id, cf_participantid, cf_number_of_request, cf_caller_id, cf_remote_tsi, cf_batch_number, cf_date_received, cr_name_first, cr_name_last, cr_tin, cr_tax_period, cr_form_number, cr_except_string FROM business_fax where r_object_id='%s'", objectid)
        );
        ReportObject reportObject = null;
        // now execute query 
        IDfCollection collectionSV = null;
        try {
            collectionSV = docQuerySV.execute(session, IDfQuery.DF_READ_QUERY);
            collectionSV.next();
            if(collectionSV.getValueCount("cf_participantid") > 0){
                reportObject = new ReportObject();
                reportObject.r_object_id = collectionSV.getString("r_object_id");
                reportObject.participantId = collectionSV.getString("cf_participantid");
                reportObject.bacthNumber = collectionSV.getString("cf_batch_number");
                reportObject.participantFaxNumber = collectionSV.getString("cf_caller_id");
                reportObject.participantName = "";
                reportObject.participantPhone = "";
                reportObject.exceptionRecordCount = collectionSV.getValueCount("cr_name_first");
                reportObject.nameFirst = getRepeatingValue(collectionSV, "cr_name_first");
                reportObject.nameLast = getRepeatingValue(collectionSV, "cr_name_last");
                reportObject.Tin = getRepeatingValue(collectionSV, "cr_tin");
                reportObject.formNumber = getRepeatingValue(collectionSV, "cr_form_number");
                reportObject.taxPeriod = getRepeatingValue(collectionSV, "cr_tax_period");
                reportObject.exceptionString = getRepeatingValue(collectionSV, "cr_except_string");
            }
            
        } catch (DfException ex) {

            return null;
        } finally {
            try {
                collectionSV.close();
            } catch (DfException ex) {
            }
        }

        return reportObject;
    }
    String[] getRepeatingValue(IDfCollection collectionSV, String valName) throws DfException{
          int rpvCount = collectionSV.getValueCount(valName);
          if (rpvCount < 1) return new String[]{};
          String[] vals = new String[rpvCount];
          for(int idx=0; idx < rpvCount; idx++){
              vals[idx]=collectionSV.getRepeatingString(valName, idx);
          }
          return vals;
    }
    public static String HSV(String value){
        if(value==null || value=="")
            return "&nbsp;";
        return value;
    }
    public static void main(String[] args) {
        ReportHandler rh = new ReportHandler();

        ReportObject obj = rh.fill( "DEV01", "dmadmin", "Niksoft1", "", "%" );
    }
}
