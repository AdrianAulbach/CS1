package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The report Class represents notes taken by a doctor during a event with a patient
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name = "report")
public class Report extends PersistentObject {

    private String location;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String text;

    /*
	 * TODO: How can I reference an ID Column while extending PresisentObject? 
     */
    @ManyToOne
    @JoinColumn(name = "id")
    private MedicalRecord medicalRecord;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
