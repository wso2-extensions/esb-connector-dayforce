# Ceridian Dayforce ESB Connector

The Ceridian Dayforce [connector](https://docs.wso2.com/display/EI650/Working+with+Connectors) helps you to 
connect to the Ceridian dayforce system from WSO2 ESB / EI perform operations on it.

The developer API can be found in [Ceredian Deoveloper site](https://developers.dayforce.com/Build/API-Explorer.aspx).

#### Building from the source

Follow the steps below to build the connector from the source code.

- Get a clone or download the source from the [repository](https://bitbucket.org/thementornetwork/wso2-integration).
- Run the following Maven command from the esb-ceridian-dayforce-connector directory: `mvn clean install`.
- The ZIP file with the Ceridian Dayforce connector will be created in the esb-ceridian-dayforce-connector/target 
directory.

## Configuring Ceridian Dayforce REST Operations

[[Prerequisites]](#Prerequisites) [[Initializing the connector]](#initializing-the-connector)

### Prerequisites

> NOTE: For development purposes we can use test credentials provided by Dayforce. However, to understand the Dayforce API and the request responses handled by Dayforce it is recommended that you create a developer account. If you do not have a Dayforce account, go to [https://developers.dayforce.com/Special-Pages/Registration.aspx](https://developers.dayforce.com/Special-Pages/Registration.aspx) and create a Dayforce developer account.

To use the Darforce REST connector, add the <ceridiandayforce.init> element in your configuration before carrying out any other Dayforce REST operation. 

### Initializing the connector
Add the following <ceridiandayforce.init>

#### init
```xml
<ceridiandayforce.init>
        <username>{$ctx:username}</username>
        <password>{$ctx:ceredianPwd}</password>
        <clientNamespace>{$ctx:clientNamespace}</clientNamespace>
        <apiVersion>{$ctx:apiVersion}</apiVersion>
</ceridiandayforce.init>
```

**Properties**
* username: The username of your Dayforce environment. For testing we can use the sample environment credential: DFWSTest
* password: The password of your Dayforce environment. For testing we can use the sample environment credential: DFWSTest
* clientNamespace: The namespace of your Dayforce environment. For testing we can use the sample environment: usconfigr57.dayforcehcm.com/Api/ddn
* apiVersion: The version of the API you want to call. For testing we will set it to: V1

Now that you have connected to Dayforce, use the information in the following topics to perform various operations with the connector.

[Working with Employees](docs/employee.md)  
[Working with Employee Personal Information](docs/employeePersonalInformation/employeePersonalInformation.md)  
[Working with Employee Employment Information](docs/employeeEmploymentInformation/employeeEmploymentInformation.md)  
[Working with User Security, Authority & Management](docs/userSecurityAuthorityAndManagement/userSecurityAuthorityAndManagement.md)  
[Working with Employee Documents](docs/employeeDocuments/employeeDocuments.md)  
[Working with Employees Time Management](docs/employeeTimeManagement/employeeTimeManagement.md)  
[Working with Reporting](docs/reporting/reporting.md)  
[Working with Recruiting](docs/recruiting/recruiting.md)  
[Working with Configuration](docs/configuration/configuration.md)  
[Working with Employment Eligibility Verification](docs/employmentEligibilityVerification/employmentEligibilityVerification.md)  