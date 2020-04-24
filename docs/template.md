# Working with Employee Addresses

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 

The following operations allow you to retrieve, create or update addresses of an employee

| Operation | Description |
| ------------- |-------------|
|[](#)|  |
|[](#)|  |
|[](#)|  |

### Operation details

This section provides more details on each of the operations.

#### Retrieving Employee Addresses
We can use GET Employee addresses operation with required parameters to search and find the required employees.

**GET Employee Addresses**
```xml
<ceridiandayforce.getEmployeeAddresses>
    <xRefCode>{$ctx:xRefCode}</xRefCode>
</ceridiandayforce.getEmployeeAddresses>
```

**Properties**

* xRefCode (Mandatory): The unique identifier (external reference code) of the employee whose data will be retrieved. The value provided must be the exact match for an employee; otherwise, a bad request (400) error will be returned.

**Sample request**

Following is a sample request that can be handled by this operation.

```json

```

**Sample response**

Given below is a sample response for this operation.

```json

```

**Related Dayforce documentation**

[]()

### Sample configuration

Following example illustrates how to connect to Dayforce with the init operation and query operation.

1.Create a sample proxy as below :
```xml

```

2.Create a json file named query.json and copy the configurations given below to it:

```json

```
3.Replace the credentials with your values.

4.Execute the following curl command:

```bash
curl http://localhost:8280/services/query -H "Content-Type: application/json" -d @query.json
```
5.Dayforce returns HTTP Code 200
