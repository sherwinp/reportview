/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.irs.ives;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Administrator
 */
public class ReportObject {
    public String r_object_id;
    public String participantName;
    public String participantId;
    public String participantPhone;
    public String participantFaxNumber;
    public String bacthNumber;
    public String reportDate;
    
    public int exceptionRecordCount=-1;
    public String[] nameFirst;
    public String[] nameLast;
    public String[] Tin;
    public String[] taxPeriod;
    public String[] formNumber;
    public String[] exceptionString;
    public String ReportDate(){
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	return outputFormat.format(Calendar.getInstance().getTime());
    }
}
