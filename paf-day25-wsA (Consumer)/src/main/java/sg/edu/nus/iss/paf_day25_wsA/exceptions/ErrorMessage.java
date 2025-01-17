package sg.edu.nus.iss.paf_day25_wsA.exceptions;

import java.util.Date;

public class ErrorMessage {
    
    private int status;
    private String message;
    private Date timeStamp;
    private String endPoint;

    
    public ErrorMessage() {
    }


    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

}
