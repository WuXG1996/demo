package com.example.demo.mvc.pojo;

import com.google.common.collect.Lists;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by void on 2018/11/23.
 */
@Data
public class Job {
    @Id
    private String jobId;
    private String title;
    private List<Integer> tag;
    private Integer num;

    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;

    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2D)
    private List<Double> location2d;

    @GeoSpatialIndexed(type= GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location2dSphere;

    public Job() {
    }

    public Job(String title, Double lon, Double lat) {
        this.title = title;
        this.lon = lon;
        this.lat = lat;
        this.location2d = Lists.newArrayList(lon, lat);
        this.location2dSphere = new GeoJsonPoint(lon, lat);
    }
}
