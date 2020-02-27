/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.CeridianDayforce;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Integration test
 */
public class CeridianDayforceConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";
        init(connectorName);

        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");

        apiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        apiRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("Authorization", "Basic REZXU1Rlc3Q6REZXU1Rlc3Q=");
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "GET Employees Test Case")
    public void GETEmployeesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(getProxyServiceURLHttp("getEmployees"), "GET",
                eiRequestHeadersMap, null);
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONArray("Data").length(), apiRestResponse.getBody()
                .getJSONArray("Data").length());
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "GET Employee Details Test Case")
    public void GETEmployeeDetailsTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeDetails") + "?xRefCode=42199", "GET",
                        eiRequestHeadersMap, null);

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONObject("Data").length(), apiRestResponse.getBody()
                .getJSONObject("Data").length());
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "GET Employee Details Test Case with contextDate")
    public void GETEmployeeDetailsTestWithcontextDate() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeDetails") + "?xRefCode=42199&contextDate=2017-01-01T13:24:56", "GET",
                        eiRequestHeadersMap, null);

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199?contextDate=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONObject("Data").length(), apiRestResponse.getBody()
                .getJSONObject("Data").length());
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "POST Employee Test Case")
    public void PostEmployeeTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(getProxyServiceURLHttp("postEmployee") +
                        "?isValidateOnly=true", "POST", eiRequestHeadersMap, "postEmployeeRequest.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees?isValidateOnly=true";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "POST", apiRequestHeadersMap,
                "postEmployeeAPIRequest.json", null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONArray("ProcessResults").length(), apiRestResponse.getBody()
                .getJSONArray("ProcessResults").length());
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "GET Employee Addresses Test Case")
    public void GETEmployeeAddressesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeAddresses") + "?xRefCode=42199", "GET",
                        eiRequestHeadersMap, null);

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Addresses";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "POST Employee Addresses Test Case")
    public void PostEmployeeAddressesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeAddresses") +
                "?isValidateOnly=true&xRefCode=42199", "POST", eiRequestHeadersMap, "postEmployeeAddressesRequest.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeCANFederalTaxes Test Case")
    public void getEmployeeCANFederalTaxesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeCANFederalTaxes"), "POST",
                        eiRequestHeadersMap, "getEmployeeCANFederalTaxes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/100421/CANFederalTaxes";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeCANStateTaxes Test Case")
    public void getEmployeeCANStateTaxesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeCANStateTaxes"), "POST",
                        eiRequestHeadersMap, "getEmployeeCANFederalTaxes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/100421/CANStateTaxes";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeCANTaxStatuses Test Case")
    public void getEmployeeCANTaxStatusesTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeCANTaxStatuses"), "POST",
                        eiRequestHeadersMap, "getEmployeeCANFederalTaxes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/100421/CANTaxStatuses";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeContacts Test Case")
    public void getEmployeeContactsTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeContacts"), "POST",
                        eiRequestHeadersMap, "getEmployeeContactsRequest.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Contacts?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeContacts Test Case")
    public void postEmployeeContactsTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeContacts"), "POST",
                        eiRequestHeadersMap, "postEmployeeContactsRequest.json");

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeDirectDeposits Test Case")
    public void getEmployeeDirectDepositsTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeDirectDeposits"), "POST",
                        eiRequestHeadersMap, "getEmployeeDirectDeposits.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/DirectDeposits";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeEmergencyContacts Test Case")
    public void getEmployeeEmergencyContactsTest() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeEmergencyContacts"), "POST",
                        eiRequestHeadersMap, "getEmployeeEmergencyContacts.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmergencyContacts?contextDateRangeTo=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeEmergencyContacts Test Case")
    public void postEmployeeEmergencyContacts() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeEmergencyContacts"), "POST",
                        eiRequestHeadersMap, "postEmployeeEmergencyContacts.json");


        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeEthnicities Test Case")
    public void getEmployeeEthnicities() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeEthnicities"), "POST",
                        eiRequestHeadersMap, "getEmployeeEthnicities.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Ethnicities?contextDateRangeTo=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeHealthAndWellness Test Case")
    public void getEmployeeHealthAndWellness() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeHealthAndWellness"), "POST",
                        eiRequestHeadersMap, "getEmployeeHealthAndWellness.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/HealthWellnessDetails?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeMaritalStatuses Test Case")
    public void getEmployeeMaritalStatuses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeMaritalStatuses"), "POST",
                        eiRequestHeadersMap, "getEmployeeMaritalStatuses.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/MaritalStatuses?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeMaritalStatuses Test Case")
    public void postEmployeeMaritalStatuses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeMaritalStatuses"), "POST",
                        eiRequestHeadersMap, "postEmployeeMaritalStatuses.json");


        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeUSFederalTaxes Test Case")
    public void getEmployeeUSFederalTaxes() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeUSFederalTaxes"), "POST",
                        eiRequestHeadersMap, "getEmployeeUSFederalTaxes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/USFederalTaxes";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeUSStateTaxes Test Case")
    public void getEmployeeUSStateTaxes() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeUSStateTaxes"), "POST",
                        eiRequestHeadersMap, "getEmployeeUSStateTaxes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/USStateTaxes?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeUSTaxStatuses Test Case")
    public void getEmployeeUSTaxStatuses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeUSTaxStatuses"), "POST",
                        eiRequestHeadersMap, "getEmployeeUSTaxStatuses.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/USTaxStatuses";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeClockDeviceGroups Test Case")
    public void getEmployeeClockDeviceGroups() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeClockDeviceGroups"), "POST",
                        eiRequestHeadersMap, "getEmployeeClockDeviceGroups.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/63499/ClockDeviceGroups";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeCompensationSummary Test Case")
    public void getEmployeeCompensationSummary() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeCompensationSummary"), "POST",
                        eiRequestHeadersMap, "getEmployeeCompensationSummary.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/CompensationSummary";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeCourses Test Case")
    public void getEmployeeCourses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeCourses"), "POST",
                        eiRequestHeadersMap, "getEmployeeCourses.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Courses";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeEmploymentAgreements Test Case")
    public void getEmployeeEmploymentAgreements() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeEmploymentAgreements"), "POST",
                        eiRequestHeadersMap, "getEmployeeEmploymentAgreements.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmploymentAgreements?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeEmploymentAgreements Test Case")
    public void postEmployeeEmploymentAgreements() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeEmploymentAgreements"), "POST",
                        eiRequestHeadersMap, "postEmployeeEmploymentAgreements.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeEmploymentStatuses Test Case")
    public void getEmployeeEmploymentStatuses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeEmploymentStatuses"), "POST",
                        eiRequestHeadersMap, "getEmployeeEmploymentStatuses.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmploymentStatuses?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeEmploymentStatuses Test Case")
    public void postEmployeeEmploymentStatuses() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeEmploymentStatuses"), "POST",
                        eiRequestHeadersMap, "postEmployeeEmploymentStatuses.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeEmploymentTypes Test Case")
    public void getEmployeeEmploymentTypes() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeEmploymentTypes"), "POST",
                        eiRequestHeadersMap, "getEmployeeEmploymentTypes.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmploymentTypes?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeHighlyCompensatedEmployees Test Case")
    public void getEmployeeHighlyCompensatedEmployees() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeHighlyCompensatedEmployees"), "POST",
                        eiRequestHeadersMap, "getEmployeeHighlyCompensatedEmployees.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/HighlyCompensatedEmployees";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeHRIncidents Test Case")
    public void getEmployeeHRIncidents() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeHRIncidents"), "POST",
                        eiRequestHeadersMap, "getEmployeeHRIncidents.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/HRIncidents";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeLabourDefaults Test Case")
    public void getEmployeeLabourDefaults() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeLabourDefaults"), "POST",
                        eiRequestHeadersMap, "getEmployeeLabourDefaults.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/LaborDefaults";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeOnboardingPolicies Test Case")
    public void getEmployeeOnboardingPolicies() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeOnboardingPolicies"), "POST",
                        eiRequestHeadersMap, "getEmployeeOnboardingPolicies.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/OnboardingPolicies?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeOnboardingPolicies Test Case")
    public void postEmployeeOnboardingPolicies() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeOnboardingPolicies"), "POST",
                        eiRequestHeadersMap, "postEmployeeOnboardingPolicies.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeOrgInfo Test Case")
    public void getEmployeeOrgInfo() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeOrgInfo"), "POST",
                        eiRequestHeadersMap, "getEmployeeOrgInfo.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/OrgUnitInfos";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeePayAdjustmentGroups Test Case")
    public void getEmployeePayAdjustmentGroups() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeePayAdjustmentGroups"), "POST",
                        eiRequestHeadersMap, "getEmployeePayAdjustmentGroups.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmployeePayAdjustCodeGroups";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeePayGradeRates Test Case")
    public void getEmployeePayGradeRates() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeePayGradeRates"), "POST",
                        eiRequestHeadersMap, "getEmployeePayGradeRates.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/PayGradeRates?contextDate=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeePerformanceRatings Test Case")
    public void getEmployeePerformanceRatings() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeePerformanceRatings"), "POST",
                        eiRequestHeadersMap, "getEmployeePerformanceRatings.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/PerformanceRatings";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeProperties Test Case")
    public void getEmployeeProperties() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeProperties"), "POST",
                        eiRequestHeadersMap, "getEmployeeProperties.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmployeeProperties?contextDate=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeProperties Test Case")
    public void postEmployeeProperties() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeProperties"), "POST",
                        eiRequestHeadersMap, "postEmployeeProperties.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeSkills Test Case")
    public void getEmployeeSkills() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeSkills"), "POST",
                        eiRequestHeadersMap, "getEmployeeSkills.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Skills";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeTrainingPrograms Test Case")
    public void getEmployeeTrainingPrograms() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeTrainingPrograms"), "POST",
                        eiRequestHeadersMap, "getEmployeeTrainingPrograms.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/TrainingPrograms";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeUnionMemberships Test Case")
    public void getEmployeeUnionMemberships() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeUnionMemberships"), "POST",
                        eiRequestHeadersMap, "getEmployeeUnionMemberships.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/UnionMemberships";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeWorkAssignments Test Case")
    public void getEmployeeWorkAssignments() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeWorkAssignments"), "POST",
                        eiRequestHeadersMap, "getEmployeeWorkAssignments.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/WorkAssignments?contextDate=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeWorkAssignments Test Case")
    public void postEmployeeWorkAssignments() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeWorkAssignments"), "POST",
                        eiRequestHeadersMap, "postEmployeeWorkAssignments.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeWorkContracts Test Case")
    public void getEmployeeWorkContracts() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeWorkContracts"), "POST",
                        eiRequestHeadersMap, "getEmployeeWorkContracts.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/WorkContracts?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeWorkContracts Test Case")
    public void postEmployeeWorkContracts() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeWorkContracts"), "POST",
                        eiRequestHeadersMap, "postEmployeeWorkContracts.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getDocumentManagementSecurityGroups Test Case")
    public void getDocumentManagementSecurityGroups() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getDocumentManagementSecurityGroups"), "POST",
                        eiRequestHeadersMap, "getDocumentManagementSecurityGroups.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/DocumentManagementSecurityGroups";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeLocations Test Case")
    public void getEmployeeLocations() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeLocations"), "POST",
                        eiRequestHeadersMap, "getEmployeeLocations.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Locations?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeLocations Test Case")
    public void postEmployeeLocations() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeLocations"), "POST",
                        eiRequestHeadersMap, "postEmployeeLocations.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeManagers Test Case")
    public void getEmployeeManagers() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeManagers"), "POST",
                        eiRequestHeadersMap, "getEmployeeManagers.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmployeeManagers?contextDateRangeFrom=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeRoles Test Case")
    public void getEmployeeRoles() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeRoles"), "POST",
                        eiRequestHeadersMap, "getEmployeeRoles.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Roles?contextDate=2017-01-01T13:24:56";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeRoles Test Case")
    public void postEmployeeRoles() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeRoles"), "POST",
                        eiRequestHeadersMap, "postEmployeeRoles.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeSSOAccounts Test Case")
    public void getEmployeeSSOAccounts() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeSSOAccounts"), "POST",
                        eiRequestHeadersMap, "getEmployeeSSOAccounts.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/SSOAccounts";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeSSOAccounts Test Case")
    public void postEmployeeSSOAccounts() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeSSOAccounts"), "POST",
                        eiRequestHeadersMap, "postEmployeeSSOAccounts.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getUserPayAdjustCodeGroups Test Case")
    public void getUserPayAdjustCodeGroups() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getUserPayAdjustCodeGroups"), "POST",
                        eiRequestHeadersMap, "getUserPayAdjustCodeGroups.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/UserPayAdjustCodeGroups";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeWorkAssignmentManagers Test Case")
    public void getEmployeeWorkAssignmentManagers() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeWorkAssignmentManagers"), "POST",
                        eiRequestHeadersMap, "getEmployeeWorkAssignmentManagers.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/EmployeeWorkAssignmentManagers";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeWorkAssignmentManagers Test Case")
    public void postEmployeeWorkAssignmentManagers() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeWorkAssignmentManagers"), "POST",
                        eiRequestHeadersMap, "postEmployeeWorkAssignmentManagers.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getAListOfDocuments Test Case")
    public void getAListOfDocuments() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getAListOfDocuments"), "POST",
                        eiRequestHeadersMap, "getAListOfDocuments.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Documents?employeeXRefCode=42199";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getDocumentDetails Test Case")
    public void getDocumentDetails() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getDocumentDetails"), "POST",
                        eiRequestHeadersMap, "getDocumentDetails.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Documents/696afd0c-5890-4316-9b7e-7ac990189018";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getAvailability Test Case")
    public void getAvailability() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getAvailability"), "POST",
                        eiRequestHeadersMap, "getAvailability.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Availability?filterAvailabilityStartDate=2018-02-04T00:00:00&filterAvailabilityEndDate=2018-02-18T00:00:00";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getSchedules Test Case")
    public void getSchedules() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getSchedules"), "POST",
                        eiRequestHeadersMap, "getSchedules.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/Schedules?filterScheduleStartDate=2018-02-04T00:00:00&filterScheduleEndDate=2018-02-18T00:00:00";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getTimeAwayFromWork Test Case")
    public void getTimeAwayFromWork() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getTimeAwayFromWork"), "POST",
                        eiRequestHeadersMap, "getTimeAwayFromWork.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Employees/42199/TimeAwayFromWork?filterTAFWStartDate=2018-02-04T00:00:00&filterTAFWEndDate=2018-02-18T00:00:00&status=APPROVED";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeePunches Test Case")
    public void getEmployeePunches() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeePunches"), "POST",
                        eiRequestHeadersMap, "getEmployeePunches.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/EmployeePunches?filterTransactionStartTimeUTC=2019-03-25T00:00:00&filterTransactionEndTimeUTC=2019-03-29T00:00:00";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getEmployeeRawPunches Test Case")
    public void getEmployeeRawPunches() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getEmployeeRawPunches"), "POST",
                        eiRequestHeadersMap, "getEmployeeRawPunches.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/EmployeeRawPunches?filterTransactionStartTimeUTC=2019-06-03T00:00:00&filterTransactionEndTimeUTC=2019-06-05T00:00:00";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postEmployeeRawPunches Test Case")
    public void postEmployeeRawPunches() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postEmployeeRawPunches"), "POST",
                        eiRequestHeadersMap, "postEmployeeRawPunches.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getReportMetadata Test Case")
    public void getReportMetadata() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getReportMetadata"), "POST",
                        eiRequestHeadersMap, "getReportMetadata.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/ReportMetadata";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getReportMetadataDetails Test Case")
    public void getReportMetadataDetails() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getReportMetadataDetails"), "POST",
                        eiRequestHeadersMap, "getReportMetadataDetails.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/ReportMetadata/Payroll_Earning_Hours_Detail";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getReports Test Case")
    public void getReports() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getReports"), "POST",
                        eiRequestHeadersMap, "getReports.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/Reports/Payroll_Earning_Hours_Detail?pageSize=1";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getJobPostings Test Case")
    public void getJobPostings() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getJobPostings"), "POST",
                        eiRequestHeadersMap, "getJobPostings.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/JobFeeds";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().length(), (apiRestResponse.getBody().length()));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getOrgUnits Test Case")
    public void getOrgUnits() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getOrgUnits"), "POST",
                        eiRequestHeadersMap, "getOrgUnits.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/OrgUnits";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "postOrgUnits Test Case")
    public void postOrgUnits() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("postOrgUnits"), "POST",
                        eiRequestHeadersMap, "postOrgUnits.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
    }

    @Test(enabled = true, groups = {"wso2.ei"}, description = "getOrgUnitDetails Test Case")
    public void getOrgUnitDetails() throws Exception {
        RestResponse<JSONObject> eiRestResponse =
                sendJsonRestRequest(getProxyServiceURLHttp("getOrgUnitDetails"), "POST",
                        eiRequestHeadersMap, "getOrgUnitDetails.json");

        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + connectorProperties
                .getProperty("apiVersion") + "/OrgUnits/Store320?includeChildOrgUnits=true";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequestHTTPS(apiEndPoint, "GET", apiRequestHeadersMap,
                null, null);

        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getString("Data"), (apiRestResponse.getBody().getString("Data")));
    }
}
