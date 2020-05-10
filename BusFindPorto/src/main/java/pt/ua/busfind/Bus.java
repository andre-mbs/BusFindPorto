package pt.ua.busfind;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author esP13
 */
@Entity
public class Bus {

    @Id
    @GeneratedValue
    private long id;
    private String node_id;
    private int location_id;
    private double head;
    private double lon;
    private double lat; 
    private int speed;
    private String ts; //mudar depois
    private String write_time; //mudar tambem para date

    @Override
    public String toString() {
        return "Bus{"
                + "id=" + id
                + ", node_id='" + node_id + '\''
                + ", location_id=" + location_id
                + ", head=" + head
                + ", lon=" + lon
                + ", lat=" + lat
                + ", speed=" + speed
                + ", ts='" + ts + '\''
                + ", write_time='" + write_time + '\''
                + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public double getHead() {
        return head;
    }

    public void setHead(double head) {
        this.head = head;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }
}
