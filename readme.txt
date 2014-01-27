Add on for Taskspace to allow JSP html templates to be used
for reports.

Install
copy zip file with contents
    custom
    WEB-INF
to EMC Documentum Taskspace war.
This will update the directories
 custom/
     src:     java source code
     config : with action and component definitions
     reportview : jsp template report
 WEB-INF/classes : java package
         gov.irs.ives

In content server repository system cabinet 
   Applications/App_Name/actions
         ts_plain_actions.xml
 
   add action description:
    <plain-action desc="LAUNCH_REPORT_ACT_LABEL" id="ives_transcript"/>

In EMC Documentum Taskspace
   Configure Tab | Actions
   Create an action using this Basic action.

Within EMC Documentum Form Builder
   ts_designer user will beable to configure
   InvokeButton on a form to launch the jsp form.