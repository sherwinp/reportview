<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/dmform_1_0.tld" prefix="dmf" %>
<%@ taglib uri="/WEB-INF/tlds/dmformext_1_0.tld" prefix="dmfx" %>
<%@ page import="com.documentum.web.form.Form" %>
<%@ page import="com.documentum.fc.client.*" %>
<%@ page import="com.documentum.fc.common.*" %>
<%@ page import="gov.irs.ives.ReportHandler" %>
<%@ page import="gov.irs.ives.ReportObject" %>
<!DOCTYPE html>
<dmf:html>
    <dmf:head>
        <title>report</title>
		<dmf:webform/>
        <style type="text/css">
            * {margin: 0; padding: 0;}
            table{ margin: 0; padding: 0;}
            th{border:1px solid black; margin: 0; padding: 0;}
            .discrep td{border:1px solid black;padding-left:3px;}
        </style>    
    </dmf:head>
    <dmf:body>
<dmf:form>
<%
   ReportHandler form = (ReportHandler)pageContext.getAttribute(Form.FORM, PageContext.REQUEST_SCOPE);
   ReportObject oData = (ReportObject)form.oData;
%>    
        <a href="../../">go back</a><h1 style="text-align: center;">Income Verification Express Service</h1>
        <table width="95%" style="font-weight: bold;">
            <tr><td><label for="ParticipantId">Participant Id:</label></td> 
                <td><%=ReportHandler.HSV("")%></td>
                <td><label for="Date">Date:</label></td>
                <td></td>
            </tr>
            <tr><td><label for="ParticipantName">Participant Name:</label></td>
                <td>&nbsp;</td>
                <td><label for="Phone">Phone:</label><td>
                <td></td>
            </tr>
            <tr><td><label for="ParticipantEmail">Participant Email:</label></td>
                <td>&nbsp;</td>
                <td><label for="FaxNumber">Fax Number:</label><td>
                <td></td>
            </tr>
        </table>
        <table class="discrep" cellpadding="0px" cellspacing="0px"  style="width:95%;border:1px solid black; margin: 0; padding: 0;">
	    <tr><th><label>&nbsp;</label></th>
		<th><label>First Four Characters of Last/First Initial </label></th>
		<th><label>First Four Characters of Business Name</label></th>
		<th><label>Last Four digits of Tin</label></th>
		<th><label>Tax Period Ending</label></th>
		<th><label>Form Number</label></th>
		<th style="width:30%;" ><label>IRS Use Only</label></th>
	    </tr>
			<% for ( int i=1; i < 33; i++){ 
                           if( oData!=null && oData.exceptionRecordCount < i ){
                        %>
			<tr><td><%=i %>.</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
                            <td>&nbsp;</td>
			</tr>
			<%}else{%>
                        <tr><td><%=i%>.</td>
                            <td><%=oData.nameFirst[i-1]%></td>
                            <td><%=oData.nameLast[i-1]%></td>
                            <td><%=oData.Tin[i-1]%></td>
                            <td><%=oData.taxPeriod[i-1]%></td>
                            <td><%=oData.formNumber[i-1]%></td>
                            <td><%=oData.exceptionString[i-1]%></td>
                         </tr>
                        <%
                         }
                         }%>
	</table>
        </dmf:form>
       </dmf:body>
 </dmf:html>