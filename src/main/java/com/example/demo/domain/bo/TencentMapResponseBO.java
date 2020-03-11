package com.example.demo.domain.bo;

import lombok.Data;

/**
 * @author void
 * @date 2019/9/9 16:31
 * @desc
 */
@Data
public class TencentMapResponseBO {
    private Integer status;
    private String message;
    private IpResult result;

    private class IpResult{
        private String ip;
        private Location location;
        private AdInfo adInfo;

        public class AdInfo {
            private String nation;
            private String province;
            private String city;
            private String district;
            private String adcode;

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }
        }

        private class Location {
            private Double lat;
            private Double lng;

            public Double getLat() {
                return lat;
            }

            public void setLat(Double lat) {
                this.lat = lat;
            }

            public Double getLng() {
                return lng;
            }

            public void setLng(Double lng) {
                this.lng = lng;
            }
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public AdInfo getAdInfo() {
            return adInfo;
        }

        public void setAdInfo(AdInfo adInfo) {
            this.adInfo = adInfo;
        }
    }
}
