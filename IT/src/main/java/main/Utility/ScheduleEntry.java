package main.Utility;

public class ScheduleEntry {
    private String scheduleId;
    private String employeeId;
    private String days;
    private String startTime;
    private String endTime;

    public ScheduleEntry(String scheduleId, String employeeId, String days, String startTime, String endTime) {
        this.scheduleId = scheduleId;
        this.employeeId = employeeId;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public ScheduleEntry( String employeeId, String days, String startTime, String endTime) {
        this.employeeId = employeeId;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getScheduleId() {
        return scheduleId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDays() {
        return days;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    // JavaFX Property для таблицы ScheduleEntry  (если необходимо использовать биндинг)
    public javafx.beans.property.StringProperty scheduleIdProperty() {
        return new javafx.beans.property.SimpleStringProperty(scheduleId);
    }

    public javafx.beans.property.StringProperty employeeIdProperty() {
        return new javafx.beans.property.SimpleStringProperty(employeeId);
    }

    public javafx.beans.property.StringProperty daysProperty() {
        return new javafx.beans.property.SimpleStringProperty(days);
    }

    public javafx.beans.property.StringProperty startTimeProperty() {
        return new javafx.beans.property.SimpleStringProperty(startTime);
    }

   
 public javafx.beans.property.StringProperty endTimeProperty() {
        return new javafx.beans.property.SimpleStringProperty(endTime);
    }

public void setScheduleId(String scheduleId) {
	this.scheduleId = scheduleId;
}

public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}

public void setDays(String days) {
	this.days = days;
}

public void setStartTime(String startTime) {
	this.startTime = startTime;
}

public void setEndTime(String endTime) {
	this.endTime = endTime;
}

@Override
public String toString() {
	return "ScheduleEntry [scheduleId=" + scheduleId + ", employeeId=" + employeeId + ", days=" + days + ", startTime="
			+ startTime + ", endTime=" + endTime + "]";
}


}
