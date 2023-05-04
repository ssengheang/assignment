package com.example.groupassessment.enitity.response;

public enum ApiStatus {

    /***
     * 1xx: Informational Communicates transfer protocol-level information.
     * 2xx: Success	Indicates that the client’s request was accepted successfully.
     * 3xx: Redirection	Indicates that the client must take some additional action in order to complete their request.
     * 4xx: Client Error This category of error status codes points the finger at clients.
     * 5xx: Server Error The server takes responsibility for these error status codes.
     */


    // Standard Code

    SUC_RETRIEVED("200", "Successfully retrieved data."),

    BAD_REQUEST("400","Bad Request."),
    UNAUTHORIZED("401","Unauthorized."),
    NOT_FOUND("404","Record not found."),

    SERVER_ERROR("500","Internal Server Error."),

    // --- Custom Code:

    SUC_CREATED("201", "Record created successfully."),
    SUC_UPLOADED("201", "File uploaded successfully."),
    SUC_UPDATED("201", "Record updated successfully."),
    SUC_DELETED("200", "Record deleted successfully."),

    FAI_CREATED("4011", "Unable to create record"),
    FAI_UPDATED("4022","Unable to update record"),
    FAI_DELETED("4033","Unable to delete record");

    private final String code;
    private final String message;

    ApiStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}

