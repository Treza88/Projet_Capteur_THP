package fr.synergy.projet_THP.apiJoinClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

public class ApiGetDay {
    @JsonProperty
    private String getDay;


    public ApiGetDay(String getDay) {
        this.getDay = getDay;
    }

    public String getGetDay() {
        return getDay;
    }

    public ApiGetDay() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ApiGetDay setGetDay(@JsonProperty("getDay") String getDay) {
        this.getDay = getDay;
        return null;
    }
}
